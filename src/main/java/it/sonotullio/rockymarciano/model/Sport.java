package it.sonotullio.rockymarciano.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Sport {

    @Id
    private String name;
    private Double price;
}
