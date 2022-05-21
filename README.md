# LogReader
This utility is created to perform log verification based on few paramaters from the log line.
It will take a file path as input to the main method
It will parse the input .txt file line by line and create a LogDetails object from it
These LogDetails objects further used to calculate the event duration for perticular event.
If the duration is > 4 MS it will log an logger entry in **logAlertGeneratorDebug.log** file.
In case of exception it will log exception stack trace in log file.
please use the **Logs.txt** file for testing purpose
