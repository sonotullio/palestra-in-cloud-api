package it.sonotullio.rockymarciano.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Entrance extends WebClass {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "La data e' un campo obbligatorio!")
    private Date date;

    @ManyToOne
    @NotNull(message = "Il cliente e' un campo obbligatorio!")
    private Client client;

    @ManyToOne
    @NotNull(message = "lo sport e' un campo obbligatorio!")
    private Sport sport;

    private boolean deleted;
}
