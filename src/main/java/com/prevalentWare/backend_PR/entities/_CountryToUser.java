package com.prevalentWare.backend_PR.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@IdClass(CountryToUser.class)
@Table(name = "\"_CountryToUser\"")
public class _CountryToUser {
    @Id
    @ManyToOne
    @JoinColumn(name = "\"A\"", referencedColumnName = "id")
    private Country country;

    @Id
    @ManyToOne
    @JoinColumn(name = "\"B\"", referencedColumnName = "id")
    private User user;

}
