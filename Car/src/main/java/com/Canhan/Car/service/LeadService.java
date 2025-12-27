package com.Canhan.Car.service;

import com.Canhan.Car.dto.TestDriveInfoDTO;
import com.Canhan.Car.dto.TestDriveRequestDTO;
import com.Canhan.Car.exception.ResourceNotFoundException;
import com.Canhan.Car.model.entity.Lead;
import com.Canhan.Car.model.entity.TestDrive;
import com.Canhan.Car.model.entity.Vehicle;
import com.Canhan.Car.model.enums.LeadSource;
import com.Canhan.Car.model.enums.LeadStatus;
import com.Canhan.Car.model.enums.TestDriveStatus;
import com.Canhan.Car.repository.LeadRepository;
import com.Canhan.Car.repository.TestDriveRepository;
import com.Canhan.Car.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeadService {
    private final LeadRepository leadRepository;
    private final VehicleRepository vehicleRepository;
    private final TestDriveRepository testDriveRepository;

    public LeadService(LeadRepository leadRepository, VehicleRepository vehicleRepository, TestDriveRepository testDriveRepository) {
        this.leadRepository = leadRepository;
        this.vehicleRepository = vehicleRepository;
        this.testDriveRepository = testDriveRepository;
    }
    //Phương thức xử lý logic đăng ký lái thử
    @Transactional
    public void createTestDrive(TestDriveRequestDTO dto){
        //tìm hoặc tạo mới khách hàng(Lead)
        Lead lead = findOrCreateLead(dto.getPhone(), dto.getFullname(), dto.getEmail(), dto.getMessage());
        //tìm xe mà khách muốn lái
        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId()).orElseThrow(()->
                new ResourceNotFoundException("không tìm thấy xe với ID: " +  dto.getVehicleId()));
        //tạo 1 bảng ghi testDrive mới
        TestDrive testdrive = new TestDrive();
        testdrive.setLead(lead);
        testdrive.setVehicle(vehicle);
        testdrive.setPreferredDate(dto.getPreferredDate());
        testdrive.setStatus(TestDriveStatus.PENDING);

        //lưu vào database
        testDriveRepository.save(testdrive);
    };


    public List<TestDriveInfoDTO> GetAllTestDrives(){
        List<TestDrive> testList = testDriveRepository.findAll(
                Sort.by(Sort.Direction.DESC, "createdAt")
        );
        return testList.stream()
                .map(this::MaptoTestDriveInfoDTO)
                .collect(Collectors.toList());
    };


    //kiểm tra SDT, nếu chưa thì tạo mới
    private Lead findOrCreateLead(String phone, String fullname, String email, String message){
        //tìm lead bằng sdt
        Optional<Lead> existinglead = leadRepository.findByPhone(phone);

        if(existinglead.isPresent()){
            return existinglead.get();
        }else {
            Lead newLead = new Lead();
            newLead.setPhone(phone);
            newLead.setFullName(fullname);
            newLead.setEmail(email);
            newLead.setMessage(message);
            newLead.setSource(LeadSource.WEBSITE);
            newLead.setStatus(LeadStatus.NEW);
            return leadRepository.save(newLead);
        }
    }
    public TestDriveInfoDTO MaptoTestDriveInfoDTO(TestDrive testdrive){
        TestDriveInfoDTO dto = new TestDriveInfoDTO();
        dto.setId(testdrive.getVehicle().getId());
        dto.setStatus(testdrive.getStatus());
        dto.setPreferredDate(testdrive.getPreferredDate());
        dto.setCreatedAt(testdrive.getCreatedAt());

        if(testdrive.getLead() != null){
            dto.setId(testdrive.getLead().getId());
            dto.setCustomerName(testdrive.getLead().getFullName());
            dto.setCustomerPhone(testdrive.getLead().getPhone());
            dto.setCustomerEmail(testdrive.getLead().getEmail());
        }
        if(testdrive.getVehicle() != null){
            dto.setId(testdrive.getVehicle().getId());
            dto.setVehicleName(testdrive.getVehicle().getName());
            dto.setVehicleSlug(testdrive.getVehicle().getSlug());
        }
        return dto;
    }
}
