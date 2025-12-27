package com.Canhan.Car.dto;

import com.Canhan.Car.model.enums.VehicleStatus;
import lombok.Data;
import java.util.List;
import java.util.Set;

@Data
public class VehicleDetailDTO {

    // Thông tin cơ bản
    private Long id;
    private String slug;
    private String modelName;
    private String variant;
    private Integer year;
    private Long priceVnd;
    private String description;

    // Thông tin từ Model và Brand
    private String modelSlug;
    private String brandName;
    private String brandSlug;

    // Các thông số chi tiết
    private String drivetrain;
    private String transmission;
    private String engine;
    private Integer powerHp;
    private Integer torqueNm;
    private String fuelConsumption;
    private Integer seats;
    private String dimensions;
    private Integer trunkL;
    private VehicleStatus status;

    // DANH SÁCH CÁC ĐỐI TƯỢNG LỒNG NHAU (Dùng DTO đã định nghĩa)

    private List<VehicleImageDTO> images;
    private List<VehicleSpecDTO> specs;
    private Set<VehicleColorDTO> colors;
}
