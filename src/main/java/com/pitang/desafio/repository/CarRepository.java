package com.pitang.desafio.repository;


import com.pitang.desafio.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByLicensePlate(String licensePlate);
    @Query("SELECT c FROM Car c WHERE c.id = :carId AND c.user.login = :username")
    Optional<Car> findByIdAndUserLogin(Long carId, String username);
    boolean existsByLicensePlateAndIdNot(String licensePlate, Long id);
}