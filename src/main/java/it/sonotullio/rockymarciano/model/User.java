package it.sonotullio.rockymarciano.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User extends WebClass {

    @Id
    private String cf;
    private String password;
    private Boolean admin;

}
