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
        loginRepository.addUser(newUser);
    }

    public void deleteUser(User targetUser){
        loginRepository.deleteUser(targetUser);

    }

    public List<User> getUsers() {
        return  loginRepository.getUsers();
    }

    public List<User> getAdmins() {
      return   loginRepository.getAdmins();
    }

}
