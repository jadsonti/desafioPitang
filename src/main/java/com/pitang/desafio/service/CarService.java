package com.pitang.desafio.service;

import com.pitang.desafio.exception.CarLicensePlateAlreadyExistsException;
import com.pitang.desafio.exception.CarNotFoundException;
import com.pitang.desafio.exception.MissingFieldsException;
import com.pitang.desafio.model.Car;
import com.pitang.desafio.model.User;
import com.pitang.desafio.repository.CarRepository;
import com.pitang.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public Car addCar(Car car, String username) throws Exception {
        if (car.getLicensePlate() == null || car.getModel() == null || car.getColor() == null) {
            throw new MissingFieldsException("Missing fields");
        }

        if (carRepository.findByLicensePlate(car.getLicensePlate()).isPresent()) {
            throw new CarLicensePlateAlreadyExistsException("License plate already exists");
        }

        User user = userService.findByLogin(username);
        car.setUser(user);
        return carRepository.save(car);
    }

    public Car findCarByIdAndUsername(Long carId, String username) {
        return carRepository.findByIdAndUserLogin(carId, username)
                .orElseThrow(() -> new CarNotFoundException("Car not found or does not belong to the current user"));
    }

    public void deleteCarByIdAndUsername(Long id, String username) {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Car car = carRepository.findByIdAndUserLogin(id, username)
                .orElseThrow(() -> new CarNotFoundException("Car not found or does not belong to user"));

        carRepository.delete(car);
    }

    public Car updateCar(Long id, Car carDetails, String username) {
        Car car = carRepository.findByIdAndUserLogin(id, username)
                .orElseThrow(() -> new CarNotFoundException("Car not found"));

        if (carRepository.existsByLicensePlateAndIdNot(carDetails.getLicensePlate(), id)) {
            throw new CarLicensePlateAlreadyExistsException("License plate already exists");
        }

        if (carDetails.getLicensePlate() == null || carDetails.getModel() == null || carDetails.getColor() == null) {
            throw new MissingFieldsException("Missing fields");
        }

        car.setManufactureYear(carDetails.getManufactureYear());
        car.setLicensePlate(carDetails.getLicensePlate());
        car.setModel(carDetails.getModel());
        car.setColor(carDetails.getColor());

        return carRepository.save(car);
    }
}