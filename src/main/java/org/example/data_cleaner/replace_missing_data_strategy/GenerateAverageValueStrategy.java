package org.example.data_cleaner.replace_missing_data_strategy;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        // Edge case
        if(rawData.size()==0)
            return 0.0;


        // Normal case
        double sum=0;
        int size= rawData.size();
        int emptyCounts=0;
        for(Map<String,Object> data:rawData){
            Object value=data.getOrDefault(key,0);

            try{
                if( Objects.equals(null,value)){
                    emptyCounts++;
                }else if (value instanceof String stringValue) {
                    if(value=="") {
                        emptyCounts++;
                        continue; //ignore the empty value when counting the sum
                    }
                    sum +=  Double.valueOf(stringValue);
                } else if(value instanceof Number){
                    // If value is already a number, directly add it
                    sum += ((Number) value).doubleValue();
                }
            } catch (RuntimeException e) {
                throw new RuntimeException("Some value in the data for this key: "+ key+" cannot be casted to number");
            }

        }
        return sum/(size-emptyCounts);
    }
}

