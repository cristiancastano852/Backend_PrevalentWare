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
import com.prevalentWare.backend_PR.entities._CountryToUser;
import com.prevalentWare.backend_PR.services.contracts.I_CountryToUserService;

@RestController
@RequestMapping("CountryToUser")
public class _CountryToUserController {
    
    @Autowired 
    private I_CountryToUserService _countryToUserService;
    
    @Autowired
    private AuthorizationUtil authorizationUtil;

    @GetMapping("/lists")
    private ResponseEntity<?> getAll_CountryToUsers(
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
                ResponseEntity<List<_CountryToUser>> _countryToUsersResponse = this._countryToUserService.findAllPaginated(page, size);
                if (_countryToUsersResponse.getStatusCode().is2xxSuccessful()) {
                    return _countryToUsersResponse;
                } else {
                    return ResponseEntity.status(_countryToUsersResponse.getStatusCode()).body("Failed to retrieve _countryToUsers list");
                }
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Internal Server Error");
            } 
    }
}
