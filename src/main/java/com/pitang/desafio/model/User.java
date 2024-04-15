package com.pitang.desafio.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public User(Long id, String firstName, String lastName, String email, Date birthday, String login, String password, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }
    @PreUpdate
    protected void onUpdate() {
        this.lastLogin = new Date();
    }
}
