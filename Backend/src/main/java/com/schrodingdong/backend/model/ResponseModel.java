package com.schrodingdong.backend.model;

import lombok.Data;

@Data
public class ResponseModel {
    private String uuid;
    private String first_name;
    private String last_name;
    private String email;
    private String ipv4;
    private long timeForRequest;
    public ResponseModel(UserModel user, long timeForRequest) {
        this.uuid = user.getUuid();
        this.first_name = user.getFirst_name();
        this.last_name = user.getLast_name();
        this.email = user.getEmail();
        this.ipv4 = user.getIpv4();
        this.timeForRequest = timeForRequest;
    }
}
