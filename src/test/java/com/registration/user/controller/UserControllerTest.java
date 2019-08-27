package com.registration.user.controller;

import com.registration.user.domain.User;
import com.registration.user.service.UserServiceImpl;
import com.registration.user.service.exclusion.ExclusionServiceImpl;
import com.registration.utils.BadRequestException;
import com.registration.utils.Validator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private ExclusionServiceImpl exclusionService;


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void registerNewUser() throws Exception{

        User user = new User();
        user.setUsername("janedoe");
        user.setSocialSecurityNumber("111-22-0467");
        user.setPassword("P@55worD");
        user.setDateOfBirth("1994-03-30");

        given(userService.register(user)).willReturn(user);

        mvc.perform(post("/register?username=janedoe&password=P@55worD&dateOfBirth=1994-03-30&socialSecurityNumber=786-45-1245")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void invalidUsernameException() throws Exception {
        User user = new User();
        user.setUsername("jane doe");
        user.setSocialSecurityNumber("111-22-0467");
        user.setPassword("P@55worD");
        user.setDateOfBirth("1994-03-30");

        given(userService.register(user)).willReturn(user);

        mvc.perform(post("/register?username=jane doe&password=P@55worD&dateOfBirth=1994-03-30&socialSecurityNumber=786-45-1245")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void invalidDate() throws Exception{

        User user = new User();
        user.setUsername("janedoe");
        user.setSocialSecurityNumber("11122467");
        user.setPassword("P@55worD");
        user.setDateOfBirth("1994");

        given(userService.register(user)).willReturn(user);

        mvc.perform(post("/register?username=jane&password=P@55worD&dateOfBirth=1990&socialSecurityNumber=853850")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validateExclusionList() {
        thrown.expect(BadRequestException.class);
        thrown.expectMessage(Validator.USER_EXCLUDED);

        User user = new User();
        user.setUsername("adaLovelace");
        user.setPassword("Analytical3ngineRulz");
        user.setDateOfBirth("1815-12-10");
        user.setSocialSecurityNumber("85385075");

        Mockito.doReturn(Boolean.FALSE).when(exclusionService).validate((user.getDateOfBirth()),
                (user.getSocialSecurityNumber()));

        userService.register(user);
     }

}
