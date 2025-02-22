import org.example.data_cleaner.replace_missing_data_strategy.GenerateAverageValueStrategy;
import org.example.data_cleaner.replace_missing_data_strategy.GenerateValueForMissingDataStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GenerateAverageValueStrategyTest {

    private GenerateValueForMissingDataStrategy strategy;

    @Before
    public void setUp() {
        strategy = new GenerateAverageValueStrategy();
    }

    @Test
    public void testGenerateValue_validNumericStrings() {
        List<Map<String, Object>> rawData = new ArrayList<>();

        // Sample data with numeric strings
        Map<String, Object> record1 = new HashMap<>();
        record1.put("value", "10");

        Map<String, Object> record2 = new HashMap<>();
        record2.put("value", "20");

        Map<String, Object> record3 = new HashMap<>();
        record3.put("value", "30");

        rawData.add(record1);
        rawData.add(record2);
        rawData.add(record3);

        // Expected average: (10 + 20 + 30) / 3 = 20.0
        Object result = strategy.generateValue(rawData, "value");

        assertEquals(20.0, result);
    }

    @Test
    public void testGenerateValue_validNumericValues() {
        List<Map<String, Object>> rawData = new ArrayList<>();

        // Sample data with numeric values
        Map<String, Object> record1 = new HashMap<>();
        record1.put("value", 10);

        Map<String, Object> record2 = new HashMap<>();
        record2.put("value", 20);

        Map<String, Object> record3 = new HashMap<>();
        record3.put("value", 30);

        rawData.add(record1);
        rawData.add(record2);
        rawData.add(record3);

        // Expected average: (10 + 20 + 30) / 3 = 20.0
        Object result = strategy.generateValue(rawData, "value");

        assertEquals(20.0, result);
    }

    @Test
    public void testGenerateValue_withEmptyStrings() {
        List<Map<String, Object>> rawData = new ArrayList<>();

        // Sample data with empty strings
        Map<String, Object> record1 = new HashMap<>();
        record1.put("value", "0");

        Map<String, Object> record2 = new HashMap<>();
        record2.put("value", "");

        Map<String, Object> record3 = new HashMap<>();
        record3.put("value", "30");

        rawData.add(record1);
        rawData.add(record2);
        rawData.add(record3);

        // Expected average: (0 + 30) / 2 = 15.0 (empty string is skipped)
        Object result = strategy.generateValue(rawData, "value");

        assertEquals(15.0, result);
    }

    @Test
    public void testGenerateValue_invalidString() {
        List<Map<String, Object>> rawData = new ArrayList<>();

        // Sample data with an invalid string value
        Map<String, Object> record1 = new HashMap<>();
        record1.put("value", "10");

        Map<String, Object> record2 = new HashMap<>();
        record2.put("value", "invalid");

        rawData.add(record1);
        rawData.add(record2);

        // The method should throw an exception because "invalid" cannot be parsed to a number
        try {
            strategy.generateValue(rawData, "value");
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Some value in the data for this key: value cannot be casted to number", e.getMessage());
        }
    }

    @Test
    public void testGenerateValue_withEmptyData() {
        List<Map<String, Object>> rawData = new ArrayList<>();

        // Sample empty data
        Object result = strategy.generateValue(rawData, "value");

        // Expected result: average of an empty list should be 0.0
        assertEquals(0.0, result);
    }

    @Test
    public void testGenerateValue_withNullValues() {
        List<Map<String, Object>> rawData = new ArrayList<>();

        // Sample data with null values
        Map<String, Object> record1 = new HashMap<>();
        record1.put("value", null);

        Map<String, Object> record2 = new HashMap<>();
        record2.put("value", "20");

        rawData.add(record1);
        rawData.add(record2);

        // Expected average: (0 + 20) / 1 = 20.0 (null values are treated as 0)
        Object result = strategy.generateValue(rawData, "value");

        assertEquals(20.0, result);
    }
}
