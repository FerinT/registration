package com.registration.user.service;

import com.registration.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExclusionServiceImpl implements ExclusionService {


    private final List<User> excludedUsers = new ArrayList<>();
    private boolean isvalid;

    public ExclusionServiceImpl() {
        User user = new User();
        user.setUsername("adaLovelace");
        user.setPassword("Analytical3ngineRulz");
        user.setDateOfBirth("10th December 1815");
        user.setSocialSecurityNumber("85385075");

        excludedUsers.add(user);
    }

    @Override
    public boolean validate(String dateOfBirth, String ssn) {

        isvalid = true;

        if(dateOfBirth == null || ssn == null){
            return false;
        }

        excludedUsers.forEach((User user) -> {
            if(user.getDateOfBirth().equals(dateOfBirth) &&
                    user.getSocialSecurityNumber().equals(ssn)) {
                isvalid = false;
            }
        });

        return isvalid;

    }
}
