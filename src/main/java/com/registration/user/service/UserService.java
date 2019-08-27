package com.registration.user.service;

import com.registration.user.domain.User;

interface UserService {

    // register a new user
    User register(User user);

    boolean validateUserExists(User user);

}
