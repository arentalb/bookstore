package com.example.bookstore.controller;

import com.example.bookstore.model.User;
import com.example.bookstore.scopeServices.LoggendinAdminService;
import com.example.bookstore.scopeServices.LoggendinUserService;
import com.example.bookstore.scopeServices.LogginProccess;
import com.example.bookstore.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {


    private final LoginService loginService;
    private final LogginProccess logginProccess;
    private final LoggendinUserService loggendinUserService;
    private final LoggendinAdminService loggendinAdminService;

    public LoginController(LoginService loginService,
                           LogginProccess logginProccess,
                           LoggendinUserService loggendinUserService,
                           LoggendinAdminService loggendinAdminService) {
        this.loginService = loginService;
        this.logginProccess = logginProccess;
        this.loggendinUserService = loggendinUserService;
        this.loggendinAdminService = loggendinAdminService;

    }

    @GetMapping("/logout")
    public String getlogOut(@RequestParam(required = false) String logout) {

        String message ;
        if (logout != null) {
            loggendinUserService.setName(null);
            loggendinAdminService.setName(null);
            message = "you logged out ";
        }else {
            message = "no one logend in  ";
        }

        return message ;

    }



    @PostMapping("/signup")
    public ResponseEntity<String> postSignUp(@RequestBody User user) {

        List<User> a = loginService.getUsers();
        List<User> b = loginService.getAdmins();


        boolean regiodterd = false;
        for (User savedUsers : b) {

            if (savedUsers.getUsername().equals(user.getUsername())) {
                regiodterd = true;
                break;
            }
        }
        for (User savedUsers : a) {

            if (savedUsers.getUsername().equals(user.getUsername())) {
                regiodterd = true;
                break;
            }
        }


        String message  ;
        if (regiodterd) {
            message = "you already registered login instead " ;


        } else {
            loginService.addUser(user);
            message = "you just registered login >" ;
        }
        return ResponseEntity.status(200).body(message);
    }



    @PostMapping("/login")
    public ResponseEntity<String> postlogib(@RequestBody User user  ) {


        boolean regiodterd = logginProccess.login(user);
        String message ;
        if (regiodterd) {
            message = "login accepted ";
            return ResponseEntity.status(200).body(message);

        } else {
            message = "wrong password ";
            return ResponseEntity.status(400).body(message);

        }

    }



}
