package com.example.aqs_new.juncton1;


import com.example.aqs_new.daigonose1.Daigonos;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Junction")

public class Junction {
    @Id
    @GeneratedValue
    private Long junctionid;
    private String patientid;


    public Long getJunctionid() {
        return junctionid;
    }

    public void setJunctionid(Long junctionid) {
        this.junctionid = junctionid;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public Daigonos getDaigonose() {
        return daigonose;
    }

    public void setDaigonose(Daigonos daigonose) {
        this.daigonose = daigonose;
    }

    @JsonIgnore
    @OneToOne( fetch = FetchType.EAGER,cascade= {CascadeType.ALL})
    @JoinColumn(name="daignose_fk",referencedColumnName = "daigonosid")
    private Daigonos daigonose;

}
