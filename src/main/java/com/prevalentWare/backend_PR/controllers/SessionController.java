package com.prevalentWare.backend_PR.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prevalentWare.backend_PR.entities.Session;
import com.prevalentWare.backend_PR.services.contracts.ISessionService;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private ISessionService sessionService;

    @GetMapping("/lists")
    private ResponseEntity<List<Session>> getAllSessions() {
        return this.sessionService.findAll();
    }
}
