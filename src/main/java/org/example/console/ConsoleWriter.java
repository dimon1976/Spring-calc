package org.example.console;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class ConsoleWriter {

    public void print(String message) {
        System.out.println(message);
    }

    public void print(Double number) {
        System.out.println(number);
    }

    public void printDoubleList(LinkedList<Double> list) {
        for (Double i : list) {
            System.out.println("result:" + i);
        }
    }

}
