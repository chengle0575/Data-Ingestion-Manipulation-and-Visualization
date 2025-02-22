package org.example.data_ingestion;

import org.apache.poi.xslf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PPTXDataIngestor implements DataIngestor {
    /**
     * Get all tables in the PPTX. Assume that the table in the pptx is drawn using table tools in ppt.
     * */
    @Override
    public List<Map<String, Object>> ingest(String filepath) {
        List<Map<String, Object>> data = new ArrayList<>();
        try{
                // Load the PowerPoint file
                FileInputStream pptxFile = new FileInputStream(filepath);
                XMLSlideShow pptx = new XMLSlideShow(pptxFile);

                // Iterate through each slide in the PowerPoint
                for (XSLFSlide slide : pptx.getSlides()) {
                    for (XSLFShape shape : slide.getShapes()) {
                        if (shape instanceof XSLFTable) { // ! Deal with Table inside pptx
                           
                            List<XSLFTableRow> table=((XSLFTable) shape).getRows();
                            List<XSLFTableCell> headers=table.get(0).getCells(); //Get headers out of table

                            // Process each reord in the table, start from index-1 to drop the header
                            for (int i=1;i<table.size();i++) {
                                Map<String,Object> record=new HashMap<>(); // Gather each row content with header as a record
                                List<XSLFTableCell> row=table.get(i).getCells();

                                // Process each cell in the row
                                for (int j=0;j<row.size();j++) {
                                    record.put(headers.get(j).getText(),row.get(j).getText());
                                }
                                data.add(record);
                            }
                        }
                    }
                }

                pptxFile.close();
                return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
