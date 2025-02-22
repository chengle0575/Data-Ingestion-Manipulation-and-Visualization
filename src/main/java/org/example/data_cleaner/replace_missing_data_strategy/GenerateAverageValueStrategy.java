package org.example.data_cleaner.replace_missing_data_strategy;

import java.util.List;
import java.util.Map;

public class GenerateAverageValueStrategy implements GenerateValueForMissingDataStrategy {

    /**
     * Generates the average value for a specified key across a list of data maps.
     *
     *  This method assumes that the value associated with the specified key in each map
     *  is a numeric type (such as Integer, Double, Long, etc.)
     *  @param rawData The list of maps containing the data to be processed.
     *  @param key The key whose values will be averaged.
     *  @return The calculated average as a Double.
     * */
    @Override
    public Object generateValue(List<Map<String, Object>> rawData, String key) {
        double sum=0;
        int size= rawData.size();
        for(Map<String,Object> data:rawData){
            Object value=data.getOrDefault(key,null);

            if(value==null){
                sum+=0;
            }else if(value instanceof Number){
                sum+=((Number) value).doubleValue();
            }else{
                throw new IllegalArgumentException("The raw data has some has invalid value for the key");
            }
        }
        return sum/size;
    }
}

