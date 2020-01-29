package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.User;
import it.sonotullio.rockymarciano.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/login")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

}
