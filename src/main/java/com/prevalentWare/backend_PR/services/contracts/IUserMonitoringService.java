package com.prevalentWare.backend_PR.services.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prevalentWare.backend_PR.entities.UserMonitoring;

public interface IUserMonitoringService {
    public ResponseEntity<List<UserMonitoring>> findAll();
    ResponseEntity<List<UserMonitoring>> findAllPaginated(Integer page, Integer size);
    
}
