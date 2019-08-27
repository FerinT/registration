package com.registration.user.service;

import com.registration.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static Map<Integer, User> userRepo = new HashMap<>();


    @Override
    public User register(User user) {
        return cacheUser(user);
    }


    // add user to cache
    private User cacheUser(User user) {
        userRepo.put(user.hashCode(), user);

        return getUser(user);
    }

    private User getUser(User user){
        User sanitizedUser = userRepo.get(user.hashCode());

        sanitizedUser.setPassword(null);
        sanitizedUser.setSocialSecurityNumber(null);

        return sanitizedUser;
    }

    @Override
    public boolean validateUserExists(User user) {
        return userRepo.containsKey(user.hashCode());
    }
}
