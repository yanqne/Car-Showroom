package com.Canhan.Car.dto;

import com.Canhan.Car.model.enums.VehicleStatus;
import lombok.Data;

@Data
public class VehicleSummaryDTO {
    private Long id;
    private String name;
    private String slug;
    private Long priceVnd;         // Ví dụ: 1405000000
    private String thumbnailUri;   // Ảnh bìa
    private String brandName;      // Lấy từ Brand
    private Integer year;
    private VehicleStatus status;
}
