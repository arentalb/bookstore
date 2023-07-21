package com.example.bookstore.scopeServices;

import com.example.bookstore.model.User;
import com.example.bookstore.service.LoginService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LogginProccess {
    private final LoggendinUserService loggendinUserService;
    private final LoggendinAdminService loggendinAdminService;
    private final LoginService loginService;

    public LogginProccess(LoggendinUserService loggendinUserService,
                          LoggendinAdminService loggendinAdminService,
                          LoginService loginService) {
        this.loggendinUserService = loggendinUserService;
        this.loggendinAdminService = loggendinAdminService;
        this.loginService = loginService;
    }

    public boolean login(User requestedUser) {
        String name = requestedUser.getUsername();
        String password = requestedUser.getPassword();

        for (User savedUser : loginService.getAdmins()) {
            if (savedUser.getUsername().equals(name) && savedUser.getPassword().equals(password)) {
                loggendinAdminService.setName(savedUser.getUsername());

                return true;
            }

        }
        for (User savedUser : loginService.getUsers()) {
            if (savedUser.getUsername().equals(name) && savedUser.getPassword().equals(password)) {
                loggendinUserService.setName(savedUser.getUsername());

                return true;
            }

        }
        return false;
    }


}
