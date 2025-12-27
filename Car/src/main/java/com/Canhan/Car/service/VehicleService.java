package com.Canhan.Car.service;

import com.Canhan.Car.dto.*;
import com.Canhan.Car.exception.ResourceNotFoundException;
import com.Canhan.Car.model.entity.Vehicle;
import com.Canhan.Car.model.entity.VehicleColor;
import com.Canhan.Car.model.entity.VehicleImage;
import com.Canhan.Car.model.entity.VehicleSpec;
import com.Canhan.Car.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    //Lấy danh sách tóm tắt
    @Transactional(readOnly = true)
    public List<VehicleSummaryDTO> getAllVehiclesSummary() {
        return vehicleRepository.findAll()
                .stream()
                .map(this::mapToVehicleSummaryDTO)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public VehicleDetailDTO  getVehicleDetail(String slug) {
        //tìm entity bằng repository
        Vehicle vehicle = vehicleRepository.findBySlug(slug).orElseThrow(()-> new ResourceNotFoundException("không thấy xe với slug là : " + slug));

        //ánh xạ entity sang dto
        return mapToVehicleDetailDTO(vehicle);
    }

    //hàm mapping
    private VehicleDetailDTO mapToVehicleDetailDTO(Vehicle vehicle){
        VehicleDetailDTO dto = new VehicleDetailDTO();
        dto.setId(vehicle.getId());
        dto.setSlug(vehicle.getSlug());
        dto.setVariant(vehicle.getVariant());
        dto.setYear(vehicle.getYear());
        dto.setPriceVnd(vehicle.getPriceVnd());
        dto.setDescription(vehicle.getDescription());
        dto.setDrivetrain(vehicle.getDrivetrain());
        dto.setTransmission(vehicle.getTransmission());
        dto.setEngine(vehicle.getEngine());
        dto.setPowerHp(vehicle.getPowerHp());
        dto.setTorqueNm(vehicle.getTorqueNm());
        dto.setFuelConsumption(vehicle.getFuelConsumption());
        dto.setSeats(vehicle.getSeats());
        dto.setDimensions(vehicle.getDimensions());
        dto.setTrunkL(vehicle.getTrunkL());
        dto.setStatus(vehicle.getStatus());

        if (vehicle.getModel() != null) {
            dto.setModelSlug(vehicle.getModel().getSlug());
            if (vehicle.getModel().getBrand() != null) {
                dto.setBrandName(vehicle.getModel().getBrand().getName());
                dto.setBrandSlug(vehicle.getModel().getBrand().getSlug());
            }
        }
        dto.setImages(
                vehicle.getImages().stream()
                        .map(this::mapToImageDTO)
                        .collect(Collectors.toList())
        );

        dto.setSpecs(
                vehicle.getSpecs().stream()
                        .map(this::mapToSpecDTO)
                        .collect(Collectors.toList())
        );

        dto.setColors(
                vehicle.getVehicleColors().stream()
                        .map(this::mapToColorDTO)
                        .collect(Collectors.toSet())
        );

        return dto;

    }
    private VehicleSummaryDTO mapToVehicleSummaryDTO(Vehicle vehicle) {
        VehicleSummaryDTO dto = new VehicleSummaryDTO();
        dto.setId(vehicle.getId());
        dto.setName(vehicle.getName());
        dto.setStatus(vehicle.getStatus());
        dto.setSlug(vehicle.getSlug());
        dto.setPriceVnd(vehicle.getPriceVnd());
        dto.setThumbnailUri(vehicle.getThumbnailUrl());
        dto.setYear(vehicle.getYear());
        if (vehicle.getModel() != null && vehicle.getModel().getBrand() != null) {
            dto.setBrandName(vehicle.getModel().getBrand().getName());
        }

        return dto;
    }

    private VehicleImageDTO mapToImageDTO(VehicleImage image) {
        VehicleImageDTO dto = new VehicleImageDTO();
        dto.setUrl(image.getUrl());
        dto.setAlt(image.getAlt());
        return dto;
    }

    private VehicleSpecDTO mapToSpecDTO(VehicleSpec spec) {
        VehicleSpecDTO dto = new VehicleSpecDTO();
        dto.setSpecKey(spec.getSpecKey());
        dto.setSpecValue(spec.getSpecValue());
        return dto;
    }

    private VehicleColorDTO mapToColorDTO(VehicleColor vehicleColor) {
        VehicleColorDTO dto = new VehicleColorDTO();
        dto.setDefault(vehicleColor.isDefault());

        // Lấy thông tin từ đối tượng Color lồng bên trong
        if (vehicleColor.getColor() != null) {
            dto.setName(vehicleColor.getColor().getName());
            dto.setHexValue(vehicleColor.getColor().getHexValue());
        }
        return dto;
    }

}