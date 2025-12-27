package com.Canhan.Car.model.entity;

import com.Canhan.Car.model.enums.VehicleStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- CÁC CỘT THEO TABLE ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(name = "name", length = 200, nullable = false)
    private String name; // VD: "Mercedes-Benz C300 AMG"

    @Column(name = "variant", length = 160)
    private String variant; // VD: "AMG Line"

    @Column(name = "year", columnDefinition = "SMALLINT UNSIGNED")
    private Integer year;

    @Column(name = "drivetrain", length = 60)
    private String drivetrain; // AWD / RWD / FWD / 4x4

    @Column(name = "transmission", length = 60)
    private String transmission; // AT / MT / CVT...

    @Column(name = "engine", length = 120)
    private String engine; // VD: "2.0L Turbo"

    @Column(name = "power_hp", columnDefinition = "SMALLINT UNSIGNED")
    private Integer powerHp; // 245

    @Column(name = "torque_nm", columnDefinition = "SMALLINT UNSIGNED")
    private Integer torqueNm; // 370

    @Column(name = "fuel_consumption", length = 40)
    private String fuelConsumption; // VD: "7.2L/100km"

    @Column(name = "seats", columnDefinition = "TINYINT UNSIGNED")
    private Integer seats;

    @Column(name = "dimensions", length = 120)
    private String dimensions; // VD: "4750x1820x1440mm"

    @Column(name = "trunk_l", columnDefinition = "SMALLINT UNSIGNED")
    private Integer trunkL;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private VehicleStatus status = VehicleStatus.ACTIVE; // ACTIVE / INACTIVE / SOLD

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "thumbnail_url", length = 600)
    private String thumbnailUrl;

    @Column(name = "price_vnd", columnDefinition = "BIGINT UNSIGNED")
    private Long priceVnd;

    @Column(name = "slug", length = 200, nullable = false, unique = true)
    private String slug;

    // --- TIMESTAMPS ---
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    // --- QUAN HỆ ---
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    private List<VehicleSpec> specs;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    private List<VehicleImage> images;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VehicleColor> vehicleColors;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private Set<Lead> leads;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private Set<TestDrive> testDrives;
}
