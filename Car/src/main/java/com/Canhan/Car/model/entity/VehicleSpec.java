package com.Canhan.Car.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicle_specs")
@Getter
@Setter
// Không cần @JsonIdentityInfo vì nó là "lá" (leaf)
public class VehicleSpec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "spec_key")
    private String specKey;
    @Lob
    @Column(name = "spec_value", columnDefinition = "LONGTEXT")
    private String specValue;

    @Column(name = "sort_order")
    private Integer sortOrder;

    // Quan hệ: Nhiều Spec thuộc về một Vehicle
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
}
