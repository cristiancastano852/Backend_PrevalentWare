package com.prevalentWare.backend_PR.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prevalentWare.backend_PR.config.AuthorizationUtil;
import com.prevalentWare.backend_PR.entities.User;
import com.prevalentWare.backend_PR.services.contracts.IUserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthorizationUtil authorizationUtil;

    @Autowired
    private IUserService userService;

    @GetMapping("/lists")
    private ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token) {
        if (!this.authorizationUtil.isAuthorized(token, "Admin", "Manager")) {
            return ResponseEntity.status(401).build();
        }else if (!this.authorizationUtil.isAuthorized(token, "User")) {
            return this.userService.findAll();
        }
        return this.userService.findAll();
    }
    
}
