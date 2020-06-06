package it.sonotullio.rockymarciano.model;

import lombok.Data;

@Data
public class ChartPoint {

    private String label;
    private Double value;

    public ChartPoint(String label, Double value) {
        this.label = label;
        this.value = value;
    }

}
