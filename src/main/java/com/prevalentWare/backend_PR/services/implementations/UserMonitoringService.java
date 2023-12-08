package com.prevalentWare.backend_PR.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prevalentWare.backend_PR.entities.UserMonitoring;
import com.prevalentWare.backend_PR.repositories.contracts.IUserMonitoringRepository;
import com.prevalentWare.backend_PR.services.contracts.IUserMonitoringService;

@Service
public class UserMonitoringService implements IUserMonitoringService{
    @Autowired
    private IUserMonitoringRepository userMonitoringRepository;

    @Override
    public ResponseEntity<List<UserMonitoring>> findAll() {
        try {
            List<UserMonitoring> userMonitorings = this.userMonitoringRepository.findAll();
            System.out.println("userMonitorings: " + userMonitorings);
            return new ResponseEntity<List<UserMonitoring>>(userMonitorings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<UserMonitoring>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
