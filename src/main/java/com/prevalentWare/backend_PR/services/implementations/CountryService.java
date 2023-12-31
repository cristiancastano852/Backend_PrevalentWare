package com.prevalentWare.backend_PR.services.implementations;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.prevalentWare.backend_PR.entities.Country;
import com.prevalentWare.backend_PR.repositories.contracts.ICountryRepository;
import com.prevalentWare.backend_PR.services.contracts.ICountryService;

@Service
public class CountryService implements ICountryService{
    @Autowired
    private ICountryRepository countryRepository;

    @Override
    public ResponseEntity<List<Country>> findAll() {
        try {
            List<Country> countries = this.countryRepository.findAll();
            return ResponseEntity.ok(countries);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @Override
    public ResponseEntity<List<Country>> findAllPaginated(Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<Country> countries = this.countryRepository.findAll(pageable).getContent();
            return ResponseEntity.ok(countries);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
