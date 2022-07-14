package com.work.covid19api.model;

import javax.persistence.*;

@Entity
@Table(name="covidTests")
public class CovidTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


}
