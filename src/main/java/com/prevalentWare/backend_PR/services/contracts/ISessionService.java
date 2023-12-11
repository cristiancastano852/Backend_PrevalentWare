package com.prevalentWare.backend_PR.services.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prevalentWare.backend_PR.entities.Session;

public interface ISessionService {
    public ResponseEntity<List<Session>> findAll();
    public ResponseEntity<Session> findBySessionToken(String token);
    public ResponseEntity<List<Session>> findAllPaginated(Integer page, Integer size);
    
}
