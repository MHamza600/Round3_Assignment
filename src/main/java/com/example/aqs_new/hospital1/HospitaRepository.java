package com.example.aqs_new.hospital1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HospitaRepository extends JpaRepository<Hospital,Long>
{


}
