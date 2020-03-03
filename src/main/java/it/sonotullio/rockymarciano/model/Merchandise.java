package it.sonotullio.rockymarciano.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Merchandise extends Product {

    @Override
    public String getType() {
        return Product.MERCHANDISE;
    }
}
