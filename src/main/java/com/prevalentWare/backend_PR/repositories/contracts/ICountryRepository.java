package com.prevalentWare.backend_PR.repositories.contracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prevalentWare.backend_PR.entities.Country;

public interface ICountryRepository  extends JpaRepository<Country, String>{
    
}
