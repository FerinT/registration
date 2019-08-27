package com.registration.user.controller;

import com.google.gson.Gson;
import com.registration.user.domain.User;
import com.registration.user.service.UserServiceImpl;
import com.registration.user.service.exclusion.ExclusionServiceImpl;
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
        user.setUsername("adaLovelace");
        user.setSocialSecurityNumber("85385078");
        user.setPassword("Analytical3ngineRulz");
        user.setDateOfBirth("1916-12-10");

        Mockito.doReturn(Boolean.TRUE).when(exclusionService).validate((Mockito.matches(user.getDateOfBirth())),
                (Mockito.matches(user.getSocialSecurityNumber())));
        Mockito.doReturn(Boolean.FALSE).when(userService).validateUserExists((user));

        Gson gson = new Gson();
        String json = gson.toJson(user);

        given(userService.register(user)).willReturn(user);

        mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());
    }

    @Test
    public void invalidUsernameException() throws Exception {
        User user = new User();
        user.setUsername("jane doe");
        user.setSocialSecurityNumber("111-22-0467");
        user.setPassword("P@55worD");
        user.setDateOfBirth("1994-03-30");

        Gson gson = new Gson();
        String json = gson.toJson(user);

        given(userService.register(user)).willReturn(user);

        mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void invalidDate() throws Exception{

        User user = new User();
        user.setUsername("janedoe");
        user.setSocialSecurityNumber("11122467");
        user.setPassword("P@55worD");
        user.setDateOfBirth("1994");

        Gson gson = new Gson();
        String json = gson.toJson(user);

        given(userService.register(user)).willReturn(user);

        mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validateExclusionList() throws Exception{
        User user = new User();
        user.setUsername("adaLovelace");
        user.setPassword("Analytical3ngineRulz");
        user.setDateOfBirth("1815-12-10");
        user.setSocialSecurityNumber("85385075");


        Mockito.doReturn(Boolean.FALSE).when(exclusionService).validate((Mockito.matches(user.getDateOfBirth())),
                (Mockito.matches(user.getSocialSecurityNumber())));
        Mockito.doReturn(Boolean.FALSE).when(userService).validateUserExists((user));

        Gson gson = new Gson();
        String json = gson.toJson(user);

        given(userService.register(user)).willReturn(user);

        mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());
     }

}
