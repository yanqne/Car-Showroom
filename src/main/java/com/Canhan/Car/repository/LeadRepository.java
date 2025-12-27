package com.Canhan.Car.repository;

import com.Canhan.Car.model.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    Optional<Lead> findByPhone(String Phone);
    Optional<Lead> findByEmail(String Email);
}
