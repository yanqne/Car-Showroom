package com.Canhan.Car.model.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "colors")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
) // <-- Giải pháp chống lặp
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80, nullable = false)
    private String name;

    @Column(name = "hex", length = 7)
    private String hexValue;

    // Quan hệ: Một Color có thể xuất hiện ở nhiều VehicleColor
    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VehicleColor> vehicleColors;
}