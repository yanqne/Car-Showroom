package com.Canhan.Car.dto;

import com.Canhan.Car.model.enums.ModelSegment;
import lombok.Data;

@Data
public class ModelDTO {
    private Long id;
    private String name;
    private Long BrandId;
    private String BrandName;
    private ModelSegment segment;
    private String slug;
}
