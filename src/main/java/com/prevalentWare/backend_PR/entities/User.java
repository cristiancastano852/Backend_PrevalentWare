package com.prevalentWare.backend_PR.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "\"User\"")
public class User {
    @Id
    private String id;

    private String email;

    @Column(name = "\"emailVerified\"")
    private LocalDateTime emailVerified;

    @Column(name = "\"termsAndConditionsAccepted\"")
    private LocalDateTime termsAndConditionsAccepted;

    private String name;
    private String image;
    private String position;

    @Column(name = "\"createdAt\"", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "\"updatedAt\"", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "\"roleId\"", referencedColumnName = "id")
    private Role role;

    // @OneToMany(mappedBy = "user")
    // private List<CountryToUser> countryToUserList;

}
