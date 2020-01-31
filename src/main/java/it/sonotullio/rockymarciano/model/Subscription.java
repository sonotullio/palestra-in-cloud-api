package it.sonotullio.rockymarciano.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
public class Subscription {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private Date fromDate;
    private Date toDate;
    private Double price;

    @ManyToOne
    private Sport sport;

    @ManyToOne
    private Client client;

    public String getDescription() {
        return getSport().getName() + ", " + getClient().getName() + " " + getClient().getSurname();
    }

}