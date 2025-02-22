import org.example.data_cleaner.replace_missing_data_strategy.GenerateAverageValueStrategy;
import org.example.data_cleaner.replace_missing_data_strategy.GenerateValueForMissingDataStrategy;
import org.example.data_processor.DataProcessor;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

        import static org.junit.Assert.assertEquals;

public class DataProcessorTest {

    private GenerateValueForMissingDataStrategy replaceDataStrategy;

    @Before
    public void setUp() {
        // Initialize the strategy for handling missing data
        replaceDataStrategy = new GenerateAverageValueStrategy();
    }

    @Test
    public void testGetCleanedData_validCSV() {
        // Assuming CSVDataIngestor returns the sample data
        String filepath = "src/test/java/testdata/sample.csv";
        Map<String, Class> schema = new HashMap<>();
        schema.put("value", String.class);

        List<Map<String, Object>> cleanedData = DataProcessor.getCleanedData(filepath, schema);

        // Expected cleaned data after ingestion and cleaning
        List<Map<String, Object>> expectedData = new ArrayList<>();
        expectedData.add(Map.of("value",10));
        expectedData.add(Map.of("value",20));
        expectedData.add(Map.of("value",30));
        expectedData.add(Map.of("value",40));
        expectedData.add(Map.of("value",60));

        assertEquals(expectedData.toString(), cleanedData.toString());
    }


    @Test(expected = RuntimeException.class)
    public void testGetCleanedData_invalidFileType() {
        // Test case for unsupported file type
        String filepath = "./testdata/sample.txt";  // Unsupported file extension
        Map<String, Class> schema = new HashMap<>();
        schema.put("value", String.class);

        // Should throw RuntimeException as the file type is unsupported
        DataProcessor.getCleanedData(filepath, schema);
    }

}
