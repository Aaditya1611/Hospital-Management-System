package com.example.auth.api.controller;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.auth.api.signup.User;
import com.example.auth.api.signup.type.RoleType;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUser() throws Exception {
        User user = User.builder()
                .username("icecream")
                .password("test")
                .email("icecream@gmail.com")
                .roles(Set.of(RoleType.PATIENT))
                .build();

        mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("icecream"))
                .andExpect(jsonPath("$.password").value("password"))
                .andExpect(jsonPath("$.roles").value("roles"))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testCreateUser_WithoutPassword() throws Exception {

        User user = User.builder()
            .username("samosa")
            .email("samosa@gmail.com")
            .roles(Set.of(RoleType.PATIENT))
            .build();

        mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}
