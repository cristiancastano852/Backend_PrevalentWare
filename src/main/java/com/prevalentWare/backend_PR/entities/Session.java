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
@Table(name = "\"Session\"")
public class Session {

    @Id
    private String id;

    @Column(name = "\"sessionToken\"", nullable = false)
    private String sessionToken;

    @Column(name = "\"expiresAt\"")
    private LocalDateTime expiresAt;
    
    @Column(name = "\"createdAt\"", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "\"userId\"")
    private User user;
}
