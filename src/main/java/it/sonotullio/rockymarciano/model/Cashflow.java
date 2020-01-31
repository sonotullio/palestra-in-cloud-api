package it.sonotullio.rockymarciano.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Cashflow {

    private double price;
    private Date date;
    private String description;

    public Cashflow(Transaction transaction) {
        this.price = transaction.getPrice();
        this.date = transaction.getDate();
        this.description = transaction.getDescription();
    }

    public Cashflow(Subscription subscription) {
        this.price = subscription.getPrice();
        this.date = subscription.getFromDate();
        this.description = subscription.getDescription();
    }

    public static Cashflow total(List<Cashflow> cashflows) {
        Cashflow total = new Cashflow();
        total.description = "Totale";
        total.date = new Date();
        for (Cashflow cashflow: cashflows) {
            total.price += cashflow.getPrice();
        }
        return total;
    }

}
