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

    private  int duration;

    private Double price;

    @ManyToOne
    private Sport sport;

    @ManyToOne
    private Client client;

    public String getDescription() {
        return "Abbonamento " + durationLabel(duration) + ", valido fino al " + toDate;
    }

    private String durationLabel(int duration) {
        switch (duration) {
            case 1:
                return "Mensile";
            case 3:
                return "Trimestrale";
            case 6:
                return "Semestrale";
            case 12:
                return "Annuale";
            default:
                return new Integer(duration) + " Mesi";
        }
    }

}