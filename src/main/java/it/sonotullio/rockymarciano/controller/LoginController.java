package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.User;
import it.sonotullio.rockymarciano.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public User login(String cf, String password) throws Exception {
        User user = userRepository.findByCfAndPassword(cf, password);
        if ( user != null ) {
            return user;
        } else {
            throw new Exception("Codice fiscele o password errati!");
        }
    }

}
