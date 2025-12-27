package com.Canhan.Car.model.entity;

import com.Canhan.Car.model.enums.TestDriveStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "test_drives")
@Getter
@Setter
// Không cần @JsonIdentityInfo vì nó là "lá"
public class TestDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preferred_at")
    private LocalDate preferredDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private TestDriveStatus status;

    @Column(length = 300)
    private String note;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    // Quan hệ: Lịch này của Lead nào
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;

    // Quan hệ: Lịch này lái thử xe nào
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
}
