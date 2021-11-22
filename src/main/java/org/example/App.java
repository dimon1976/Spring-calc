package org.example;


//xml
//annotation
//java base

//constructor
//setter
//field

import org.example.config.RootConfiguration;
import org.example.service.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(RootConfiguration.class);
        Application application = (Application) ac.getBean("application");
        application.menu(null);
    }
}