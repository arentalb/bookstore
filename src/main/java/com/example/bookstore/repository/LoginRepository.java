package com.example.bookstore.repository;

import com.example.bookstore.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LoginRepository {

    public List<User> users;
    public List<User> admins;

    public LoginRepository() {

        users = new ArrayList<>();
        admins = new ArrayList<>();
        User mainAdmin = new User();
        mainAdmin.setUsername("aren");
        mainAdmin.setPassword("12345");
        admins.add(mainAdmin);
    }
}
