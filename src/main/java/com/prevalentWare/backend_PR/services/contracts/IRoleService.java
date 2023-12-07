package com.prevalentWare.backend_PR.services.contracts;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.prevalentWare.backend_PR.entities.Role;

public interface IRoleService {
    public ResponseEntity<List<Role>> findAll();
    
}
