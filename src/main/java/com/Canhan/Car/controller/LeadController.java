package com.Canhan.Car.controller;

import com.Canhan.Car.dto.TestDriveInfoDTO;
import com.Canhan.Car.dto.TestDriveRequestDTO;
import com.Canhan.Car.model.entity.TestDrive;
import com.Canhan.Car.service.LeadService;
import com.Canhan.Car.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test-drives")
@CrossOrigin(origins = "http://localhost:5173")
public class LeadController {
    private final LeadService leadService;

    public  LeadController(LeadService leadService) {
        this.leadService = leadService;
    }
    //API nhận form đăng kí lái thử
    // - Endpont: POST /api/v1/test-drive
    // - React gọi khi gửi dùng nhấn nút gửi trên testDriveForm
    // @param testDriveRequest dữ liệu JSON từ form
    @PostMapping("/form")
    public ResponseEntity<Void> submitTestDrive(@Valid @RequestBody TestDriveRequestDTO req) {
        // @Valid: Tự động kích hoạt các luật validation trong TestDriveRequestDTO
        // (như @NotBlank, @Email). Nếu vi phạm, Spring sẽ tự trả về lỗi 400 Bad Request.

        // @RequestBody: Bảo Spring lấy JSON từ body của request
        // và chuyển nó thành đối tượng TestDriveRequestDTO.
        leadService.createTestDrive(req);
        // Trả về 201 Created (nghĩa là "Đã tạo thành công")
        // không cần body, vì React chỉ cần biết là đã thành công.
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    //Hiển thị danh sách đăng kí lái thử
    @GetMapping
    public ResponseEntity<List<TestDriveInfoDTO>> getAllTestDrives() {
        List<TestDriveInfoDTO> testDrives = leadService.GetAllTestDrives();
        return ResponseEntity.ok(testDrives);
    }
}
