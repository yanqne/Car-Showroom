package com.Canhan.Car.dto;

import com.Canhan.Car.model.enums.VehicleStatus;
import lombok.Data;

@Data
public class VehicleRequestDTO {

    private Long modelId;

    private String name;

    private String variant;

    private String drivetrain;

    private String transmission;

    private String engine;

    private Integer torqueNm;

    private String fuelConsumption;

    private Integer powerHp;

    private Integer trunkL;

    private VehicleStatus status;

    private String description;

    private Integer year;

    private Integer seats;

    private String dimensions;

    private String thumnailUrl;

    private Long priceVnd;

//    private String slug;

}
