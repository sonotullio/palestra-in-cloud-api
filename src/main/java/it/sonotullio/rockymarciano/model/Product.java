package it.sonotullio.rockymarciano.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type", length = 45)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Sport.class, name = Product.SPORT),
        @JsonSubTypes.Type(value = Merchandise.class, name = Product.MERCHANDISE),
})
public abstract class Product {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String name;
    private Double price;
    private String description;

    public static final String SPORT = "sport";
    public static final String MERCHANDISE = "merchandise";

    public abstract String getType();

}
