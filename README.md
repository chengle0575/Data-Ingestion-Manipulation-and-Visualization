# Data Ingestion, Visualization, and REST API


## Overview
This project involves building a local application that handles structured data ingestion from multiple sources (CSV, PPTX, JSON, and PDF formats), processes and cleans the data, merges it into a unified structure, and then serves the data via a REST API. The data is visualized using charts (e.g., bar chart, line graph) and can be filtered or sorted for better insights.


## Key Features:
#### Data Ingestion: 
The application processes structured data from various formats, including CSV, PPTX( only tabular data ), JSON, and PDF( only tabular data ), using different strategies and libraries tailored to each specific format.

#### Data Cleaning:
It handles missing values and cleans data for processing. To improve extensibility, the Strategy design pattern is utilized, allowing for easy customization and addition of new data cleaning strategies. Currently only one algorithm is used. Currently, it uses an algorithm that generates values based on the average.
#### Data Merging: 
All ingested data is unified into a single, structured data for further analysis.
#### Data Visualization: 
The data is visualized with a pie chart showing member distribution by location, with an option to filter by membership type. The chart can highlight trends in location-based membership preferences if enough data is available. A bar chart is provided to display the number of members sold in each quarter of the year.
#### REST API: 
REST API is provided to access the data in JSON format


## Set up Instructions:
1. Git clone the project locally
2. Go into the project. Run `mvn spring-boot:run` in terminal
3. Go to `http://localhost:8080/` in browser
   

## Testing Instructions:
1. Go to '/src/test' folder.
2. Run each Tese class inside.


## Assumptions & Chanllenges:
1. To ingest table content from a PPTX file, the table must be created using PowerPoint's built-in tools and components, rather than being manually inserted. This is the contsrain from library.
3. Current ingestor algorithm for the json file is not versatile. It is designed based on how the json file is structured.
4. The data cleaning algorithm (1) removes empty values for String types and (2) generates numeric values for missing data. For now, the schema is constrained to either string or numeric types to ensure the cleaning process works correctly, though it may not fully represent the true data types.
5. For data cleaning, the clean algorithm currently only handle the top level missing value check. If there is null value in the nested structure, the algorithm won't do anything.
