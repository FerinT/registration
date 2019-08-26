package com.registration.user.service;

import com.registration.user.domain.User;
import com.registration.utils.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static Map<Integer, User> userRepo = new HashMap<>();
    private ExclusionServiceImpl exclusionService;

    public UserServiceImpl(ExclusionServiceImpl exclusionService) {
        this.exclusionService = exclusionService;
    }

    @Override
    public User register(User user) {
        return cacheUser(user);
    }


    // add user to cache
    private User cacheUser(User user) {
        if(validateUserExists(user)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, Validator.USER_ALREADY_REGISTERED);
        }

        if(!exclusionService.validate(user.getDateOfBirth(), user.getSocialSecurityNumber())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, Validator.USER_EXCLUDED);
        }


        userRepo.put(user.hashCode(), user);

        return userRepo.get(user.hashCode());
    }

    private boolean validateUserExists(User user) {
        return userRepo.containsKey(user.hashCode());
    }
}
