package com.prevalentWare.backend_PR.services.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prevalentWare.backend_PR.entities.User;

public interface IUserService {

    public ResponseEntity<List<User>> findAll();
    
}
