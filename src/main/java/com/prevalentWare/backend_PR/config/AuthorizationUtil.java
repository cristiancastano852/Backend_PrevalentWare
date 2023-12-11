package com.prevalentWare.backend_PR.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.prevalentWare.backend_PR.entities.Role;
import com.prevalentWare.backend_PR.entities.Session;
import com.prevalentWare.backend_PR.entities.User;
import com.prevalentWare.backend_PR.entities.Role.Enum_RoleName;
import com.prevalentWare.backend_PR.repositories.contracts.ISessionRepository;
import com.prevalentWare.backend_PR.repositories.contracts.IUserRepository;

@Component
public class AuthorizationUtil {

    @Autowired
    private ISessionRepository sessionRepository;

    @Autowired
    private IUserRepository userRepository;


    public boolean isAuthorized(String token, String... allowedRoles) {
        Session session = this.sessionRepository.findBySessionToken(token);
        if (session == null) {
            return false;
        }

        User user = this.userRepository.findById(session.getUser().getId()).orElse(null);
        if (user == null) {
            return false;
        }

        Role role = user.getRole();
        if (role == null) {
            return false;
        }

        for (String allowedRole : allowedRoles) {
            if (role.getName() == Enum_RoleName.valueOf(allowedRole)) {
                return true;
            }
        }

        return false; 
    }
}