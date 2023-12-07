package com.prevalentWare.backend_PR.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prevalentWare.backend_PR.entities.Role;
import com.prevalentWare.backend_PR.services.contracts.IRoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/lists")
    private ResponseEntity<List<Role>> getAllRoles() {
        return this.roleService.findAll();
    }
}
