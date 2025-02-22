package org.example;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVDataIngestor implements DataIngestor {
    @Override
    public List<Map<String, Object>> ingest(String filepath) {

        List<Map<String, Object>> res = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(filepath))) {
            String[] titles = csvReader.readNext();  // Read the header line (titles)
            if (titles == null) {
                return List.of();  // Return empty list if no content
            }

            //Read the content and form the return value
            String[] contentLine;
            while ((contentLine = csvReader.readNext()) != null) {
                Map<String, Object> record = new HashMap<>();
                for (int i = 0; i < titles.length; i++) {
                    record.put(titles[i], contentLine[i]);
                }
                res.add(record);
            }

            return res;

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}