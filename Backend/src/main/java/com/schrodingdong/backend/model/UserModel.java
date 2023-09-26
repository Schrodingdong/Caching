package com.schrodingdong.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Entity
@Table(name = "mytable")
public class UserModel implements Serializable {
    @Id
    private String uuid;
    private String first_name;
    private String last_name;
    private String email;
    private String ipv4;
}
