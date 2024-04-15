package com.pitang.desafio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer manufactureYear;
    private String licensePlate;
    private String model;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Car(Integer manufactureYear, String licensePlate, String model, String color, User user) {
        this.manufactureYear = manufactureYear;
        this.licensePlate = licensePlate;
        this.model = model;
        this.color = color;
        this.user = user;
    }
}
