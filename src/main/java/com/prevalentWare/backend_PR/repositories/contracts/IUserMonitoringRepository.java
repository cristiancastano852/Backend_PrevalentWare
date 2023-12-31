package com.prevalentWare.backend_PR.repositories.contracts;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prevalentWare.backend_PR.entities.UserMonitoring;

public interface IUserMonitoringRepository extends JpaRepository<UserMonitoring, String> {
     @Query("SELECT um.user, COUNT(um.id) " +
           "FROM UserMonitoring um " +
           "WHERE um.createdAt BETWEEN :startDate AND :endDate " +
           "GROUP BY um.user " +
           "ORDER BY COUNT(um.id) DESC")
    List<Object[]> findTopThreeUsersByRecordCount(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable);
    List<UserMonitoring> findByUser_EmailAndCreatedAtBetween(String email, LocalDateTime startDate, LocalDateTime endDate,  Pageable pageable);

//     @Query("SELECT um.id, um.usage, um.description, um.userId "+
//         "FROM UserMonitoring um "+
//         "JOIN User u ON um.userId = u.id "+
//         "JOIN _CountryToUser co ON u.id = co.B "+
//         "WHERE um.description = :usageType "+
//         "AND co.A = :countryId "+
//         "AND um.createdAt >= :startDate "+
//         "AND um.createdAt <= :endDate")

// List<UserMonitoring> findByDescriptionAndUser_Country_IdAndCreatedAtBetween(
//             String description,
//             String countryId,
//             LocalDateTime startDate,
//             LocalDateTime endDate,
//             Pageable pageable);
}
