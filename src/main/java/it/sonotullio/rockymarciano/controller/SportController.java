package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.Sport;
import it.sonotullio.rockymarciano.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/sports")
public class SportController {

    @Autowired
    SportRepository sportRepository;

    @PostMapping
    public Sport save(@RequestBody Sport sport) {
        return sportRepository.save(sport);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        sportRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Sport find(@PathVariable String id) throws Exception {
        Optional<Sport> sport = sportRepository.findById(id);

        if (sport.isPresent()) {
            return sport.get();
        } else {
            throw new Exception("Not found: " + id);
        }
    }

    @GetMapping
    public List<Sport> findAll() {
        return (List<Sport>) sportRepository.findAll();
    }
}
