package com.registration.user.controller;

import com.registration.user.domain.User;
import com.registration.user.service.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8080/register?username=ferin&password=Abc123mm&dateOfBirth=1988-02-03&socialSecurityNumber=1234567898777
// http://localhost:8080/register?username=adaLovelace&password=Analytical3ngineRulz&dateOfBirth=10th%20December%201815&socialSecurityNumber=85385075

@RestController
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public User register(@RequestParam(value="username") String username,
                         @RequestParam(value="password") String password,
                         @RequestParam(value="dateOfBirth") String dateOfBirth,
                         @RequestParam(value="socialSecurityNumber") String socialSecurityNumber) {

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setDateOfBirth(dateOfBirth);
        user.setSocialSecurityNumber(socialSecurityNumber);

        return userService.register(user);
    }
}
