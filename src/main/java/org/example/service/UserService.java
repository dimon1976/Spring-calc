package org.example.service;

import org.example.entity.User;
import org.example.storage.InMemoryUserStorage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    private InMemoryUserStorage storage;

    public UserService(InMemoryUserStorage storage) {
        this.storage = storage;
    }


    public boolean findByName(String name) {
        List<User> usersList = storage.getUsersList();
        for (User r : usersList) {
            if (r.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    public User getUser(String name) {
        List<User> usersList = storage.getUsersList();
        for (User r : usersList) {
            if (r.getName().equals(name)) {
                return r;
            }
        }
        return null;
    }


    public void saveUser(String login, String password) {
        storage.getUsersList().add(new User(login, password));
    }

}
