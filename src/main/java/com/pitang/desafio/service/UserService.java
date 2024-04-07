package com.pitang.desafio.service;

import com.pitang.desafio.model.User;
import com.pitang.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        if (userDetails.getEmail() != null && !userDetails.getEmail().equals(user.getEmail()) && userRepository.findByEmail(userDetails.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }

        if (userDetails.getLogin() != null && !userDetails.getLogin().equals(user.getLogin()) && userRepository.findByLogin(userDetails.getLogin()).isPresent()) {
            throw new Exception("Login already exists");
        }

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setBirthday(userDetails.getBirthday());
        user.setLogin(userDetails.getLogin());
        user.setPassword(userDetails.getPassword());
        user.setPhone(userDetails.getPhone());
        user.setCars(userDetails.getCars());

        if (user.getFirstName() == null || user.getEmail() == null || user.getLogin() == null) {
            throw new Exception("Missing fields");
        }

        return userRepository.save(user);
    }

    public User authenticate(String login, String password) {
        Optional<User> userOptional = userRepository.findByLogin(login);

        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            return userOptional.get();
        }
        return null;
    }


}