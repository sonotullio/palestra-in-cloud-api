package it.sonotullio.rockymarciano.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Purchase extends WebClass {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "UTC")
    @NotNull(message = "La data e' un campo obbligatorio!")
    private Date date;

    @ManyToOne
    @NotNull(message = "Il cliente e' un campo obbligatorio!")
    private Client client;

    @ManyToOne
    @NotNull(message = "Il prodotto e' un campo obbligatorio!")
    private Product product;

}
