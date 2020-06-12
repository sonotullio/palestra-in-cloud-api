package it.sonotullio.rockymarciano.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Client extends WebClass {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    @NotNull(message = "l'indirizzo e-mail e' un campo obbligatorio!")
    private String email;

    @Column(unique = true)
    @NotNull(message = "la password e' un campo obbligatorio!")
    private String password;

    private Boolean admin;

    @NotNull(message = "Il nome e' un campo obbligatorio!")
    private String name;

    @NotNull(message = "Il cognome e' un campo obbligatorio!")
    private String surname;

    @NotNull(message = "Il codice fiscale e' un campo obbligatorio!")
    private String cf;

    private String phone;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dateOfBirth;

    private String birthPlace;

    private String gender;

    private String note;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date certificateExpirationDate;

    @Lob
    private byte[] img;

    @JsonIgnore
    @OrderBy("date ASC")
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    private List<Purchase> purchases = new ArrayList<>();

    @JsonIgnore
    @OrderBy("date ASC")
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    private List<Entrance> entrances = new ArrayList<>();

    public Date getExpirationDate() {
        List<Purchase> purchases = getPurchases();
        if (purchases.isEmpty()) {
            return null;
        } else {
            Collections.reverse(purchases);
            for (Purchase purchase: purchases) {
                if (purchase.getProduct() instanceof Sport) {
                    return DateUtils.addMonths(purchase.getDate(), ((Sport) purchase.getProduct()).getDuration());
                }
            }
            return null;
        }
    }

    public boolean getExpired() {
        return getExpirationDate() == null ? Boolean.TRUE : new Date().after(getExpirationDate());
    }

    public boolean getExpiredCertificate() {
        return getCertificateExpirationDate() == null ? Boolean.TRUE : new Date().after(getCertificateExpirationDate());
    }

    public int getAge() {
        Date now = new Date();
        long ageTime = now.getTime() - getDateOfBirth().getTime();
        return (int) (ageTime / 3.154e+10);
    }

}
