package com.example.aqs_new.api1;


import com.example.aqs_new.daigonose1.Daigonos;
import com.example.aqs_new.doctor1.DoctorRepository;
import com.example.aqs_new.doctor1.Doctors;
import com.example.aqs_new.hospital1.Hospital;
import com.example.aqs_new.juncton1.Junction;
import com.example.aqs_new.patient1.Patient;
import com.example.aqs_new.patient1.PatientRepository;
import com.example.aqs_new.response.Respone;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/", "project"})
public class Controller {

    @Autowired
    Services services;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @RequestMapping(
            value = {"/SignUp"},
            method = {RequestMethod.POST},
            produces = {"application/json"}
    )
    @ResponseBody
    public Respone SignUp (@RequestBody Patient patient)
    {
        this.services.signUpPatient(patient);
        Respone respone = new Respone();
        respone.setResponse("Sign Up Sucessful");
        return respone;
    }


    @RequestMapping(
            value = {"/SignUp_doc"},
            method = {RequestMethod.POST},
            produces = {"application/json"}
    )
    @ResponseBody
    public Respone SignUp (@RequestBody Doctors doctor)
    {

        this.services.signUpDocter(doctor);
        Respone respone = new Respone();
        respone.setResponse("Sign Up Sucessful");
        return respone;
    }

    @RequestMapping(
            value = {"/add_daigonos/{doctors}/{patients}"},
            method = {RequestMethod.POST},
            produces = {"application/json"}
    )
    @ResponseBody()
    public Respone NewPost (@RequestBody Daigonos daigonose, @PathVariable("doctors") String doctors , @PathVariable("patients") String patients)
    {
        Respone respone = new Respone();
        Patient patient =patientRepository.findPatientByUsername(patients);
        if(patient==null)
        {
            respone.setResponse("No patient found");
            return respone;
        }

        Doctors doctor1 =doctorRepository.findDoctorsByUsername(doctors);
        if(patient==null)
        {
            respone.setResponse("No Doctor found");
            return respone;
        }
        Doctors doctor = services.NewDoctor(doctors);
        doctor.daigonosList.add(daigonose);
        Daigonos d1 = doctor.getpost(doctor);
        Junction j = d1.getJunction();
        j.setPatientid(patients);
        d1.setJunction(j);
        d1.setDoctor(doctor);
        doctorRepository.save(doctor);
        respone.setResponse("Patient Daigonosed");
        return  respone;


    }


    @RequestMapping(
            value = {"/hospitalInfo"},
            method = {RequestMethod.POST},
            produces = {"application/json"}
    )
    @ResponseBody
    public Hospital HospitalInfo (@RequestBody Hospital hospital)
    {
        return this.services.setHospital(hospital);
    }



    @RequestMapping(
            value = {"/getalldoc"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<Object> Getalldoc ()
    {
       List<Doctors> doctors=this.services.findalldoc();
       return  new ResponseEntity<>(doctors, HttpStatus.OK);
    }



    @RequestMapping(
            value = {"/doctorpatients/{doctorid}"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<Object> doctorpatients (@PathVariable("doctorid") String doctorid)
    {
        List<Patient> patients = this.services.finddoctorpatients(doctorid);
        return  new ResponseEntity<> (patients, HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/Login/{username}/{password}"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Respone Login ( @PathVariable("username") String username , @PathVariable("password") String password)
    {

        return this.services.login(username , password);

    }


    @RequestMapping(
            value = {"/getalldocbypage/{page}"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Page<Doctors> Getalldocbypage (@PathVariable("page") int page)
    {
        return doctorRepository.findAll(PageRequest.of(page,5));
    }




}