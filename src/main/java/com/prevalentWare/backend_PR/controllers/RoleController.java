package com.prevalentWare.backend_PR.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.prevalentWare.backend_PR.entities.Role;
import com.prevalentWare.backend_PR.services.contracts.IRoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/lists")
    private ResponseEntity<?> getAllRoles(
        @RequestHeader("Authorization") String token,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size
    ) {
        try {
            ResponseEntity<List<Role>> rolesResponse = this.roleService.findAllPaginated(page, size);
            if (rolesResponse.getStatusCode().is2xxSuccessful()) {
                return rolesResponse;
            } else {
                return ResponseEntity.status(rolesResponse.getStatusCode()).body("Failed to retrieve roles list");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        } 
    }
}
