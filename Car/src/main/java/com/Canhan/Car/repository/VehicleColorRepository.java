package com.Canhan.Car.repository;

import com.Canhan.Car.model.entity.Vehicle;
import com.Canhan.Car.model.entity.VehicleColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleColorRepository extends JpaRepository<VehicleColor, Long> {
    List<VehicleColor> findByVehicleId(Long vehicleId);
}
