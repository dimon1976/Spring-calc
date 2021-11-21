package org.example.console;

import java.util.Scanner;

public class ConsoleReader {
    private final Scanner sc;

    public ConsoleReader(Scanner sc) {
        this.sc = sc;
    }

    public Scanner getSc() {
        return sc;
    }
}
