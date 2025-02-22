package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONDataIngestor implements DataIngestor {
    @Override
    public List<Map<String, Object>> ingest(String filepath) {
        //Use Jackson library
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            Map<String, Object>  data = objectMapper.readValue(
                    new File(filepath),
                    Map.class
            );

            //Convert to array of map
            List<Map<String, Object>> formattedData=new ArrayList<>();
            processTopLevel(data, formattedData);


            return formattedData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Process only top level for now. Separate the method out to be dynamic
    private void processTopLevel(Map<String, Object> data, List<Map<String, Object>> formattedData) {
        // Iterate over each entry at the top level of the JSON
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            Object value = entry.getValue();

            // If the value is a list, treat each item as a separate record
            if (value instanceof List) {
                List<Object> list = (List<Object>) value;

                for (Object obj : list) {
                    // Ensure that the object is a Map
                    if (obj instanceof Map) {
                        Map<String, Object> record = (Map<String, Object>) obj;
                        // Add the record to the formatted data list
                        formattedData.add(record);
                    }
                }
            }
        }
    }

}

