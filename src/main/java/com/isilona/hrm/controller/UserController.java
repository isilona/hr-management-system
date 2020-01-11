package com.isilona.hrm.controller;

import com.isilona.hrm.dao.entity.User;
import com.isilona.hrm.dao.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity create() {

        User user = new User();
        user.setUserName("Name");
        user.setPassword("Password");
        User saved = repository.save(user);

        return ResponseEntity
                .created(null)
                .body(saved);
    }

    @GetMapping
    public ResponseEntity test() {

        return ResponseEntity
                .ok()
                .body("HELLO v3");
    }
}
