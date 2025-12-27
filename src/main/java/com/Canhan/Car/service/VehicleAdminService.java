package com.Canhan.Car.service;

import com.Canhan.Car.dto.VehicleRequestDTO;
import com.Canhan.Car.dto.VehicleResponseDTO;
import com.Canhan.Car.exception.ResourceNotFoundException;
import com.Canhan.Car.model.entity.Model;
import com.Canhan.Car.model.entity.Vehicle;
import com.Canhan.Car.model.enums.VehicleStatus;
import com.Canhan.Car.repository.ModelRepository;
import com.Canhan.Car.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleAdminService {
    private final VehicleRepository vehicleRepository;
    private final ModelRepository modelRepository;

    public VehicleAdminService(VehicleRepository vehicleRepository, ModelRepository modelRepository) {
        this.vehicleRepository = vehicleRepository;
        this.modelRepository = modelRepository;
    }
    //Lấy tất cả xe
    public List<VehicleResponseDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }
    //Tạo xe mới
    @Transactional
    public VehicleResponseDTO createVehicle(VehicleRequestDTO dto) {
        //kiểm tra Model có tồn tại không
        Model model = modelRepository.findById(dto.getModelId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Model: " + dto.getModelId()));
        //tạo Xe mới
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(model);
        vehicle.setSlug(generateSlug(dto.getName()));
        vehicle.setStatus(VehicleStatus.ACTIVE);//mặt định là active
        MaptoVehicle(vehicle, dto);
        vehicleRepository.save(vehicle);
        System.out.println("ENTITY STATUS = " + vehicle.getStatus());
        return mapToResponseDTO(vehicle);

    }
    //Tìm 1 xe
    @Transactional
    public Optional<VehicleResponseDTO> findVehicleById(@PathVariable Long id) {
        Vehicle findVehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy xe với id: " + id));
        System.out.println("ENTITY STATUS = " + findVehicle.getStatus());
        return Optional.of(mapToResponseDTO(findVehicle));
    }
    //CẬP NHẬT THÔNG TIN XE
    @Transactional
    public VehicleResponseDTO updateVehicle(Long id, VehicleRequestDTO dto) {
        //kiểm tra id xe
        Vehicle findVehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy xe với id: " + id));
        //kiểm tra model
        if (!findVehicle.getModel().getId().equals(dto.getModelId())) {
            Model model = modelRepository.findById(dto.getModelId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Model có Id: " + dto.getModelId()));
            findVehicle.setModel(model);
        }
        //cập nhật lại các trường
        MaptoVehicle(findVehicle, dto);
        findVehicle.setSlug(generateSlug(dto.getName()));
        vehicleRepository.save(findVehicle);

        return mapToResponseDTO(findVehicle);
    }

    //XOÁ 1 XE ( Theo hướng cập nhật status thành ẩn)
    @Transactional
    public void SoftdeleteVehicle(Long id, VehicleStatus newStatus) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("không tìm thấy xe với id: " + id));
        vehicle.setStatus(newStatus);
        vehicleRepository.save(vehicle);
        vehicleRepository.flush();
    }

    //XOÁ CỨNG 1 XE ( theo hướng xoá 1 xe khỏi dữ liệu database)
    @Transactional
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("không tìm thấy xe với id: " + id));
        vehicleRepository.delete(vehicle);
    }

    private String generateSlug(String text) {
        // Logic tạo slug, ví dụ: "Toyota Camry 2.5Q" -> "toyota-camry-2-5q"
        // (Cần import thư viện hoặc tự viết)
        return text.toLowerCase().replaceAll("\\s+", "-").replaceAll("[^a-z0-9-]", "");
    }

    public void MaptoVehicle(Vehicle vehicle, VehicleRequestDTO dto) {
        vehicle.setName(dto.getName());
        vehicle.setVariant(dto.getVariant());
        vehicle.setDrivetrain(dto.getDrivetrain());
        vehicle.setTransmission(dto.getTransmission());
        vehicle.setEngine(dto.getEngine());
        vehicle.setTorqueNm(dto.getTorqueNm());
        vehicle.setFuelConsumption(dto.getFuelConsumption());
        vehicle.setPowerHp(dto.getPowerHp());
        vehicle.setTrunkL(dto.getTrunkL());
        vehicle.setDescription(dto.getDescription());
        vehicle.setThumbnailUrl(dto.getThumnailUrl());
        vehicle.setYear(dto.getYear());
        vehicle.setDimensions(dto.getDimensions());
        vehicle.setSeats(dto.getSeats());
        vehicle.setPriceVnd(dto.getPriceVnd());
//        vehicle.setSlug(dto.getSlug());

    }

    private VehicleResponseDTO mapToResponseDTO(Vehicle vehicle) {
        Model model = vehicle.getModel();
        var brand = model.getBrand();

        return new VehicleResponseDTO(
                vehicle.getId(),
                vehicle.getName(),
                vehicle.getVariant(),
                vehicle.getDrivetrain(),
                vehicle.getTransmission(),
                vehicle.getEngine(),
                vehicle.getTorqueNm(),
                vehicle.getFuelConsumption(),
                vehicle.getPowerHp(),
                vehicle.getTrunkL(),
                vehicle.getDescription(),
                vehicle.getYear(),
                vehicle.getSeats(),
                vehicle.getDimensions(),
                vehicle.getThumbnailUrl(),
                vehicle.getPriceVnd(),
                vehicle.getSlug(),
                vehicle.getStatus(),
                model.getId(),
                model.getName(),
                brand.getId(),
                brand.getName()
        );

    }
}