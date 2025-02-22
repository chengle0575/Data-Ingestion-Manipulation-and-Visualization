package org.example.data_cleaner.replace_missing_data_strategy;

import java.util.List;
import java.util.Map;

public class GenerateAverageValueStrategy implements GenerateValueForMissingDataStrategy {

    /**
     * Generates the average value for a specified key across a list of data maps.
     *
     *  This method assumes that the value associated with the specified key in each map
     *  store the numeric number as string.
     *
     *  @param rawData The list of maps containing the data to be processed.
     *  @param key The key whose values will be averaged.
     *  @return The calculated average as a Double.
     * */
    @Override
    public Object generateValue(List<Map<String, Object>> rawData, String key) {
        double sum=0;
        int size= rawData.size();
        for(Map<String,Object> data:rawData){
            Object value=data.getOrDefault(key,0);

            try{
                if (value instanceof String stringValue) {
                    if(value=="") continue; //ignore the empty value when counting the sum
                    sum +=  Double.valueOf(stringValue);
                } else {
                    // If value is already a number, directly add it
                    sum += ((Number) value).doubleValue();
                }
            } catch (RuntimeException e) {
                throw new RuntimeException("Some value in the data for this key: "+ key+" cannot be casted to number");
            }

        }
        return sum/size;
    }
}

