package com.Canhan.Car.model.entity;


import com.Canhan.Car.model.enums.LeadSource;
import com.Canhan.Car.model.enums.LeadStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "leads")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
) // <-- Giải pháp chống lặp
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 140)
    private String fullName;

    @Column(length = 40)
    private String phone;

    @Column(length = 160)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private LeadSource source;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private LeadStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    // Quan hệ: Khách hàng này quan tâm xe nào (có thể null)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    // Quan hệ: Một Lead có thể có nhiều lịch lái thử
    @OneToMany(mappedBy = "lead", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TestDrive> testDrives;
}
