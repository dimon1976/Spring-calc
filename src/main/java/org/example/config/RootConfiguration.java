package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.Scanner;

@Configuration
@ComponentScan("org.example")
public class RootConfiguration {

    @Bean
    public Scanner Scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public LinkedList<Double> usersList(){
        return new LinkedList<>();
    }
}
