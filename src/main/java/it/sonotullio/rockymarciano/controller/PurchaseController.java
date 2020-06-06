package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.ChartPoint;
import it.sonotullio.rockymarciano.model.Purchase;
import it.sonotullio.rockymarciano.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/map")
    public List<ChartPoint> map() {
        List<ChartPoint> result = new ArrayList<>();
        Map<String, Double> map = new HashMap<>();

        for (Purchase purchase : purchaseRepository.findAll()) {
            Double total = map.get(purchase.getProduct().getName());
            if (total == null) {
                map.put(purchase.getProduct().getName(), purchase.getProduct().getPrice());
            } else {
                map.replace(purchase.getProduct().getName(), total + purchase.getProduct().getPrice());
            }
        }

        map.forEach((k, v) -> {
            result.add(new ChartPoint(k,v));
        });

        return result;

    }

}