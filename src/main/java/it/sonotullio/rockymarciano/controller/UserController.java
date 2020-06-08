package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.User;
import it.sonotullio.rockymarciano.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public User save(@RequestBody User user) throws Exception {
        Optional<User> result = userRepository.findByCf(user.getCf());
        if (result.isPresent()) {
            throw new Exception("Utente gia registrato con questo codice fiscale");
        } else {
            return userRepository.save(user);
        }
    }

    @GetMapping("/{id}")
    public User find(@PathVariable String id) throws Exception {
        Optional<User> client = userRepository.findById(id);

        if (client.isPresent()) {
            return client.get();
        } else {
            throw new Exception("Not found: " + id);
        }
    }

    @GetMapping
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }
}
