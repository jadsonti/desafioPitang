package com.pitang.desafio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.pitang.desafio.model.User;
import com.pitang.desafio.model.Car;
import com.pitang.desafio.repository.UserRepository;
import com.pitang.desafio.repository.CarRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Configuration
public class LoadDatabase {

    private final PasswordEncoder passwordEncoder;

    public LoadDatabase(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, CarRepository carRepository) {
        return args -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateAlice = sdf.parse("1990-01-01");
            Date dateBob = sdf.parse("1992-02-02");

            User alice = new User(null, "Alice", "Smith", "alice.smith@example.com", dateAlice, "alicesmith", passwordEncoder.encode("password1"), "1234567890");
            User bob = new User(null, "Bob", "Johnson", "bob.johnson@example.com", dateBob, "bobjohnson", passwordEncoder.encode("password2"), "0987654321");

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
