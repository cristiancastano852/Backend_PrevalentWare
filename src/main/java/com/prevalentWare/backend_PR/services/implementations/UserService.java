package com.prevalentWare.backend_PR.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
            List<User> users = this.userRepository.findAll();
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<User>> findAllPaginated(Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<User> users = this.userRepository.findAll(pageable).getContent();
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<User> findById(String id) {
        try {
            User user = this.userRepository.findById(id).orElse(null);
            if (user == null) {
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<User> findByEmail(String email) {
        try {
            User user = this.userRepository.findByEmail(email);
            if (user == null) {
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
