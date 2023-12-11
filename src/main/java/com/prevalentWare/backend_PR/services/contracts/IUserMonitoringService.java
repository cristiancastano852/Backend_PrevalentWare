package com.prevalentWare.backend_PR.services.contracts;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.prevalentWare.backend_PR.entities.UserMonitoring;
import com.prevalentWare.backend_PR.entities.UserWithRecordCount;

public interface IUserMonitoringService {
    public ResponseEntity<List<UserMonitoring>> findAll();
    ResponseEntity<List<UserMonitoring>> findAllPaginated(Integer page, Integer size);
    public ResponseEntity<List<UserMonitoring>> findByUserEmailAndDateRange(String userEmail, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    List<UserWithRecordCount> findTopThreeUsersWithMostRecordsInDateRange(
        LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    //findTopUsersByUsageTypeAndCountryAndDateRange
    // public List<Object[]> findTopUsersByUsageTypeAndCountryAndDateRange(
    //         String usageType, String countryId, LocalDateTime startDate, LocalDateTime endDate);
    //findByDescriptionAndUser_Country_IdAndCreatedAtBetween
    // public ResponseEntity<List<UserMonitoring>> findByDescriptionAndUser_Country_IdAndCreatedAtBetween(
    //         String description, String countryId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    public ResponseEntity<List<UserMonitoring>> findAllPaginated(Pageable pageable);

}   
