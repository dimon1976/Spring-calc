package org.example.service;

import org.example.console.ConsoleReader;
import org.example.console.ConsoleWriter;
import org.example.entity.User;
import org.example.storage.InMemoryStorage;
import org.springframework.stereotype.Component;


@Component
public class Application {
    InMemoryStorage storage;
    private UserService userService;
    private final ConsoleReader cr;
    private final ConsoleWriter cw;
    private static final String NUM = "Input number:";
    private static final String OPERATION = "Enter operation +, -, *, /  e - Exit";
    private static final String WRONGTYPE = "You entered the wrong type!";
    private static final String WRONGNUMBER = "Wrong number!";
    private static final String ENTERLOGIN = "Enter login:";
    private static final String ENTERPASSWORD = "Enter password:";


//    public Application(ConsoleReader cr, ConsoleWriter cw) {
//        this.cr = cr;
//        this.cw = cw;
//    }


    public Application(InMemoryStorage storage, UserService userService, ConsoleReader cr, ConsoleWriter cw) {
        this.storage = storage;
        this.userService = userService;
        this.cr = cr;
        this.cw = cw;
    }

    public void menu(User user) {
        if (user != null) {
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
        cw.print("1) - Register");
        cw.print("2) - Authorization");
        cw.print("3) - Exit");
        int i = getInt();
        getChoice(i);
    }

    public void run(User user) {
        while (true) {
            cw.print(OPERATION);
            String op = getLine();
            if (op.equals("e")) {
                cw.print("Exit to menu");
                menu(user);
                break;
            }
            cw.print(NUM);
            double a = getDouble();
            cw.print(NUM);
            double b = getDouble();
            cr.getSc().nextLine();
            double result = getOperation(op, a, b, user);
            cw.print(result);
        }
    }

    private void getChoice(int i) {
        switch (i) {
            case 1:
                cw.print(ENTERLOGIN);
                String login = getLine();
                if (userService.findByName(login)) {
                    cw.print("User already exist");
                    menu(null);
                    break;
                } else {
                    cw.print(ENTERPASSWORD);
                    String password = getLine();
                    userService.saveUser(login, password);
                    getChoice(2);
                }
                break;
            case 2:
                cw.print("Authorization - enter login:");
                User user = userService.getUser(getLine());
                cw.print(ENTERPASSWORD);
                String pass = getLine();
                if (user != null) {
                    if (user.getPassword().equals(pass)) {
                        cw.print("you are logged in");
                        menu(user);
                        break;
                    }
                }
                cw.print("User not found.");
                menu(null);
                break;
            case 3:
                System.exit(3);
            default:
                cw.print(WRONGNUMBER);
                menu(null);
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
                   menu(user);
                    break;
                }
                cw.print("Result list is empty");
                menu(user);
                break;
            case 3:
                user = null;
                menu(user);
                break;
            default:
                cw.print(WRONGNUMBER);
                menu(user);
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
                cw.print(WRONGTYPE);
                return getOperation(getLine(), a, b, user);
        }
    }

    private double getDouble() {
        while (!cr.getSc().hasNextDouble()) {
            cr.getSc().next();
            cw.print(WRONGTYPE);
        }
        return cr.getSc().nextDouble();
    }

    private String getLine() {
        return cr.getSc().next();
    }

    private int getInt() {
        while (!cr.getSc().hasNextInt()) {
            cr.getSc().next();
            cw.print(WRONGTYPE);
        }
        return cr.getSc().nextInt();
    }
}
