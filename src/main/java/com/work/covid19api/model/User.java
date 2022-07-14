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

    public User(){

    }

    //value specifying constructor
    public User(String firstname, String othername, String lastname, String userid, String contact, String email){
        this.setFirstname(firstname);
        this.setOthername(othername);
        this.setLastname(lastname);
        this.setContact(contact);
        this.setEmail(email);
        this.setUpdate_date(LocalDateTime.now());
//        this.setPosted_date(LocalDateTime.now());

    }

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

    public LocalDateTime getPosted_date() {
        return posted_date;
    }

    public void setPosted_date(LocalDateTime posted_date) {
        this.posted_date = posted_date;
    }

    public Long getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(Long posted_by) {
        this.posted_by = posted_by;
    }

    public LocalDateTime getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(LocalDateTime update_date) {
        this.update_date = update_date;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }
}
