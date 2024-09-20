package com.org.utils;

import com.org.constants.FrameworkConstants;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public final class CsvUtils {

    public static List<Map<String, String>> getTestDetails(String csvFilename) {

        Reader csvReader = null;
        List<Map<String, String>> list = new ArrayList<>();

        try {
            csvReader = Files.newBufferedReader(Paths.get(FrameworkConstants.getCsvpath()
                    + csvFilename + ".csv"));

            CSVParser parser = new CSVParser(csvReader, CSVFormat.DEFAULT);

            List<CSVRecord> records = parser.getRecords();
            List<String> columns = new ArrayList<>();
            for (CSVRecord record: records) {
                // Adding column names
                if(record.getRecordNumber() == 1) {
                    for (String columnName: record) columns.add(columnName);
                    continue;
                }

                Map<String, String> map = new HashMap<>();
                for (int i = 0; i < record.size(); i++) {
                    String key = columns.get(i);
                    String value = record.get(i);
                    map.put(key, value);
                }
                if(map.get("Execute").equalsIgnoreCase("Yes"))
                    list.add(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (Objects.nonNull(csvReader)) {
                try {
                    csvReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;

    }

}
