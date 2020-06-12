package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.ChartPoint;
import it.sonotullio.rockymarciano.model.Entrance;
import it.sonotullio.rockymarciano.repository.EntranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/map")
    public List<ChartPoint> map() {
        List<ChartPoint> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (Entrance entrance : entranceRepository.findAll()) {
            Integer total = map.get(entrance.getSport().getName());
            if (total == null) {
                map.put(entrance.getSport().getName(), 1);
            } else {
                map.replace(entrance.getSport().getName(), total + 1);
            }
        }

        map.forEach((k, v) -> {
            result.add(new ChartPoint(k,new Double(v)));
        });

        return result;

    }
}
