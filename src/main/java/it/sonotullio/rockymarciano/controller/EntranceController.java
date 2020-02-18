package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.Entrance;
import it.sonotullio.rockymarciano.repository.EntranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/entrances")
public class EntranceController {

    @Autowired
    EntranceRepository entranceRepository;

    @PostMapping
    public Entrance save(@RequestBody Entrance entrance) {
        return entranceRepository.save(entrance);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        entranceRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Entrance find(@PathVariable String id) throws Exception {
        Optional<Entrance> entrance = entranceRepository.findById(id);

        if (entrance.isPresent()) {
            return entrance.get();
        } else {
            throw new Exception("Not found: " + id);
        }
    }

    @GetMapping
    public List<Entrance> findAll(Optional<Integer> clientId) {
        if (clientId.isPresent()) {
            return entranceRepository.findAllByClientId(clientId.get());
        }
        return (List<Entrance>) entranceRepository.findAll();
    }
}
