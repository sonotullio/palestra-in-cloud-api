package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.Transaction;
import it.sonotullio.rockymarciano.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping
    public Transaction save(@RequestBody Transaction transaction) {
        transaction.setPrice(transaction.getPrice() * -1);
        return transactionRepository.save(transaction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        transactionRepository.deleteById(id);
    }

}
