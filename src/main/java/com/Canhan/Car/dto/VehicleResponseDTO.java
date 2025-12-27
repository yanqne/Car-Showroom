package com.Canhan.Car.dto;

import com.Canhan.Car.model.enums.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {
    private Long id;
    private String name;
    private String variant;
    private String drivetrain;
    private String transmission;
    private String engine;
    private Integer torqueNm;
    private String fuelConsumption;
    private Integer powerHp;
    private Integer trunkL;
    private String description;
    private Integer year;
    private Integer seats;
    private String dimensions;
    private String thumbnailUrl;
    private Long priceVnd;
    private String slug;
    private VehicleStatus Status;
    // thông tin từ model & brand
    private Long modelId;
    private String modelName;
    private Long brandId;
    private String brandName;
}
