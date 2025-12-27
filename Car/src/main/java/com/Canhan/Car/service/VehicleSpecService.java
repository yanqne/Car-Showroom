package com.Canhan.Car.service;

import com.Canhan.Car.dto.VehicleSpecDTO;
import com.Canhan.Car.exception.ResourceNotFoundException;
import com.Canhan.Car.model.entity.Vehicle;
import com.Canhan.Car.model.entity.VehicleSpec;
import com.Canhan.Car.repository.VehicleSpecRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class VehicleSpecService {
    private final VehicleSpecRepository vehicleSpecRepository;
    public VehicleSpecService(VehicleSpecRepository vehicleSpecRepository) {
        this.vehicleSpecRepository = vehicleSpecRepository;
    }
    //Gọi danh sách theo sản phẩm
    public List<VehicleSpecDTO> findAll() {
        List<VehicleSpec> vehicleSpecs = vehicleSpecRepository.findAll();
        return vehicleSpecs.stream().map(this::mapToVehicleSpecDTO).toList();
    }
    //gọi danh sách các vehicle spec theo id xe
    public List<VehicleSpecDTO> findVehicleSpecByVehicleId(Long VehicleId) {
        List<VehicleSpec> vehicleSpec = vehicleSpecRepository.findByVehicleId(VehicleId);
        return vehicleSpec.stream().map(this::mapToVehicleSpecDTO).toList();
    }
    //Tạo mới VehicleSpec
    public VehicleSpecDTO createSpec(Long vehicleId, VehicleSpecDTO dto) {
        VehicleSpec spec = mapToVehicleSpec(dto);

        // Gán Vehicle vào Spec thay vì chỉ gán id
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        spec.setVehicle(vehicle);

        VehicleSpec saved = vehicleSpecRepository.save(spec);
        return mapToVehicleSpecDTO(saved);
    }
    //Update VehicleSpec
    public VehicleSpecDTO updateVehicleSpec(Long VehicleId, Long SpecId, VehicleSpecDTO dto) {
        VehicleSpec spec = vehicleSpecRepository.findByIdAndVehicleId(SpecId, VehicleId).orElseThrow(()->new ResourceNotFoundException("Không tìm thấy VehicleSpec với Id: " + SpecId + " cho vehicleId: " + VehicleId));
        spec.setSpecValue(dto.getSpecValue());
        spec.setSpecKey(dto.getSpecKey());
        spec.setSortOrder(Integer.valueOf(dto.getSortOrder()));
        VehicleSpec updated = vehicleSpecRepository.save(spec);
        return mapToVehicleSpecDTO(updated);
    }
    //Delete VehicleSpec ( Xoá 1 phần của 1 xe)
    public void DeleteVehicleSpec(Long VehicleId, Long SpecId){
        VehicleSpec spec = vehicleSpecRepository.findByIdAndVehicleId(SpecId, VehicleId).orElseThrow(()->new ResourceNotFoundException("Không tìm thấy VehicleSpec với Id: " + SpecId + " cho vehicleId: " + VehicleId));
        vehicleSpecRepository.delete(spec);
    }
    public VehicleSpecDTO mapToVehicleSpecDTO(VehicleSpec vehicleSpec){
        VehicleSpecDTO dto = new VehicleSpecDTO();
        dto.setId(vehicleSpec.getId());
        dto.setSpecKey(vehicleSpec.getSpecKey());
        dto.setSpecValue(vehicleSpec.getSpecValue());
        dto.setSortOrder(vehicleSpec.getSortOrder());
        if(vehicleSpec.getId() != null){
            dto.setVehicleId(vehicleSpec.getVehicle().getId());
        }
        return dto;
    }
    public VehicleSpec mapToVehicleSpec(VehicleSpecDTO dto){
        VehicleSpec spec = new VehicleSpec();
        spec.setId(dto.getId());
        if (dto.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(dto.getVehicleId());
            spec.setVehicle(vehicle);
        }
        spec.setSpecKey(dto.getSpecKey());
        spec.setSpecValue(dto.getSpecValue());
        spec.setSortOrder(dto.getSortOrder());
        return spec;
    }

}
