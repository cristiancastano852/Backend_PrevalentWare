package com.prevalentWare.backend_PR.services.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prevalentWare.backend_PR.entities._CountryToUser;

public interface I_CountryToUserService {
    public ResponseEntity<List<_CountryToUser>> findAll();
    ResponseEntity<List<_CountryToUser>> findAllPaginated(Integer page, Integer size);
}
