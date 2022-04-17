package ru.otus.homework3.service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceStreams implements IOService {
    private final PrintStream printStream;
    private final Scanner inputScanner;

    public IOServiceStreams(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.inputScanner = new Scanner(inputStream);
    }

    public void outputString(String text) {
        printStream.println(text);
    }

    public String readString() {
        return inputScanner.nextLine();
    }
}
