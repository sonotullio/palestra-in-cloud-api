package it.sonotullio.rockymarciano.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Sport extends Product {

    @NotNull(message = "Il limite di ingressi settimanali e' un campo obbligatorio!")
    private int maxEntrance;

    @NotNull(message = "La durata dell'abbonamento e' un campo obbligatorio!")
    private int duration;

    @JsonIgnore
    @OrderBy("date ASC")
    @OneToMany(mappedBy="sport", cascade = CascadeType.ALL)
    private List<Entrance> entrances = new ArrayList<>();

    @Override
    public String getType() {
        return Product.SPORT;
    }
}
