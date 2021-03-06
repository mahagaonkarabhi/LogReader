package com.cs.assesment;

import com.cs.assesment.parser.LogParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class LogParserTest {

    @InjectMocks
    LogParser logParser;

    @Test()
    public void testParse() throws IOException {
        this.logParser.parse("src/test/resources/Logs.txt");
    }

}

