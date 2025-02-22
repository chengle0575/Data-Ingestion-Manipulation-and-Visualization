package org.example.data_cleaner;

import org.example.data_cleaner.replace_missing_data_strategy.GenerateValueForMissingDataStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataCleaner {

    /**
     * Clean only top layer null value of the data
     * @param rawData the raw data ingested from files
     * @param schema the data type of the top level data
     * @example: [{a:null,b:"content"},{a:"content",b:{c:null,d:"content"}}] => [{a:"content",b:{c:null,d:"content"}}]
     * */
    List<Map<String, Object>> clean(List<Map<String, Object>> rawData, Map<String,Class> schema, GenerateValueForMissingDataStrategy replaceDataStrategy) {

        List<Map<String, Object>> cleanedData = new ArrayList<>();

        for (Map<String, Object> data : rawData) {
            if (!data.containsValue(null)) {
                cleanedData.add(data);
            } else {
                //Deal with the null value, if number is lost, insert a value based on algorithm, else drop it
                for (String key : data.keySet()) {
                    if (schema.get(key).equals(Number.class)) {
                        Object generatedValue = replaceDataStrategy.generateValue(rawData, key);
                        data.put(key, generatedValue);
                        cleanedData.add(data);
                    }
                }

            }


        }
        return cleanedData;

    }

    //TODO: clean method with different signature for other rawData type


}