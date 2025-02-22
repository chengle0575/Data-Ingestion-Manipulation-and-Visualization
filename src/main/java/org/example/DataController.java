package org.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.data_processor.DataProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/*
* This DataController is used to send back data
* */
@RestController
@RequestMapping("/api") //Base url
public class DataController {

    private String[] filepaths=new String[]{"src/main/datasets/dataset1.json","src/main/datasets/dataset2.csv","src/main/datasets/dataset3.pdf","src/main/datasets/dataset4.pptx"};
    private List<Map<String, Class>> schemas=List.of(Map.of(
            "id", String.class,
            "name", String.class,
            "industry", String.class,
            "revenue ", String.class,
            "location",String.class,
            "employees",String.class,
            "performance", String.class
    ),Map.of(

            "Date", String.class,
            "Membership_ID",String.class,
            "Membership_Type",String.class,
            "Activity",String.class,
            "Revenue",String.class,
            "Duration (Minutes)", Number.class,
            "Location",String.class
    ),Map.of(
            "Year", String.class,
            "Quarter", Number.class,
            "Revenue (in $)", Number.class,
            "Memberships Sold ", Number.class
    ),Map.of(
            "Quarter", String.class,
            "Revenue (in $)", Number.class,
            "membership_sold", Number.class,
            "Avg duration", Number.class
    ));




    @GetMapping("/data/{file_type}")
    public String getDataByFileType(@PathVariable String file_type){
        System.out.println("reached controller");
        System.out.println(file_type);
        try{
            List<Map<String,Object>> res;
            if(Objects.equals(file_type, "json")){
                res= DataProcessor.getCleanedData(filepaths[0],schemas.get(0));
            }else if(Objects.equals(file_type, "csv")){
                res=DataProcessor.getCleanedData(filepaths[1],schemas.get(1));
            }else if(Objects.equals(file_type, "pdf")){
                res=DataProcessor.getCleanedData(filepaths[2],schemas.get(2));
            }else if(Objects.equals(file_type, "pptx")){
                res=DataProcessor.getCleanedData(filepaths[3],schemas.get(3));
            }else{
                return "Not Found";
            }

            //Convert to json and respond to client
            return utility.convertToJSON(res);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/data")
    public String getData(){
        try{
            //Process the data
            List<Map<String,Object>> res=DataProcessor.processMultipleDataAndMerge(filepaths,schemas);

            //Convert to json and respond to client
            return utility.convertToJSON(res);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
