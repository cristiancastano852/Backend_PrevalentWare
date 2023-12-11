package com.prevalentWare.backend_PR.repositories.contracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prevalentWare.backend_PR.entities._CountryToUser;

public interface I_CountryToUserRepository extends JpaRepository<_CountryToUser, String> {

}
