package com.Canhan.Car.controller;

import com.Canhan.Car.dto.VehicleDetailDTO;
import com.Canhan.Car.dto.VehicleSummaryDTO;
import com.Canhan.Car.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    //API lấy danh sách tóm tắt tất cả các xe
    // - Endpoint: GET api/v1/vehicles
    // - React  gọi khi: tải trang danh sách xe ( VehiclelistPage)
    @GetMapping
    public ResponseEntity<List<VehicleSummaryDTO>> getAllVehicles() {
        List<VehicleSummaryDTO> vehicles = vehicleService.getAllVehiclesSummary();
        return ResponseEntity.ok(vehicles);
    }
    //API lấy thông tin chi tiết của 1 xe
    // - Endpoint: GET api/v1/vehicles/{slug}
    // - React gọi khi: tải trang chi tiết xe {VehicleDetailPage}
    // @param slug lấy tự động từ URL
    @GetMapping("/{slug}")
    public ResponseEntity<VehicleDetailDTO> getVehicleBySlug(@PathVariable String slug) {
        //Service sẽ tự động đưa ra ResourceNotFoundException(404) nếu không thấy slug
        VehicleDetailDTO vehicleDetail = vehicleService.getVehicleDetail(slug);
        return ResponseEntity.ok(vehicleDetail);

    }

}
