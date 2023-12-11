package com.prevalentWare.backend_PR.entities;

import lombok.Data;

@Data
public class UserWithRecordCount {
    private User user;
    private long recordCount;

    public UserWithRecordCount(User user, long recordCount) {
        this.user = user;
        this.recordCount = recordCount;
    }
}