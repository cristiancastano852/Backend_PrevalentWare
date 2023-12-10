package com.prevalentWare.backend_PR.services.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prevalentWare.backend_PR.entities.Session;

public interface ISessionService {
    // public ResponseEntity<String> login(String username, String password);
    // public ResponseEntity<String> logout();
    public ResponseEntity<List<Session>> findAll();
    //findByToken()
    public ResponseEntity<Session> findBySessionToken(String token);
    // ResponseEntity<List<Session>> findAllPaginated(Integer page, Integer size);
    
}
