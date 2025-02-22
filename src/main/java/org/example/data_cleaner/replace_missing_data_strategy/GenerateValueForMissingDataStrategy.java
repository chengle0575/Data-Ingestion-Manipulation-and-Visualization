package org.example.data_cleaner.replace_missing_data_strategy;

import java.util.List;
import java.util.Map;

public interface GenerateValueForMissingDataStrategy {
    Object generateValue(List<Map<String, Object>> rawData, String key);
}
