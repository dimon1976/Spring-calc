package org.example.entity;

import java.util.LinkedList;

public class User {
    private String name;
    private String password;
    private LinkedList<Double> resultList;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //    @Autowired
    public User() {
    }

    public User(String name, String password, LinkedList<Double> resultList) {
        this.name = name;
        this.password = password;
        this.resultList = resultList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LinkedList<Double> getResultList() {
        return resultList;
    }


    public void setResultList(LinkedList<Double> resultList) {
        this.resultList = resultList;
    }
}
