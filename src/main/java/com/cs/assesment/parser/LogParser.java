package com.cs.assesment.parser;

import com.cs.assesment.mapper.DataMapper;
import com.cs.assesment.model.LogDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;

public class LogParser {

    private static final long DESIRED_TIME_DIFFERENCE = 4L;
    private final Logger logger = Logger.getLogger(LogParser.class.getName());
    private DataMapper dataMapper = null;

    public LogParser() {
        this.dataMapper = new DataMapper();
    }

    public void parse(String filePath) throws IOException {
        readFile(filePath);
    }

    private void readFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, LogDetails> map = new HashMap<>();
        try (Stream<String> stream = lines(get(filePath))) {
            stream.forEach(s -> extractAndPrint(objectMapper, map, s));
        }

    }

    private void extractAndPrint(ObjectMapper objectMapper, Map<String, LogDetails> map, String s)  {
        LogDetails logDetails = dataMapper.getLogDetails(objectMapper, s);
        if(logDetails !=null){
            if (map.containsKey(logDetails.getId())) {
                LogDetails logDetails1 = map.get(logDetails.getId());
                long difference = getTimeDifference(logDetails, logDetails1);
                boolean alert = false;
                if (difference > DESIRED_TIME_DIFFERENCE) {
                    alert = true;
                    logger.debug("Event id - " + logDetails.getId() + " Event duration - " + difference + " Alert - " + alert);
                }
                logDetails.setAlert(alert);
                logDetails.setDuration(difference);
                // TODO - Save the record (logdetails information) in DB with alert and Event Duration
            } else {
                map.put(logDetails.getId(), logDetails);
            }
        }

    }


    private long getTimeDifference(LogDetails logDetails, LogDetails logDetails1) {
        long diff = 0;
        if (logDetails.getState().equalsIgnoreCase("STARTED")) {
            diff = logDetails1.getTimeStamp() - logDetails.getTimeStamp();
        } else {
            diff = logDetails.getTimeStamp() - logDetails1.getTimeStamp();
        }
        return diff;
    }
}
