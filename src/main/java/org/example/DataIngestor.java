package org.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

//TODO: define the return type
public interface DataIngestor {
    public abstract List<Map<String, Object>> ingest(String filepath);
}




class PPTXDataIngestor implements DataIngestor {
    @Override
    public List<Map<String, Object>> ingest(String filepath) {
        return null;
    }
}




class PDFDataIngestor implements DataIngestor {
    @Override
    public List<Map<String, Object>> ingest(String filepath) {
        return null;
    }
}