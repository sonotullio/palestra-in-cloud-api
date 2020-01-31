package it.sonotullio.rockymarciano.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String email;

    private String name;

    private String surname;

    private String cf;

    private String phone;

    private String sport;

    private String dateOfBirth;

    private String birthPlace;

    private String note;

    private Date certificateExpirationDate;

    @Lob
    private byte[] img;

    @OrderBy("toDate ASC")
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Subscription> subscriptions = new ArrayList<>();

    public Date getExpirationDate() {
        return this.subscriptions.isEmpty() ? null : getSubscriptions().get(getSubscriptions().size() - 1).getToDate();
    }

    public String getSport() {
        return this.subscriptions.isEmpty() ? null : getSubscriptions().get(getSubscriptions().size() - 1).getSport().getName();
    }

}
