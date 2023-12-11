package com.prevalentWare.backend_PR.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class CountryToUser implements Serializable {
    private String country; 
    private String user;    
}
