package org.example.service;

import org.example.entity.User;
import org.example.storage.InMemoryStorage;

import java.util.List;

public class UserService {

    private InMemoryStorage storage = new InMemoryStorage();

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
