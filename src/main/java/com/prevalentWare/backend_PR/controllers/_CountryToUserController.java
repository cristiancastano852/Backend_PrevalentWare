package com.prevalentWare.backend_PR.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prevalentWare.backend_PR.entities._CountryToUser;
import com.prevalentWare.backend_PR.services.contracts.I_CountryToUserService;

@RestController
@RequestMapping("CountryToUser")
public class _CountryToUserController {
    
    @Autowired 
    private I_CountryToUserService _countryToUserService;

    @GetMapping("/lists")
    private ResponseEntity<List<_CountryToUser>> getAll_CountryToUsers() {
        return this._countryToUserService.findAll();
    }
}
