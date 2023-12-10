package com.prevalentWare.backend_PR.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.prevalentWare.backend_PR.entities.User;
import com.prevalentWare.backend_PR.entities._CountryToUser;
import com.prevalentWare.backend_PR.repositories.contracts.I_CountryToUserRepository;
import com.prevalentWare.backend_PR.services.contracts.I_CountryToUserService;

@Service
public class _CountryToUserService implements I_CountryToUserService {
    @Autowired
    private I_CountryToUserRepository _countryToUserRepository;

    @Override
    public ResponseEntity<List<_CountryToUser>> findAll() {
        try {
            List<_CountryToUser> _countryToUsers = this._countryToUserRepository.findAll();
            return new ResponseEntity<List<_CountryToUser>>(_countryToUsers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<_CountryToUser>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<_CountryToUser>> findAllPaginated(Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<_CountryToUser> _countryToUsers = this._countryToUserRepository.findAll(pageable);
            return new ResponseEntity<List<_CountryToUser>>(_countryToUsers.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<_CountryToUser>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
