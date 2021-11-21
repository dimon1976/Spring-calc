package org.example;


//xml
//annotation
//java base

//constructor
//setter
//field

import org.example.service.Application;

public class App {
    public static void main(String[] args) {
        //        ApplicationContext ac = new AnnotationConfigApplicationContext(RootConfiguration.class);
//        Application application =(Application)ac.getBean("application");
//        application.menu();

        Application application = new Application();
        application.menu(null);
    }
}