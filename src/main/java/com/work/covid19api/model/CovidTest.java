package com.work.covid19api.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="covidTests")
public class CovidTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="userid")
    private String userid;

    @Column(name="temperature")
    private double temperature;

    @Column(name="covidresult")
    private String covidresult;

    @Column(name="testdate")
    private String testdate;

    @Column(name="postingdate")
    private LocalDateTime postingdate;

    @Column(name="postedby")
    private String postedby;

    @Column(name="updateddate")
    private LocalDateTime updateddate;


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

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getCovidresult() {
        return covidresult;
    }

    public void setCovidresult(String covidresult) {
        this.covidresult = covidresult;
    }

    public String getTestdate() {
        return testdate;
    }

    public void setTestdate(String testdate) {
        this.testdate = testdate;
    }

    public LocalDateTime getPostingdate() {
        return postingdate;
    }

    public void setPostingdate(LocalDateTime postingdate) {
        this.postingdate = postingdate;
    }

    public String getPostedby() {
        return postedby;
    }

    public void setPostedby(String postedby) {
        this.postedby = postedby;
    }

    public LocalDateTime getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(LocalDateTime updateddate) {
        this.updateddate = updateddate;
    }
}
