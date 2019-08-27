package com.registration.user.service.exclusion;

import com.registration.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExclusionServiceImpl implements ExclusionService {


    private final List<User> excludedUsers = new ArrayList<>();
    private boolean isvalid;

    public ExclusionServiceImpl() {
        populateExclusionList();
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

    private void populateExclusionList() {

        User user = new User();
        user.setUsername("adaLovelace");
        user.setPassword("Analytical3ngineRulz");
        user.setDateOfBirth("1815-12-10");
        user.setSocialSecurityNumber("85385075");

        User user1 = new User();
        user1.setUsername("alanTuring");
        user1.setPassword("eniGmA123");
        user1.setDateOfBirth("June 1912-06-23");
        user1.setSocialSecurityNumber("123456789");

        User user2 = new User();
        user2.setUsername("konradZuse");
        user2.setPassword("zeD1");
        user2.setDateOfBirth("June 1910-06-22");
        user2.setSocialSecurityNumber("987654321");

        excludedUsers.add(user);
        excludedUsers.add(user1);
        excludedUsers.add(user2);

    }
}
