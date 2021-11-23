package org.example.service;

import org.example.entity.User;
import org.example.storage.InMemoryUserStorage;
import org.springframework.stereotype.Component;

@Component
public class CalcService {
   private InMemoryUserStorage storage;

    public CalcService(InMemoryUserStorage storage) {
        this.storage = storage;
    }

    public double getOperation(String op, double a, double b, User user) {
        switch (op) {
            case "+":
                storage.save((sum(a, b)), user);
                return sum(a, b);
            case "-":
                storage.save((sub(a, b)), user);
                return sub(a, b);
            case "*":
                storage.save((multi(a, b)), user);
                return multi(a, b);
            case "/":
                storage.save((division(a, b)), user);
                return division(a, b);
        }
        return 0;
    }

        private double sum ( double a, double b){
            return a + b;
        }

        private double division ( double a, double b){
            return a / b;
        }

        private double multi ( double a, double b){
            return a * b;
        }

        private double sub ( double a, double b){
            return a - b;
        }
    }
