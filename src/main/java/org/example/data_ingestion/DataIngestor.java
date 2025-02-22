package org.example.data_ingestion;


import java.util.List;
import java.util.Map;

//TODO: define the return type
public interface DataIngestor {
    public abstract List<Map<String, Object>> ingest(String filepath);
}








