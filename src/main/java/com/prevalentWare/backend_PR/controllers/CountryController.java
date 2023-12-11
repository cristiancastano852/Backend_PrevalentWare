package com.prevalentWare.backend_PR.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prevalentWare.backend_PR.config.AuthorizationUtil;
import com.prevalentWare.backend_PR.entities.Country;
import com.prevalentWare.backend_PR.services.contracts.ICountryService;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private ICountryService countryService;

    @Autowired
    private AuthorizationUtil authorizationUtil;

    @GetMapping("/lists") ResponseEntity<?> getAllCountries(
        @RequestHeader("Authorization") String token,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size) {
        try {
            if (token ==null){
                return ResponseEntity.status(401).body("Unauthorized: No token | User is not authorized to access this resource ");
            }
            if (!this.authorizationUtil.isAuthorized(token, "Admin", "Manager")) {
                return ResponseEntity.status(401).body("Unauthorized: User is not authorized to access this resource");
            }
            ResponseEntity<List<Country>> countriesResponse = this.countryService.findAllPaginated(page, size);
            if (countriesResponse.getStatusCode().is2xxSuccessful()) {
                return countriesResponse;
            } else {
                return ResponseEntity.status(countriesResponse.getStatusCode()).body("Failed to retrieve countries list");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
