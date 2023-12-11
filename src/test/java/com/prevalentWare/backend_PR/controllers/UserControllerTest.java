package com.prevalentWare.backend_PR.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.prevalentWare.backend_PR.config.AuthorizationUtil;
import com.prevalentWare.backend_PR.entities.User;
import com.prevalentWare.backend_PR.services.contracts.ISessionService;
import com.prevalentWare.backend_PR.services.contracts.IUserService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @MockBean
    private ISessionService sessionService;

    @MockBean
    private AuthorizationUtil authorizationUtil;

    @Test
    public void testGetAllUsers() throws Exception {
        
        when(authorizationUtil.isAuthorized(anyString(), any())).thenReturn(true);
        when(userService.findAllPaginated(anyInt(), anyInt())).thenReturn(ResponseEntity.ok(Collections.emptyList()));

        mockMvc.perform(get("/user/lists")
                .header("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJQcmV2YWxlbnR3YXJlIiwiaWF0IjoxNjkyOTEwNDcwLCJleHAiOjE3MjQ0NDY0NzAsImF1ZCI6Ind3dy5wcmV2YWxlbnR3YXJlLmNvbSIsInN1YiI6ImpvaG4ubGVlQHRlc3QuY29tIiwiR2l2ZW5OYW1lIjoiSm9obiIsIlN1cm5hbWUiOiJMZWUiLCJFbWFpbCI6ImpvaG4ubGVlQHRlc3QuY29tIiwiUm9sZSI6ImNsbHBuMWg0ZTAwMDEzODdlY290amp0ZDkifQ.KlqtsePojPiQsx3mM3zi_jv-wCcboSLKkbVNGsfgVkA")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testSearchUser() throws Exception {
        // Simulación de la llamada al endpoint "/user/search"
        when(authorizationUtil.isAuthorized(anyString(), any())).thenReturn(true);
        when(userService.findByEmail(anyString())).thenReturn(ResponseEntity.ok(new User()));

        mockMvc.perform(get("/user/search")
                .header("Authorization", "")
                .header("email", "john.hernandez@test.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}


