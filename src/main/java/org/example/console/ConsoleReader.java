package org.example.console;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleReader {
    private final Scanner sc;


    public ConsoleReader(Scanner sc) {
        this.sc = sc;
    }

    public Scanner getSc() {
        return sc;
    }
}
