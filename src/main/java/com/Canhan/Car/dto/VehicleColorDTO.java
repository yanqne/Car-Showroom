package com.Canhan.Car.dto;

import lombok.Data;

@Data
public class VehicleColorDTO {
    private String name;     // Tên màu, ví dụ: "Trắng Ngọc Trai"
    private String hexValue; // Mã hex, ví dụ: "#FFFFFF"
    private boolean isDefault; // Có phải màu mặc định không
}