package com.Canhan.Car.repository;

import com.Canhan.Car.model.entity.VehicleSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleSpecRepository extends JpaRepository<VehicleSpec, Long> {
    List<VehicleSpec> findByVehicleId(Long VehicleId);
    Optional<VehicleSpec> findByIdAndVehicleId(Long VehicleId,Long SpecId);
}
