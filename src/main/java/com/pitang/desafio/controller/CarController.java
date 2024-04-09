package com.pitang.desafio.controller;

import com.pitang.desafio.exception.*;
import com.pitang.desafio.model.Car;
import com.pitang.desafio.model.User;
import com.pitang.desafio.service.CarService;
import com.pitang.desafio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<?> getUserCars() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized - No authentication information available");
        }
        String username = authentication.getName();

        try {
            List<Car> cars = userService.findCarsByUserLogin(username);
            return ResponseEntity.ok(cars);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestBody Car car, Authentication authentication) {
        try {
            String username = authentication.getName();
            Car savedCar = carService.addCar(car, username);
            return ResponseEntity.ok(savedCar);
        } catch (MissingFieldsException | CarLicensePlateAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized - No authentication information available");
        }

        try {
            String username = authentication.getName();
            Car car = carService.findCarByIdAndUsername(id, username);
            return ResponseEntity.ok(car);
        } catch (CarNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized - No authentication information available");
        }

        String username = authentication.getName();

        try {
            carService.deleteCarByIdAndUsername(id, username);
            return ResponseEntity.ok().body("Car removed successfully");
        } catch (CarNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody Car car, Authentication authentication) {
        try {
            String username = authentication.getName();
            Car updatedCar = carService.updateCar(id, car, username);
            return ResponseEntity.ok(updatedCar);
        } catch (MissingFieldsException | CarLicensePlateAlreadyExistsException | InvalidFieldsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (CarNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
