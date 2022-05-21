package com.cs.assesment;

import com.cs.assesment.parser.LogParser;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        String logFilePath = args[0];
        processLogs(logFilePath);
    }

    private static void processLogs(String logFilePath) throws IOException {
        LogParser jSonParser = new LogParser();
        jSonParser.parse(logFilePath);
    }


}
