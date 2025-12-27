package com.Canhan.Car.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicle_colors")
@Getter
@Setter
// Không cần @JsonIdentityInfo vì nó là bảng nối
public class VehicleColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_default")
    private boolean isDefault;

    // Quan hệ: Liên kết tới Vehicle
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    // Quan hệ: Liên kết tới Color
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;
}
