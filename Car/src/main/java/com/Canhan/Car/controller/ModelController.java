package com.Canhan.Car.controller;

import com.Canhan.Car.dto.ModelDTO;
import com.Canhan.Car.service.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/models")
public class ModelController {
    private final ModelService modelService;
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }
    @GetMapping
    public ResponseEntity<List<ModelDTO>> findAll() {
        return ResponseEntity.ok().body(modelService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ModelDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(modelService.findById(id));
    }
    @PostMapping
    public ResponseEntity<ModelDTO> save(@RequestBody ModelDTO dto) {
        return ResponseEntity.ok().body(modelService.add(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ModelDTO> update(@PathVariable Long id, @RequestBody ModelDTO dto) {
        return ResponseEntity.ok().body(modelService.update(dto, id));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        modelService.delete(id);
    }
}
