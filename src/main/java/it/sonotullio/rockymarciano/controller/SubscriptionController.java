package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.Subscription;
import it.sonotullio.rockymarciano.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionController {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @PostMapping
    public Subscription save(@RequestBody Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @GetMapping("/{id}")
    public Subscription find(@PathVariable String id, Optional<Integer> clientId) throws Exception {

        Optional<Subscription> subscription = subscriptionRepository.findById(id);

        if (subscription.isPresent()) {
            return subscription.get();
        } else {
            throw new Exception("Not found: " + id);
        }
    }

    @GetMapping("/client/{clientId}")
    public List<Subscription> findByClientId(@PathVariable int clientId) {
        return subscriptionRepository.findAllByClientId(clientId);
    }

}