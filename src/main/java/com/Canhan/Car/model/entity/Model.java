package com.Canhan.Car.model.entity;

import com.Canhan.Car.model.enums.ModelSegment;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "models")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
) // <-- Giải pháp chống lặp
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 160, nullable = false)
    private String name;

    @Column(length = 180, unique = true)
    private String slug;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private ModelSegment segment;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    // Quan hệ: Nhiều Model thuộc về một Brand
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    // Quan hệ: Một Model có nhiều Vehicle (phiên bản)
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Vehicle> vehicles;
}
