package it.sonotullio.rockymarciano.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Sport extends Product {

    private int maxEntrance;
    private int duration;

    @Override
    public String getType() {
        return Product.SPORT;
    }
}
