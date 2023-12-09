package com.prevalentWare.backend_PR.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prevalentWare.backend_PR.entities.Country;
import com.prevalentWare.backend_PR.services.contracts.ICountryService;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private ICountryService countryService;

    @GetMapping("/lists")
    private ResponseEntity<List<Country>> getAllCountries() {
        return this.countryService.findAll();
    }
}
