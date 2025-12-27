package com.Canhan.Car.controller;

import com.Canhan.Car.dto.VehicleRequestDTO;
import com.Canhan.Car.dto.VehicleResponseDTO;
import com.Canhan.Car.dto.VehicleStatusRequest;
import com.Canhan.Car.model.entity.Vehicle;
import com.Canhan.Car.model.enums.VehicleStatus;
import com.Canhan.Car.service.VehicleAdminService;
import com.Canhan.Car.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/vehicles")
public class VehicleAdminController {
    private final VehicleAdminService vehicleAdminService;
    public VehicleAdminController(VehicleAdminService vehicleAdminService,  VehicleService vehicleService) {
        this.vehicleAdminService = vehicleAdminService;
    }
    //API gọi tất cả xe
    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles() {
        return ResponseEntity.ok().body(vehicleAdminService.getAllVehicles());
    }
    //API thêm xe mới
    @PostMapping
    public ResponseEntity<VehicleResponseDTO> addVehicle(@RequestBody VehicleRequestDTO dto) {
        VehicleResponseDTO vehicle = vehicleAdminService.createVehicle(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    }
    //API xem chi tiết 1 xe
    @GetMapping("/{id}")
    public ResponseEntity<Optional<VehicleResponseDTO>> findVehicleById(@PathVariable Long id) {
        Optional<VehicleResponseDTO> vehicle = vehicleAdminService.findVehicleById(id);
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }
    //API chỉnh sửa xe
    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleRequestDTO dto) {
        VehicleResponseDTO vehicle = vehicleAdminService.updateVehicle(id, dto);
        return ResponseEntity.ok(vehicle);
    }
    //API xoá mềm 1 xe
    @PutMapping("/{id}/status")
    public ResponseEntity<?> softDeleteVehicle(@PathVariable Long id, @RequestBody VehicleStatusRequest req) {
        try {
            vehicleAdminService.SoftdeleteVehicle(id, req.getStatus());
            return ResponseEntity.ok("Cập nhật trạng thái thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
        }
    }
    //API xoá cứng 1 xe
    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleAdminService.deleteVehicle(id);
        System.out.println("delete vehicle");
        return ResponseEntity.noContent().build();
    }
}
