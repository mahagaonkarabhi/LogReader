package com.cs.assesment.mapper;

import com.cs.assesment.model.LogDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;

public class DataMapper {
    private static final Logger logger = Logger.getLogger(DataMapper.class.getName());

    public LogDetails getLogDetails(ObjectMapper objectMapper, String content) {
        try {
            return objectMapper.readValue(content, LogDetails.class);
        } catch (IOException e) {
            logger.debug("Exception occurred while parsing the input file ", e);
        }
        return null;
    }
}
