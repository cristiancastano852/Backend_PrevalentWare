package com.prevalentWare.backend_PR.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prevalentWare.backend_PR.entities.UserMonitoring;
import com.prevalentWare.backend_PR.services.contracts.IUserMonitoringService;

@RestController
@RequestMapping("/monitoring")
public class UserMonitoringController {
    @Autowired
    private IUserMonitoringService userMonitoringService;

    @GetMapping("/lists")
    private ResponseEntity<List<UserMonitoring>> getAllUserMonitoring() {
        return this.userMonitoringService.findAll();
    }

}
