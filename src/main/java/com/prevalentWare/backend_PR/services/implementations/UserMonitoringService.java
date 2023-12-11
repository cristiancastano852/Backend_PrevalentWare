package com.prevalentWare.backend_PR.services.implementations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.prevalentWare.backend_PR.entities.User;
import com.prevalentWare.backend_PR.entities.UserMonitoring;
import com.prevalentWare.backend_PR.entities.UserWithRecordCount;
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
            return new ResponseEntity<List<UserMonitoring>>(userMonitorings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<UserMonitoring>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<UserMonitoring>> findAllPaginated(Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<UserMonitoring> userMonitoringsPage = userMonitoringRepository.findAll(pageable);
            List<UserMonitoring> userMonitorings = userMonitoringsPage.getContent();
            return new ResponseEntity<>(userMonitorings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<UserMonitoring>> findByUserEmailAndDateRange(String userEmail, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        try {
            List<UserMonitoring> userMonitorings = userMonitoringRepository.findByUser_EmailAndCreatedAtBetween(userEmail, startDate, endDate, pageable);
            return new ResponseEntity<>(userMonitorings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<UserWithRecordCount> findTopThreeUsersWithMostRecordsInDateRange(
        LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {

        List<Object[]> topUsersData = userMonitoringRepository.findTopThreeUsersByRecordCount(startDate, endDate, pageable);
        
        List<UserWithRecordCount> topUsers = new ArrayList<>();

        for (Object[] userData : topUsersData) {
            User user = (User) userData[0];
            Long recordCount = (Long) userData[1];

            topUsers.add(new UserWithRecordCount(user, recordCount));
        }

        return topUsers;
    }

    @Override
    public ResponseEntity<List<UserMonitoring>> findAllPaginated(Pageable pageable) {
        try {
            Page<UserMonitoring> userMonitoringsPage = userMonitoringRepository.findAll(pageable);
            List<UserMonitoring> userMonitorings = userMonitoringsPage.getContent();
            return new ResponseEntity<>(userMonitorings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @Override
    // public List<Object[]> findTopUsersByUsageTypeAndCountryAndDateRange(
    //         String usageType, String countryId, LocalDateTime startDate, LocalDateTime endDate) {
    //     List<Object[]> topUsers = userMonitoringRepository.findTopUsersByUsageTypeAndCountryAndDateRange(usageType, countryId, startDate, endDate);
    //     return topUsers;
    // }

    // @Override
    // public ResponseEntity<List<UserMonitoring>> findByDescriptionAndUser_Country_IdAndCreatedAtBetween(
    //         String description, String countryId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
    //     try {
    //         List<UserMonitoring> userMonitorings = userMonitoringRepository.findByDescriptionAndUser_Country_IdAndCreatedAtBetween(description, countryId, startDate, endDate, pageable);
    //         return new ResponseEntity<>(userMonitorings, HttpStatus.OK);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

}
