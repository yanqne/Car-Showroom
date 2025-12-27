package com.Canhan.Car.controller;

import com.Canhan.Car.dto.BrandDTO;
import com.Canhan.Car.model.entity.Brand;
import com.Canhan.Car.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/brands")
public class BrandController {
    public final BrandService brandService;
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    //Lấy cả
    @GetMapping
    public ResponseEntity<List<BrandDTO>> getALl() {
        return ResponseEntity.ok().body(brandService.findAll());

    }
    //Lấy 1
    @GetMapping("/{id}")
    public ResponseEntity<Optional<BrandDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(brandService.findById(id));
    }
    @PostMapping
    public ResponseEntity<BrandDTO> create(@RequestBody BrandDTO dto) {
        return ResponseEntity.ok().body(brandService.save(dto));
    }
    //Xoá
    @DeleteMapping("/{id}")
    public void  delete(@PathVariable Long id) {
        brandService.delete(id);
    }
    //Update
    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> update(@PathVariable Long id, @RequestBody BrandDTO dto) {
        return ResponseEntity.ok().body(brandService.Update(dto, id));
    }
}
