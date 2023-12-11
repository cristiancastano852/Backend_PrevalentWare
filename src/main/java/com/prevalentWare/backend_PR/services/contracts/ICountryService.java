package com.prevalentWare.backend_PR.services.contracts;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.prevalentWare.backend_PR.entities.Country;

public interface ICountryService {
    public ResponseEntity<List<Country>> findAll();
    ResponseEntity<List<Country>> findAllPaginated(Integer page, Integer size);
}
