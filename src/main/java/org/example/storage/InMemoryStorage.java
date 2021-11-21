package org.example.storage;

import org.example.entity.User;

import java.util.LinkedList;
import java.util.List;

public class InMemoryStorage {

    private LinkedList<User> usersList = new LinkedList<>();


    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(LinkedList<User> usersList) {
        this.usersList = usersList;
    }

    public void save(Double result, User user) {
        if (user.getResultList() != null) {
            user.getResultList().addFirst(result);
            return;
        }
        user.setResultList(new LinkedList<>());
        user.getResultList().addFirst(result);
    }
}
