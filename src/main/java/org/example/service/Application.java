package org.example.service;

import org.example.console.ConsoleReader;
import org.example.console.ConsoleWriter;
import org.example.entity.User;
import org.example.storage.InMemoryStorage;

import java.util.Scanner;

public class Application {
    Scanner scanner = new Scanner(System.in);
    InMemoryStorage storage = new InMemoryStorage();
    private UserService userService = new UserService();
    private final ConsoleReader cr = new ConsoleReader(scanner);
    private final ConsoleWriter cw = new ConsoleWriter();
    private static final String num = "Input number:";
    private static final String operation = "Enter operation +, -, *, /  e - Exit";
    private static final String wrongType = "You entered the wrong type!";
    private static final String wrongNumber = "Wrong number!";
    private boolean status = false;

//    public Application(ConsoleReader cr, ConsoleWriter cw) {
//        this.cr = cr;
//        this.cw = cw;
//    }


    public void menu() {
        cw.print("1) - Register");
        cw.print("2) - Authorization");
        cw.print("3) - Exit");
        int i = getInt();
        getChoice(i);
    }

    public void userMenu(User user) {
        cw.print("1) - Calculator");
        cw.print("2) - History operation");
        cw.print("3) - Logout");
        cw.print("4) - Exit");
        int i = getInt();
        if (i == 4) {
            cw.print("Exit");
            System.exit(i);
        }
        userChoice(i, user);
    }

    public void run(User user) {
        while (!status) {
            cw.print(operation);
            String op = getLine();
            if (op.equals("e")) {
                status = false;
                cw.print("Exit to menu");
                userMenu(user);
                break;
            }
            cw.print(num);
            double a = getDouble();
            cw.print(num);
            double b = getDouble();
            cr.getSc().nextLine();
            double result = getOperation(op, a, b, user);
            cw.print(result);
        }
    }

    private void getChoice(int i) {
        switch (i) {
            case 1:
                cw.print("Enter login:");
                String login = getLine();
                if (userService.findByName(login)) {
                    cw.print("User already exist");
                    menu();
                    break;
                } else {
                    cw.print("Enter password:");
                    String password = getLine();
                    User user = new User(password, login);
                    userService.saveUser(login, password);
                    getChoice(2);
                }

                break;
            case 2:
                cw.print("Authorization - enter login:");
                User user = userService.getUser(getLine());
                cw.print("enter password:");
                String pass = getLine();
                if (user.getPassword().equals(pass)) {
                    cw.print("you are logged in");
                    userMenu(user);
                    break;
                }
            case 3:
                System.exit(3);
            default:
                cw.print(wrongNumber);
                menu();
                break;
        }
    }

    private void userChoice(int i, User user) {
        switch (i) {
            case 1:
                run(user);
                break;
            case 2:
                if (user.getResultList() != null) {
                    cw.printDoubleList(user.getResultList());
                    userMenu(user);
                    break;
                }
                cw.print("Result list is empty");
                userMenu(user);
                break;
            case 3:
                user = null;
                menu();
                break;
            default:
                cw.print(wrongNumber);
                userMenu(user);
                break;
        }
    }

    private double getOperation(String op, double a, double b, User user) {
        switch (op) {
            case "+":
                storage.save((CalcService.sum(a, b)), user);
                return CalcService.sum(a, b);
            case "-":
                storage.save((CalcService.sub(a, b)), user);
                return CalcService.sub(a, b);
            case "*":
                storage.save((CalcService.multi(a, b)), user);
                return CalcService.multi(a, b);
            case "/":
                storage.save((CalcService.division(a, b)), user);
                return CalcService.division(a, b);
            default:
                cw.print(wrongType);
                return getOperation(getLine(), a, b, user);
        }
    }

    private double getDouble() {
        while (!cr.getSc().hasNextDouble()) {
            cr.getSc().next();
            cw.print(wrongType);
        }
        return cr.getSc().nextDouble();
    }

    private String getLine() {
        return cr.getSc().next();
    }

    private int getInt() {
        while (!cr.getSc().hasNextInt()) {
            cr.getSc().next();
            cw.print(wrongType);
        }
        return cr.getSc().nextInt();
    }
}
