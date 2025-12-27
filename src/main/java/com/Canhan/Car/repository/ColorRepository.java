package com.Canhan.Car.repository;

import com.Canhan.Car.model.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color> findByHexValue(String hexValue);
}
