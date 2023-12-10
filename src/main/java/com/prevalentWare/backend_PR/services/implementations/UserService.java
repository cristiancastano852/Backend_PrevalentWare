package com.prevalentWare.backend_PR.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prevalentWare.backend_PR.entities.User;
import com.prevalentWare.backend_PR.repositories.contracts.IUserRepository;
import com.prevalentWare.backend_PR.services.contracts.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Override
    public ResponseEntity<List<User>> findAll() {
        try {
            System.out.println("asdasdasdasdasdasddddddddd");
            List<User> users = this.userRepository.findAll();
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
