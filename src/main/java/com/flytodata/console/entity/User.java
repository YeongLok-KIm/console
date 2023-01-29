package com.flytodata.console.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "email", length = 128)
    private String email;

    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "user_code", length = 16)
    private String userCode;

    @Column(name = "api_key", length = 64)
    private String apiKey;

    @Column(name = "api_secret", length = 128)
    private String apiSecret;


    @CreationTimestamp
    @Column(name = "created", nullable = false)
    private Instant created;

    @UpdateTimestamp
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Builder
    public User(String name, String email, String password, String api_key, String api_secret, Boolean active){
        this.name = name;
        this.email = email;
        this.password = password;
        this.apiKey = api_key;
        this.apiSecret = api_secret;
        this.active = true;
    }
}