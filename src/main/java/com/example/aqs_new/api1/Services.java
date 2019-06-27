package com.example.aqs_new.api1;


import com.example.aqs_new.daigonose1.Daigonos;
import com.example.aqs_new.daigonose1.DaigonosRepository;
import com.example.aqs_new.doctor1.DoctorRepository;
import com.example.aqs_new.doctor1.Doctors;
import com.example.aqs_new.hospital1.HospitaRepository;
import com.example.aqs_new.hospital1.Hospital;
import com.example.aqs_new.juncton1.Junction;
import com.example.aqs_new.patient1.Patient;
import com.example.aqs_new.patient1.PatientRepository;
import com.example.aqs_new.response.Respone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class Services {

    @Autowired
    HospitaRepository hospitalRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DaigonosRepository daigonosRepository;


    public Hospital setHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
        return hospital;
    }

    public void signUpPatient(Patient patient) {
        patientRepository.save(patient);
    }


    public void signUpDocter(Doctors doctor) {
        doctorRepository.save(doctor);
    }


    public Patient NewPatient(long id) {
        return patientRepository.findPatientByPatientid(id);
    }

    public Doctors NewDoctor(String username) {
        return doctorRepository.findDoctorsByUsername(username);
    }


    public List<Doctors> findalldoc() {
        return doctorRepository.findAll();

    }

    public List<Patient> finddoctorpatients(String id) {
        Doctors doctor = doctorRepository.findDoctorsByUsername(id);
        ArrayList<String> patientids = new ArrayList<String>();
        for (Iterator<Daigonos> daigonoseIterator = doctor.getDaigonosList().iterator(); daigonoseIterator.hasNext(); ) {
            Daigonos daigonose = daigonoseIterator.next();
            Junction junction = daigonose.getJunction();
            String patient = junction.getPatientid();
            if (!patientids.contains(patient)) {

                patientids.add(patient);
            }
        }

        ArrayList<Patient> patients = new ArrayList<Patient>();
        for (int i = 0; i < patientids.size(); i++) {
            patients.add(patientRepository.findPatientByUsername(patientids.get(i)));
        }
        return patients;
    }

    public Respone login(String username , String password)
    {
        Respone respone = new Respone();

        Doctors doctors = doctorRepository.findDoctorsByUsername(username);
        if(doctors==null)
        {
            respone.setResponse("No doctor Found");
            return  respone;
        }
        if(!doctors.getPassword().equals(password))
        {
            respone.setResponse("In valid Password");
            return  respone;
        }

        respone.setResponse("Login Sucessfull");
        return  respone;
    }


}
