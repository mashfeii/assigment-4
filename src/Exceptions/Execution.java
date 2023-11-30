package Exceptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Execution {
    public static void terminate(String errorMessage) throws IOException {
        BufferedWriter outputStream = new BufferedWriter(new FileWriter("output.txt"));
        outputStream.write(errorMessage + "\n");
        outputStream.close();
        System.exit(0);
    }
}