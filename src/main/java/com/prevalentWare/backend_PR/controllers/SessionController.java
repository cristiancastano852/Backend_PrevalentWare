package com.prevalentWare.backend_PR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.prevalentWare.backend_PR.config.AuthorizationUtil;
import com.prevalentWare.backend_PR.services.contracts.ISessionService;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private ISessionService sessionService;

    @Autowired
    private AuthorizationUtil authorizationUtil;
    @GetMapping("/lists")
    private ResponseEntity<?> getAllSessions(
        @RequestHeader("Authorization") String token,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size
    ) {
       try {
        if (token ==null || token ==""){
            return ResponseEntity.status(401).body("Unauthorized: No token | User is not authorized to access this resource");
        }
        if (!this.authorizationUtil.isAuthorized(token, "Admin")) {
            return ResponseEntity.status(401).body("Unauthorized: User is not authorized to access this resource");
        }
        return this.sessionService.findAllPaginated(page, size);

       } catch (Exception e) {
              return ResponseEntity.status(500).body("Internal Server Error");
       }
    }
}
