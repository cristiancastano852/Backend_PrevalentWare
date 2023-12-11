package com.prevalentWare.backend_PR.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name = "\"UserMonitoring\"")
public class UserMonitoring {

    @Id
    private String id;

    private Integer usage;

    private String description;

    @Column(name = "\"createdAt\"", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "\"userId\"", referencedColumnName = "id")
    private User user;

    
}