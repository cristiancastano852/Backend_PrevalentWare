package com.prevalentWare.backend_PR.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prevalentWare.backend_PR.entities.Role;
import com.prevalentWare.backend_PR.repositories.contracts.IRoleRepository;
import com.prevalentWare.backend_PR.services.contracts.IRoleService;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public ResponseEntity<List<Role>> findAll() {
        try {
            List<Role> roles = this.roleRepository.findAll();
            return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new  ResponseEntity<List<Role>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @Override
    public ResponseEntity<List<Role>> findAllPaginated(Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Role> rolesPage = roleRepository.findAll(pageable);
            List<Role> roles = rolesPage.getContent();
            return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new  ResponseEntity<List<Role>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
