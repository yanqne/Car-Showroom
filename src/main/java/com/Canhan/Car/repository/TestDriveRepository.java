package com.Canhan.Car.repository;

import com.Canhan.Car.model.entity.TestDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDriveRepository extends JpaRepository<TestDrive, Long> {
    List<TestDrive> findByLeadId(Long leadId);
    List<TestDrive> findByVehicleId(Long vehicleId);

}
