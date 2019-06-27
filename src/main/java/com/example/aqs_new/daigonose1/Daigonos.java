package com.example.aqs_new.daigonose1;

import com.example.aqs_new.doctor1.Doctors;
import com.example.aqs_new.juncton1.Junction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "Daigonose")

public class Daigonos {

    @Id
    @GeneratedValue
    private Long daigonosid;
    private String instruction;
    private String medcine;
    @UpdateTimestamp
    private Time current;
    private String ward;


    @JsonIgnore
    @ManyToOne( fetch = FetchType.EAGER,cascade={CascadeType.ALL})
    @JoinColumn( name = "doctor_fk",referencedColumnName = "doctorid")
    private Doctors doctor;


    @OneToOne(fetch = FetchType.EAGER, cascade= {CascadeType.ALL})
    @JoinColumn(name="junction_id",referencedColumnName = "junctionid")
    private Junction junction = new Junction();

    public Doctors getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctors doctor) {
        this.doctor = doctor;
    }

    public Junction getJunction() {
        return junction;
    }

    public void setJunction(Junction junction) {
        this.junction = junction;
    }




    public Long getDaigonosid() {
        return daigonosid;
    }

    public void setDaigonosid(Long daigonosid) {
        this.daigonosid = daigonosid;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getMedcine() {
        return medcine;
    }

    public void setMedcine(String medcine) {
        this.medcine = medcine;
    }

    public Time getCurrent() {
        return current;
    }

    public void setCurrent(Time current) {
        this.current = current;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

}
