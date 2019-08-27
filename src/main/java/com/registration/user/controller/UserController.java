package com.registration.user.controller;

import com.registration.user.domain.User;
import com.registration.user.service.UserServiceImpl;
import com.registration.user.service.exclusion.ExclusionServiceImpl;
import com.registration.utils.BadRequestException;
import com.registration.utils.Validator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/*
*
* {
"username": "adaLovelace",
"password": "Analytical3ngineRulz",
"dateOfBirth": "1815-12-10",
"socialSecurityNumber": "85385075"
}
*
* */

@RestController
public class UserController {

    private UserServiceImpl userService;
    private ExclusionServiceImpl exclusionService;

    public UserController(UserServiceImpl userService, ExclusionServiceImpl exclusionService) {
        this.userService = userService;
        this.exclusionService = exclusionService;
    }

    @RequestMapping("/register")
    public User register(@Valid @RequestBody final User user) {

        if(!exclusionService.validate(user.getDateOfBirth(), user.getSocialSecurityNumber())) {
            throw new BadRequestException(Validator.USER_EXCLUDED);
        }

        if(userService.validateUserExists(user)) {
            throw new BadRequestException(Validator.USER_ALREADY_REGISTERED);
        }

        return userService.register(user);
    }
}
