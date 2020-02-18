package it.sonotullio.rockymarciano.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    private String dateOfBirth;

    private String birthPlace;

    private String note;

    @Temporal(TemporalType.DATE)
    private Date certificateExpirationDate;

    @Lob
    private byte[] img;

    @JsonIgnore
    @OrderBy("toDate ASC")
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    private List<Subscription> subscriptions = new ArrayList<>();

    @JsonIgnore
    @OrderBy("date ASC")
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    private List<Entrance> entrances = new ArrayList<>();

    public Date getExpirationDate() {
        return this.subscriptions.isEmpty() ? null : getSubscriptions().get(getSubscriptions().size() - 1).getToDate();
    }

    public String getExDate() {
        return this.getExpirationDate() != null ? new Integer(this.getExpirationDate().getDate()).toString() : "";
    }

    public String getExCertificateDate() {
        return this.getCertificateExpirationDate() != null ? new Integer(this.getCertificateExpirationDate().getDate()).toString() : "";
    }

    public String getExMonthAndYear() {
        if (this.getExpirationDate() == null) {
            return "";
        }
        return Month.of(this.getExpirationDate().getMonth()).getDisplayName(TextStyle.SHORT, Locale.ITALY) + " " + (1900 + this.getExpirationDate().getYear());
    }

    public String getExCertificateMonthAndYear() {
        if (this.getCertificateExpirationDate() == null) {
            return "";
        }
        return Month.of(this.getCertificateExpirationDate().getMonth()).getDisplayName(TextStyle.SHORT, Locale.ITALY) + " " + (1900 + this.getCertificateExpirationDate().getYear());
    }

    public String getSport() {
        return this.subscriptions.isEmpty() ? null : getSubscriptions().get(getSubscriptions().size() - 1).getSport().getName();
    }

}
