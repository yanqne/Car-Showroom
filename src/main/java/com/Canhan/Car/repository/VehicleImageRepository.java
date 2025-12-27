package com.Canhan.Car.repository;

import com.Canhan.Car.model.entity.VehicleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleImageRepository extends JpaRepository<VehicleImage, Long> {

}
