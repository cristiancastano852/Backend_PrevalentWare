package com.prevalentWare.backend_PR.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prevalentWare.backend_PR.config.AuthorizationUtil;
import com.prevalentWare.backend_PR.entities.User;
import com.prevalentWare.backend_PR.services.contracts.ISessionService;
import com.prevalentWare.backend_PR.services.contracts.IUserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthorizationUtil authorizationUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISessionService sessionService;

    //return all users if admin or manager, return only the user if user
    @GetMapping("/lists")
    private ResponseEntity<?> getAllUsers(
        @RequestHeader("Authorization") String token,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size
        ) {
        try {
            if (!this.authorizationUtil.isAuthorized(token, "Admin", "Manager")) {
                if (this.authorizationUtil.isAuthorized(token, "User")) {
                    String userId = this.sessionService.findBySessionToken(token).getBody().getUser().getId();
                    User user = this.userService.findById(userId).getBody();
                    return ResponseEntity.ok(user);
                }
                return ResponseEntity.status(401).body("Unauthorized: User is not authorized to access this resource");
            }
            ResponseEntity<List<User>> usersResponse = this.userService.findAllPaginated(page, size);
            if (usersResponse.getStatusCode().is2xxSuccessful()) {
                return usersResponse;
            } else {
                return ResponseEntity.status(usersResponse.getStatusCode()).body("Failed to retrieve users list");
            }
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    //Search user by Email if admin or manager, return only the user if user
    @GetMapping("/search")
    private ResponseEntity<?> searchUser(
        @RequestHeader("Authorization") String token,
        @RequestHeader("email") String email) {
        if (!this.authorizationUtil.isAuthorized(token, "Admin", "Manager")) {
            if (this.authorizationUtil.isAuthorized(token, "User")) {
                String userId = this.sessionService.findBySessionToken(token).getBody().getUser().getId();
                User user = this.userService.findById(userId).getBody();
                if (user.getEmail().equals(email)) {
                    return ResponseEntity.ok(user);
                }
                return ResponseEntity.status(401).body("Unauthorized: User not allowed to access this resource.");
            }
            return ResponseEntity.status(401).body("Unauthorized: User not recognized.");
        }
        ResponseEntity<User> userResponse = this.userService.findByEmail(email);
        if (userResponse.getStatusCode().is2xxSuccessful()) {
            return userResponse;
        }
        return ResponseEntity.status(userResponse.getStatusCode()).body("User not found.");
    }

   
    
}
