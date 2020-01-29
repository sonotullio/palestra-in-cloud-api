package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.Client;
import it.sonotullio.rockymarciano.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @PostMapping
    public Client save(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping("/{id}")
    public Client find(@PathVariable String id) throws Exception {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            return client.get();
        } else {
            throw new Exception("Not found: " + id);
        }
    }

    @GetMapping
    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }
}
