package org.example.console;

import org.example.entity.User;
import org.example.service.CalcServiceTest;
import org.example.service.UserService;
import org.springframework.stereotype.Component;


@Component
public class Application {
    private final UserService userService;
    private final CalcServiceTest calcService;
    private User user;
    private final ConsoleReader cr;
    private final ConsoleWriter cw;
    private static final String NUM = "Input number:";
    private static final String OPERATION = "Enter operation +, -, *, /  e - Exit";
    private static final String WRONGTYPE = "You entered the wrong type!";
    private static final String WRONGNUMBER = "Wrong number!";
    private static final String ENTERLOGIN = "Enter login:";
    private static final String ENTERPASSWORD = "Enter password:";
    private static final String EXITPROGRAMM = "Exit";
    private static final String USERNOTFOUND = "User not found.";
    private static final String USEREXIST = "User already exist";

    public Application(UserService userService, CalcServiceTest calcService, ConsoleReader cr, ConsoleWriter cw) {
        this.calcService = calcService;
        this.userService = userService;
        this.cr = cr;
        this.cw = cw;
    }

    public void menu() {
        if (user != null) {
            cw.print("1) - Calculator");
            cw.print("2) - History operation");
            cw.print("3) - Logout");
            cw.print("4) - Exit");
            int i = getInt();
            if (i == 4) {
                cw.print(EXITPROGRAMM);
                return;
            }
            userChoice(i);
        }
        cw.print("1) - Register");
        cw.print("2) - Authorization");
        cw.print("3) - Exit");
        int i = getInt();
        getChoice(i);
    }

    private void calculate() {
        while (true) {
            cw.print(OPERATION);
            String op = getLine();
            if (op.equals("e")) {
                cw.print("Exit to menu");
                menu();
                break;
            } else if (getOperation(op)) {
                cw.print(NUM);
                double a = getDouble();
                cw.print(NUM);
                double b = getDouble();
                cr.getSc().nextLine();
                double result = calcService.getOperation(op, a, b, user);
                cw.print(result);
            }
        }
    }

    private void getChoice(int i) {
        switch (i) {
            case 1:
                cw.print(ENTERLOGIN);
                String login = getLine();
                if (userService.findByName(login)) {
                    cw.print(USEREXIST);
                    user = null;
                    menu();
                    break;
                } else {
                    cw.print(ENTERPASSWORD);
                    String password = getLine();
                    userService.saveUser(login, password);
                    getChoice(2);
                }
                break;
            case 2:
                cw.print("Authorization - " + ENTERLOGIN);
                user = userService.getUser(getLine());
                cw.print(ENTERPASSWORD);
                String pass = getLine();
                if (user != null) {
                    if (user.getPassword().equals(pass)) {
                        cw.print("you are logged in");
                        menu();
                        break;
                    }
                }
                cw.print(USERNOTFOUND);
                user = null;
                menu();
                break;
            case 3:
                cw.print(EXITPROGRAMM);
                return;
            default:
                cw.print(WRONGNUMBER);
                user = null;
                menu();
                break;
        }
    }

    private void userChoice(int i) {
        switch (i) {
            case 1:
                calculate();
                break;
            case 2:
                if (user.getResultList() != null) {
                    cw.printDoubleList(user.getResultList());
                    menu();
                    break;
                }
                cw.print("Result list is empty");
                menu();
                break;
            case 3:
                user = null;
                menu();
                break;
            default:
                cw.print(WRONGNUMBER);
                menu();
                break;
        }
    }

    private boolean getOperation(String operation) {
        if (operation.matches("[+-/*]")) {
            return true;
        }
        cw.print("Wrong operation");
        return false;
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
