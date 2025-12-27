package com.Canhan.Car.dto;

import com.Canhan.Car.model.entity.Vehicle;
import com.Canhan.Car.model.entity.VehicleSpec;
import lombok.Data;

@Data
public class VehicleSpecDTO {
    private Long Id;
    private String specKey;   // Ví dụ: "Dài x Rộng x Cao"
    private String specValue; // Ví dụ: "4885 x 1840 x 1445 (mm)"
    private int sortOrder;
    private Long VehicleId;
}
