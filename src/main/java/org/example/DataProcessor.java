package org.example;

import org.apache.commons.io.FilenameUtils;
import org.example.data_cleaner.DataCleaner;
import org.example.data_cleaner.replace_missing_data_strategy.GenerateAverageValueStrategy;
import org.example.data_ingestor.CSVDataIngestor;
import org.example.data_ingestor.JSONDataIngestor;
import org.example.data_ingestor.PDFDataIngestor;
import org.example.data_ingestor.PPTXDataIngestor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * DataProcess will ingest data from the original file and clean the data
 *
 * */
public class DataProcessor {

    public static List<Map<String, Object>> getCleanedData(String filepath, Map<String,Class> schema)  {
        String extension= FilenameUtils.getExtension(filepath);

        //1. Extract data
        List<Map<String,Object>> rawData=new ArrayList<>();
        if(Objects.equals(extension, "csv")){
            rawData=new CSVDataIngestor().ingest(filepath);
        }else if(Objects.equals(extension, "pdf")){
            rawData=new PDFDataIngestor().ingest(filepath);
        }else if(Objects.equals(extension, "pptx")){
            rawData=new PPTXDataIngestor().ingest(filepath);
        }else if(Objects.equals(extension, "json")){
            rawData=new JSONDataIngestor().ingest(filepath);
        }else{
            throw new RuntimeException("Cannot process this typf of file.");
        }

        //2. Clean data
        List<Map<String, Object>> cleanedData=DataCleaner.clean(rawData,schema,new GenerateAverageValueStrategy());

        return cleanedData;
    }



    public static List<Map<String, Object>>  processMultipleDataAndMerge(String[] filepaths,List<Map<String,Class>> schemas)  {

        List<Map<String, Object>> mergedData=new ArrayList<>();
        for(int i=0;i<filepaths.length;i++){
            List<Map<String, Object>> cleanedListData= getCleanedData(filepaths[i],schemas.get(i));
            mergedData.addAll(cleanedListData);
        }

        return mergedData;
    }
}
