package com.prevalentWare.backend_PR.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prevalentWare.backend_PR.config.AuthorizationUtil;
import com.prevalentWare.backend_PR.entities.User;
import com.prevalentWare.backend_PR.entities.UserMonitoring;
import com.prevalentWare.backend_PR.entities.UserWithRecordCount;
import com.prevalentWare.backend_PR.services.contracts.ISessionService;
import com.prevalentWare.backend_PR.services.contracts.IUserMonitoringService;
import com.prevalentWare.backend_PR.services.contracts.IUserService;

@RestController
@RequestMapping("/monitoring")
public class UserMonitoringController {
    @Autowired
    private IUserMonitoringService userMonitoringService;

    @Autowired
    private AuthorizationUtil authorizationUtil;

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    private ResponseEntity<?> getAllUserMonitoring(
        @RequestHeader("Authorization") String token,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size
    ) {
        try {
            if (token ==null){
                return ResponseEntity.status(401).body(null);
            }
            if (!this.authorizationUtil.isAuthorized(token, "Admin")) {
                return ResponseEntity.status(401).body(null);
            }
            Pageable pageable = PageRequest.of(page, size);
            ResponseEntity<List<UserMonitoring>> userMonitoringPage = this.userMonitoringService.findAllPaginated(pageable);
            if (userMonitoringPage.getStatusCode().is2xxSuccessful()) {
                return userMonitoringPage;
            } else {
                return ResponseEntity.status(userMonitoringPage.getStatusCode()).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
        
    }

    @GetMapping("/user")
    private ResponseEntity<?> getUserMonitoringByUserEmailAndDateRange(
        @RequestHeader("Authorization") String token,
        @RequestParam String email,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            if(!this.authorizationUtil.isAuthorized(token, "Admin")){
                if (this.authorizationUtil.isAuthorized(token, "User")) {
                    String userId = this.sessionService.findBySessionToken(token).getBody().getUser().getId();
                    User user = this.userService.findById(userId).getBody();
                    if (user.getEmail().equals(email)) {
                        ResponseEntity<List<UserMonitoring>> userMonitoringPage = this.userMonitoringService.findByUserEmailAndDateRange(email, startDate, endDate, pageable);
                        if (userMonitoringPage.getBody().isEmpty()) {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("No UserMonitoring found for the given criteria");
                        }
                        return ResponseEntity.ok(userMonitoringPage.getBody());
                    }
                    return ResponseEntity.status(401).body("Unauthorized: You can only consult your own data.");
                }
                return ResponseEntity.status(401).body("Unauthorized: User is not authorized to access this resource");
            }
            if (startDate.isAfter(endDate)) {
                 return ResponseEntity.badRequest().body("Start date must be before end date");
            }
            
            ResponseEntity<List<UserMonitoring>> userMonitoringPage = this.userMonitoringService.findByUserEmailAndDateRange(email, startDate, endDate, pageable);
        
            if (userMonitoringPage.getBody().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No UserMonitoring found for the given criteria");
            }

            return ResponseEntity.ok(userMonitoringPage.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
       
    }

    //return the three users with most records in date range
    @GetMapping("/top-users")
    private ResponseEntity<?> getTopThreeUsersInDateRange(
            @RequestHeader("Authorization") String token,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        
        if (startDate.isAfter(endDate)) {
            return ResponseEntity.badRequest().body("Start date must be before end date");
        }
        if (!this.authorizationUtil.isAuthorized(token, "Admin")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Unauthorized: User is not authorized to access this resource");
        }

        Pageable pageable = PageRequest.of(0, 3);
        List<UserWithRecordCount> topUsers = userMonitoringService.findTopThreeUsersWithMostRecordsInDateRange(startDate, endDate, pageable);
        return ResponseEntity.ok(topUsers);
    }

    // @GetMapping("/top-users-by-usage-type")
    // private ResponseEntity<?> getTopUsersByUsageTypeAndCountryAndDateRange(
    //         @RequestHeader("Authorization") String token,
    //         @RequestParam String usageType,
    //         @RequestParam String countryId,
    //         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
    //         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
    //         @RequestParam(defaultValue = "0") Integer page,
    //         @RequestParam(defaultValue = "10") Integer size) {
    //         if (!this.authorizationUtil.isAuthorized(token, "Admin")) {
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    //                 .body("Unauthorized: User is not authorized to access this resource");
    //         }
    //         Pageable pageable = PageRequest.of(page, size);
    //     // List<Object[]> topUsers = userMonitoringService.findTopUsersByUsageTypeAndCountryAndDateRange(usageType, countryId, startDate, endDate);
    //         ResponseEntity<List<UserMonitoring>> response = userMonitoringService.findByDescriptionAndUser_Country_IdAndCreatedAtBetween(
    //                 usageType, countryId, startDate, endDate, pageable);

    //         return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    //     // return ResponseEntity.ok(topUsers);
    //         }  

    }

