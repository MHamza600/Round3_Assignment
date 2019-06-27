package com.example.aqs_new.repository;


import com.example.aqs_new.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer> {



}

