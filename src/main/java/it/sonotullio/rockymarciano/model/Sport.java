package it.sonotullio.rockymarciano.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Sport extends Product {

    private int maxEntrance;
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
