package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class utility {

    public static String convertToJSON(List<Map<String, Object>> data) throws JsonProcessingException {

        ObjectMapper objectMapper=new ObjectMapper();
        String jsonResult=objectMapper.writeValueAsString(data);

        System.out.println("finish data conversion, check the json resulthere");
        System.out.println(jsonResult);
        return jsonResult;
    }

}
