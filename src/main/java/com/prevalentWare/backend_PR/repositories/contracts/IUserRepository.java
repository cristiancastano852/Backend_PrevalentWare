package com.prevalentWare.backend_PR.repositories.contracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prevalentWare.backend_PR.entities.User;

public interface IUserRepository extends JpaRepository<User, String> {

    
}
