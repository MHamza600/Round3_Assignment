package com.example.aqs_new.doctor1;

import com.example.aqs_new.hospital1.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DoctorRepository extends JpaRepository<Doctors,Long> {
    Doctors findDoctorsByDoctorid(long id);
    Doctors findDoctorsByUsername(String username);
    Page<Doctors> findAll(Pageable pageable);
}
