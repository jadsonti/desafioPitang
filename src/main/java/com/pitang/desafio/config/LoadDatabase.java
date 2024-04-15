package com.pitang.desafio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.pitang.desafio.model.User;
import com.pitang.desafio.model.Car;
import com.pitang.desafio.repository.UserRepository;
import com.pitang.desafio.repository.CarRepository;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, CarRepository carRepository) {
        return args -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            User alice = new User(null, "Alice", "Smith", "alice.smith@example.com", sdf.parse("1990-01-01"), "alicesmith", "password1", "1234567890");
            User bob = new User(null, "Bob", "Johnson", "bob.johnson@example.com", sdf.parse("1992-02-02"), "bobjohnson", "password2", "0987654321");

            userRepository.save(alice);
            userRepository.save(bob);

            Car aliceCar1 = new Car(null, 2015, "ABC123", "SUV Modelo X", "Preto", alice);
            Car aliceCar2 = new Car(null, 2017, "XYZ789", "Compact Modelo Y", "Vermelho", alice);
            Car bobCar1 = new Car(null, 2018, "DEF456", "SUV Modelo Z", "Azul", bob);
            Car bobCar2 = new Car(null, 2016, "UVW345", "Sedan Modelo K", "Verde", bob);

            carRepository.saveAll(Arrays.asList(aliceCar1, aliceCar2, bobCar1, bobCar2));
        };
    }
}
