package com.work.covid19api.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="userId")
    private String userid;

    @Column(name="firstName")
    private String firstname;

    @Column(name="otherName")
    private String othername;

    @Column(name="lastName")
    private String lastname;

    @Column(name="contact")
    private String contact;

    @Column(name="email")
    private String email;

    @Column(name="posted_date")
    private LocalDateTime posted_date;

    @Column(name="posted_by")
    private Long posted_by;

    @Column(name="updated_date")
    private LocalDateTime update_date;

    @Column(name="ip_address")
    private String ip_address;

    @Column(name="mac_address")
    private String mac_address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
