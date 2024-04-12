package com.pitang.desafio.service;

import com.pitang.desafio.exception.UserNotFoundException;
import com.pitang.desafio.model.Car;
import com.pitang.desafio.model.User;
import com.pitang.desafio.repository.CarRepository;
import com.pitang.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CarRepository carRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }
        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new Exception("Login already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("User not found");
        }
    }

    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User updateUser(Long id, User userDetails) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));

        if (!user.getEmail().equals(userDetails.getEmail()) && userRepository.findByEmail(userDetails.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }

        if (!user.getLogin().equals(userDetails.getLogin()) && userRepository.findByLogin(userDetails.getLogin()).isPresent()) {
            throw new Exception("Login already exists");
        }

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setBirthday(userDetails.getBirthday());
        user.setLogin(userDetails.getLogin());
        user.setPhone(userDetails.getPhone());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));

        updateOrAddCars(user, userDetails.getCars());

        return userRepository.save(user);
    }

    private void updateOrAddCars(User user, List<Car> newCars) {
        if (newCars != null) {
            user.getCars().clear();

            newCars.forEach(newCar -> {
                Car managedCar = carRepository.findById(newCar.getId())
                        .map(existingCar -> {
                            existingCar.setManufactureYear(newCar.getManufactureYear());
                            existingCar.setLicensePlate(newCar.getLicensePlate());
                            existingCar.setModel(newCar.getModel());
                            existingCar.setColor(newCar.getColor());
                            return existingCar;
                        }).orElseGet(() -> {
                            newCar.setUser(user);
                            return newCar;
                        });
                user.getCars().add(managedCar);
            });
        }
    }


    public User authenticate(String login, String password) {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            return userOptional.get();
        }
        return null;
    }

    public User findByLogin(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with login: " + login));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), Collections.emptyList());
    }

    public List<Car> findCarsByUserLogin(String login) throws UserNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("User not found with login: " + login));
        return user.getCars();
    }

    public void onLoginSuccess(String username) {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with login: " + username));
        user.setLastLogin(new Date());
        userRepository.save(user);
    }

}