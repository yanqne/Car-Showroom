package com.Canhan.Car.dto;

import com.Canhan.Car.model.enums.TestDriveStatus;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class TestDriveInfoDTO {

    private Long id; // ID của lịch lái thử
    private TestDriveStatus status;
    private LocalDate preferredDate; // Ngày khách muốn lái
    private Timestamp createdAt; // Ngày khách đăng ký

    // --- Thông tin khách hàng (từ Lead) ---
    private Long vehicleId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;

    // --- Thông tin xe (từ Vehicle) ---
    private String vehicleName; // Tên xe
    private String vehicleSlug; // Để admin click vào xem chi tiết xe
}
