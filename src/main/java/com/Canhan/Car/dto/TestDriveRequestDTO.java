package com.Canhan.Car.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TestDriveRequestDTO {
    @NotNull(message = "Id của xe không được để trống")
    private Long vehicleId;
    @NotBlank(message = "Họ tên không được để trống")
    private String fullname;
    @NotBlank(message = "SDT không được để trống")
    private String phone;
    @Email(message = "Email không đúng định dạng")
    @NotBlank(message = "Email không được để trống")
    private String email;
    @NotNull(message = "Ngày lái thử mong muốn không được để trống")
    private LocalDate preferredDate;
    private String message;

}
