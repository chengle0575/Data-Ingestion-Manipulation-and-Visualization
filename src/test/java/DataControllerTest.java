
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DataController;
import org.example.data_processor.DataProcessor;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.*;

@WebMvcTest(DataController.class)
public class DataControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataProcessor dataProcessor;

    @Autowired
    private ObjectMapper objectMapper;



    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetDataByFileTypeJson() throws Exception {
        // Mocking the DataProcessor.getCleanedData method
        List<Map<String, Object>> mockData = new ArrayList<>();
        Map<String, Object> mockRecord = new HashMap<>();
        mockRecord.put("id", "1");
        mockRecord.put("name", "Test Name");
        mockData.add(mockRecord);

        // Mock the response from DataProcessor
        Mockito.mockStatic(DataProcessor.class).when(() ->
                DataProcessor.getCleanedData(Mockito.anyString(), Mockito.any())).thenReturn(mockData);

        // Perform GET request to /api/data/json
        mockMvc.perform(get("/api/data/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockData)));
    }

    @Test
    public void testGetDataByFileTypeNotFound() throws Exception {
        // Perform GET request with invalid file type
        mockMvc.perform(get("/api/data/invalid")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Not Found"));
    }

    @Test
    public void testGetData() throws Exception {
        // Mocking the DataProcessor.processMultipleDataAndMerge method
        List<Map<String, Object>> mockData = new ArrayList<>();
        Map<String, Object> mockRecord = new HashMap<>();
        mockRecord.put("id", "1");
        mockRecord.put("name", "Test Name");
        mockData.add(mockRecord);

        // Mock the response from DataProcessor
        Mockito.mockStatic(DataProcessor.class).when(() ->
                DataProcessor.processMultipleDataAndMerge(Mockito.any(), Mockito.any())).thenReturn(mockData);

        // Perform GET request to /api/data
        mockMvc.perform(get("/api/data")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockData)));
    }
}
