package org.example.data_ingestion;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PDFDataIngestor implements DataIngestor {

    /**
     * Ingest Tabular data from PDF.
     *
     * @param filepath  Assume the data in the provided pdf is tabular
     *
     */
    @Override
    public List<Map<String, Object>> ingest(String filepath) {
        List<Map<String, Object>> formattedData = new ArrayList<>();

        try (PDDocument document = PDDocument.load(new File(filepath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            //Split text into lines, assuming rows are separated by newlines
            String[] lines = text.split("\n");


            String[] headers = new String[]{"Year","Quarter","Revenue($)","Memberships_Sold"};  //TODO: make the stcuture as dynamic input

            //Iterate through remaining lines to extract data for each row
            //Drop the title of the file and headers, start from index-
            for (int i = 2; i < lines.length; i++) {
                String line = lines[i].trim();

                if (!line.isEmpty()) {
                    // Split each line into columns
                    String[] columns = line.split("\\s+");
                    Map<String, Object> row = new HashMap<>();

                    // Map each column to its corresponding header
                    for (int j = 0; j < columns.length && j < headers.length; j++) {
                        row.put(headers[j], columns[j]);
                    }

                    // Add the row data to the result list
                    formattedData.add(row);
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return formattedData;
    }
}