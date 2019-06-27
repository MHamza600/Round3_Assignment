package com.example.aqs_new.patient1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient  findPatientByPatientid(Long id);
    Patient  findPatientByUsername(String username);
}
