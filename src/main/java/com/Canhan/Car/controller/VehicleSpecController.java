package com.Canhan.Car.controller;

import com.Canhan.Car.dto.VehicleDetailDTO;
import com.Canhan.Car.dto.VehicleSpecDTO;
import com.Canhan.Car.model.entity.Vehicle;
import com.Canhan.Car.model.entity.VehicleSpec;
import com.Canhan.Car.service.VehicleSpecService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle-spec")
public class VehicleSpecController {
    private final VehicleSpecService vehicleSpecService;
    public VehicleSpecController(VehicleSpecService vehicleSpecService) {
        this.vehicleSpecService = vehicleSpecService;
    }
    //Lấy tất cả danh sách VehicleSpec
    @GetMapping
    public ResponseEntity<List<VehicleSpecDTO>> getAllVehicleSpec(){
        List<VehicleSpecDTO> all = vehicleSpecService.findAll();
        return ResponseEntity.ok(all);
    }
    //Lấy tất cả danh sách VehicleSpec của 1 xe
    @GetMapping("/{VehicleId}")
    public ResponseEntity<List<VehicleSpecDTO>> getVehicleSpecByVehicleId(@PathVariable Long VehicleId){
        List<VehicleSpecDTO> spec =  vehicleSpecService.findVehicleSpecByVehicleId(VehicleId);
        return ResponseEntity.ok(spec);
    }
    //Tạo mới VehicleSpec
    @PostMapping("/{VehicleId}/specs")
    public ResponseEntity<VehicleSpecDTO> createVehicleSpec(@PathVariable Long VehicleId, @RequestBody VehicleSpecDTO vehicleSpecDTO){
        VehicleSpecDTO newSpec = vehicleSpecService.createSpec(VehicleId, vehicleSpecDTO);
        return ResponseEntity.ok(newSpec);
    }
    //Cập nhật VehicleSpec
    @PutMapping("/{VehicleId}/specs/{SpecId}")
    public ResponseEntity<VehicleSpecDTO> updateVehicleSpec(@PathVariable Long VehicleId, @PathVariable Long SpecId, @RequestBody VehicleSpecDTO vehicleSpecDTO){
        VehicleSpecDTO update = vehicleSpecService.updateVehicleSpec(VehicleId, SpecId, vehicleSpecDTO);
        return ResponseEntity.ok(update);
    }
    //Delele 1 VehicleSpec
    @DeleteMapping("/{VehicleId}/specs/{SpecId}")
    public ResponseEntity<Void> deleteVehicleSpec(@PathVariable Long VehicleId, @PathVariable Long SpecId){
        vehicleSpecService.DeleteVehicleSpec(VehicleId, SpecId);
        return ResponseEntity.ok().build();
    }

}
