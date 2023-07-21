package com.example.bookstore.service;


import com.example.bookstore.model.User;
import com.example.bookstore.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void addUser(User newUser) {

        loginRepository.users.add(newUser);
    }

    public void deleteUser(User targetUser) {
        loginRepository.users.remove(targetUser);
    }

    public List<User> getUsers() {

        return loginRepository.users;
    }

    public List<User> getAdmins() {

        return loginRepository.admins;
    }

}
