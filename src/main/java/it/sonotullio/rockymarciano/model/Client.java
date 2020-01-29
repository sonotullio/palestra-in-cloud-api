package it.sonotullio.rockymarciano.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Client {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

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

    @OrderBy("toDate ASC")
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Subscription> subscriptions = new ArrayList<>();

    public Date getExpirationDate() {
        return this.subscriptions.isEmpty() ? null : getSubscriptions().get(getSubscriptions().size() - 1).getToDate();
    }

}
