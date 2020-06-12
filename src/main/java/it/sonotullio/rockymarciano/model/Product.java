package it.sonotullio.rockymarciano.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type", length = 45)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Sport.class, name = Product.SPORT),
        @JsonSubTypes.Type(value = Merchandise.class, name = Product.MERCHANDISE),
})
public abstract class Product extends WebClass {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotNull(message = "Il nome e' un campo obbligatorio!")
    private String name;

    @NotNull(message = "Il prezzo e' un campo obbligatorio!")
    private Double price;

    private String description;

    @JsonIgnore
    @OrderBy("date ASC")
    @OneToMany(mappedBy="product", cascade = CascadeType.ALL)
    private List<Purchase> purchases = new ArrayList<>();

    public static final String SPORT = "sport";
    public static final String MERCHANDISE = "merchandise";

    public abstract String getType();

}
