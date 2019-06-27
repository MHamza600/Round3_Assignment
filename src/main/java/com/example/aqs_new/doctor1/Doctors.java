package com.example.aqs_new.doctor1;

import com.example.aqs_new.daigonose1.Daigonos;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Doctors")
public class Doctors {


    @Id
    @GeneratedValue
    private Long doctorid;
    private String name;
    private String address;
    private String contact;
    @Email
    @Column(unique=true)
    private String email;
    @NotNull
    @Column(unique=true)
    private String username;
    @NotNull
    private String password;
    private Long age;
    private String specialization;
    private String education;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Long doctorid) {
        this.doctorid = doctorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Daigonos> getDaigonosList() {
        return daigonosList;
    }

    public void setDaigonosList(List<Daigonos> daigonosList) {
        this.daigonosList = daigonosList;
    }

    @OneToMany(mappedBy = "doctor",fetch = FetchType.EAGER,cascade={CascadeType.ALL})
    public List<Daigonos> daigonosList =  new ArrayList<>();


    public Daigonos getpost(Doctors doctors)
    {
        List <Daigonos> p1 = doctors.getDaigonosList();
        return p1.get((p1.size()-1));
    }

}
