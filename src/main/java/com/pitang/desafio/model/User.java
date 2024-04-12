package com.pitang.desafio.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "app_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private String login;
    private String password;
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private List<Car> cars;


    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.lastLogin = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastLogin = new Date();
    }
}
