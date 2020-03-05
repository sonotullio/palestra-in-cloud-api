package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.Purchase;
import it.sonotullio.rockymarciano.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/purchases")
public class PurchaseController {

    @Autowired
    PurchaseRepository purchaseRepository;

    @PostMapping
    public Purchase save(@RequestBody Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @GetMapping("/{id}")
    public Purchase find(@PathVariable String id) throws Exception {

        Optional<Purchase> subscription = purchaseRepository.findById(id);

        if (subscription.isPresent()) {
            return subscription.get();
        } else {
            throw new Exception("Not found: " + id);
        }
    }

    @GetMapping
    public List<Purchase> find(Optional<Integer> clientId, Optional<String> productId) {
        if(clientId.isPresent()) {
            return purchaseRepository.findAllByClientId(clientId.get());
        } else if (productId.isPresent()){
            return purchaseRepository.findAllByProductId(productId.get());
        } else {
            return (List<Purchase>) purchaseRepository.findAll();
        }
    }

}