package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.Client;
import it.sonotullio.rockymarciano.repository.ClientRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
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

    @PostMapping(value = "/image")
    public void saveImage(@RequestBody MultipartFile image, int clientId) throws IOException, SQLException {
        Optional<Client> client = clientRepository.findById(clientId);

        byte[] bytes = image.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        client.get().setImg(blob);

        clientRepository.save(client.get());
    }

    @GetMapping("/{id}")
    public Client find(@PathVariable int id) throws Exception {
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
