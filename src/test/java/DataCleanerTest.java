import org.example.data_cleaner.DataCleaner;
import org.example.data_cleaner.replace_missing_data_strategy.GenerateAverageValueStrategy;
import org.example.data_cleaner.replace_missing_data_strategy.GenerateValueForMissingDataStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataCleanerTest {

    private GenerateValueForMissingDataStrategy replaceDataStrategy;

    @Before
    public void setUp() {
        // Initialize the strategy for handling missing data
        replaceDataStrategy = new GenerateAverageValueStrategy();
    }

    @Test
    public void testClean_validData() {
        List<Map<String, Object>> rawData = new ArrayList<>();

        // Sample data with no missing or empty values
        Map<String, Object> record1 = new HashMap<>();
        record1.put("a", "content");
        record1.put("b", "content");

        Map<String, Object> record2 = new HashMap<>();
        record2.put("a", "content");
        record2.put("b", "content");

        rawData.add(record1);
        rawData.add(record2);

        // Schema specifying the expected types of the top-level data
        Map<String, Class> schema = new HashMap<>();
        schema.put("a", String.class);
        schema.put("b", String.class);

        // Cleaning the data with the given strategy
        List<Map<String, Object>> cleanedData = DataCleaner.clean(rawData, schema, replaceDataStrategy);

        // Check if cleaned data is same as raw data (since no values are missing)
        assertEquals(2, cleanedData.size());
        assertTrue(cleanedData.contains(record1));
        assertTrue(cleanedData.contains(record2));
    }

    @Test
    public void testClean_dataWithEmptyValues() {
        List<Map<String, Object>> rawData = new ArrayList<>();

        // Sample data with empty value for 'a' in one record
        Map<String, Object> record1 = new HashMap<>();
        record1.put("a", null);
        record1.put("b", "content");

        Map<String, Object> record2 = new HashMap<>();
        record2.put("a", "content");
        record2.put("b", "content");

        rawData.add(record1);
        rawData.add(record2);

        // Schema specifying the expected types of the top-level data
        Map<String, Class> schema = new HashMap<>();
        schema.put("a", String.class);
        schema.put("b", String.class);

        // Cleaning the data with the given strategy
        List<Map<String, Object>> cleanedData = DataCleaner.clean(rawData, schema, replaceDataStrategy);

        // Check that the record with the null value for 'a' is removed
        assertEquals(1, cleanedData.size());
        assertTrue(cleanedData.contains(record2));
    }

    @Test
    public void testClean_dataWithEmptyString() {
        List<Map<String, Object>> rawData = new ArrayList<>();

        // Sample data with empty string value for 'a' in one record
        Map<String, Object> record1 = new HashMap<>();
        record1.put("a", "");
        record1.put("b", "content");

        Map<String, Object> record2 = new HashMap<>();
        record2.put("a", "content");
        record2.put("b", "content");

        rawData.add(record1);
        rawData.add(record2);

        // Schema specifying the expected types of the top-level data
        Map<String, Class> schema = new HashMap<>();
        schema.put("a", String.class);
        schema.put("b", String.class);

        // Cleaning the data with the given strategy
        List<Map<String, Object>> cleanedData = DataCleaner.clean(rawData, schema, replaceDataStrategy);

        // Check that the record with the empty string value for 'a' is removed
        assertEquals(1, cleanedData.size());
        assertTrue(cleanedData.contains(record2));
    }

    @Test
    public void testClean_dataWithMissingNumericValue() {
        List<Map<String, Object>> rawData = new ArrayList<>();

        // Sample data with missing numeric value for 'value' in one record
        Map<String, Object> record1 = new HashMap<>();
        record1.put("value", "");

        Map<String, Object> record2 = new HashMap<>();
        record2.put("value", "30");

        rawData.add(record1);
        rawData.add(record2);

        // Schema specifying the expected types of the top-level data
        Map<String, Class> schema = new HashMap<>();
        schema.put("value", String.class);

        // Cleaning the data with the given strategy
        List<Map<String, Object>> cleanedData = DataCleaner.clean(rawData, schema, replaceDataStrategy);

        // The first record will be dropped due to String.class although it is literally value.
        assertEquals(1, cleanedData.size());

    }

    @Test
    public void testClean_emptyData() {
        List<Map<String, Object>> rawData = new ArrayList<>();

        // Empty data
        Map<String, Class> schema = new HashMap<>();
        schema.put("a", String.class);
        schema.put("b", String.class);

        // Cleaning the data with the given strategy
        List<Map<String, Object>> cleanedData = DataCleaner.clean(rawData, schema, replaceDataStrategy);

        // Check that no data is returned (empty data should result in an empty cleaned list)
        assertEquals(0, cleanedData.size());
    }
}
