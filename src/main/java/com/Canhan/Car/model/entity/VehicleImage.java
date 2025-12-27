package com.Canhan.Car.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "vehicle_images")
@Getter
@Setter
// Không cần @JsonIdentityInfo vì nó là "lá"
public class VehicleImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 700, nullable = false)
    private String url;

    @Column(length = 200)
    private String alt;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    // Quan hệ: Nhiều Image thuộc về một Vehicle
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
}