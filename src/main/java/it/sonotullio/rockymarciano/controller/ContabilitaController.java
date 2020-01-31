package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.Cashflow;
import it.sonotullio.rockymarciano.model.Subscription;
import it.sonotullio.rockymarciano.model.Transaction;
import it.sonotullio.rockymarciano.repository.SubscriptionRepository;
import it.sonotullio.rockymarciano.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/contabilita")
public class ContabilitaController {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping
    public List<Cashflow> getAll() {
        List<Cashflow> cashflows = new ArrayList<>();

        for (Subscription subscription: subscriptionRepository.findAll()) {
            cashflows.add(new Cashflow(subscription));
        }

        for (Transaction transaction: transactionRepository.findAll()) {
            cashflows.add(new Cashflow(transaction));
        }

        cashflows.sort(Comparator.comparing(Cashflow::getDate));
        cashflows.add(Cashflow.total(cashflows));

        return cashflows;
    }
}
