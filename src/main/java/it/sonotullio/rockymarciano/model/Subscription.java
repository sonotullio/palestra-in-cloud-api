package it.sonotullio.rockymarciano.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Subscription {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "UTC")
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "UTC")
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