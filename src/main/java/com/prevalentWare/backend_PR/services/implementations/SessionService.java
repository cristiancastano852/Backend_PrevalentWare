package com.prevalentWare.backend_PR.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.prevalentWare.backend_PR.entities.Session;
import com.prevalentWare.backend_PR.repositories.contracts.ISessionRepository;
import com.prevalentWare.backend_PR.services.contracts.ISessionService;

@Service
public class SessionService implements ISessionService{
    @Autowired
    private ISessionRepository sessionRepository;

    // @Override
    // public ResponseEntity<String> login(String username, String password) {
    //     try {
    //         List<Session> sessions = this.sessionRepository.findAll();
    //         return ResponseEntity.ok("Login successful");
    //     } catch (Exception e) {
    //         return ResponseEntity.internalServerError().build();
    //     }

    // }

    // @Override
    // public ResponseEntity<String> logout() {
    //     try {
    //         List<Session> sessions = this.sessionRepository.findAll();
    //         return ResponseEntity.ok("Logout successful");
    //     } catch (Exception e) {
    //         return ResponseEntity.internalServerError().build();
    //     }

    // }

    @Override
    public ResponseEntity<List<Session>> findAll() {
        try {
            List<Session> sessions = this.sessionRepository.findAll();
            return new ResponseEntity<List<Session>>(sessions, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @Override
    public ResponseEntity<Session> findBySessionToken(String token) {
        try {
            Session session = this.sessionRepository.findBySessionToken(token);
            return new ResponseEntity<Session>(session, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
