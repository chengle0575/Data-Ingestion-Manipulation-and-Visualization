
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.utility;
import org.junit.Test;


import java.util.*;

import static org.junit.Assert.assertEquals;


public class ConvertToJSONTest {

    @Test
    public void testConvertToJSON() throws JsonProcessingException {
        // Prepare input data
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> record1 = new HashMap<>();
        record1.put("value", 10);
        Map<String, Object> record2 = new HashMap<>();
        record2.put("value", 20);
        data.add(record1);
        data.add(record2);

        // Expected JSON output
        String expectedJson = "[{\"value\":10},{\"value\":20}]";

        // Call the method
        String actualJson = utility.convertToJSON(data);

        // Assert that the actual JSON output matches the expected JSON
        assertEquals(expectedJson, actualJson);
    }
}
