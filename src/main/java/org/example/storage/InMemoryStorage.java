package org.example.storage;

import org.example.entity.User;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class InMemoryStorage {
    private LinkedList<User> usersList;

    public InMemoryStorage(LinkedList<User> usersList) {
        this.usersList = usersList;
    }

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
