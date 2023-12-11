package com.prevalentWare.backend_PR.repositories.contracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prevalentWare.backend_PR.entities.Session;

public interface ISessionRepository extends JpaRepository<Session, String>{
    Session findBySessionToken(String sessionToken);
}
