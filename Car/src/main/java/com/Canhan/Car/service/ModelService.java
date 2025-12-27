package com.Canhan.Car.service;

import com.Canhan.Car.dto.ModelDTO;
import com.Canhan.Car.exception.ResourceNotFoundException;
import com.Canhan.Car.model.entity.Brand;
import com.Canhan.Car.model.entity.Model;
import com.Canhan.Car.repository.BrandRepository;
import com.Canhan.Car.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ModelService {
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    public ModelService(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }
    //Lấy danh sách
    public List<ModelDTO> findAll() {
        return modelRepository.findAll().stream().map(this::maptoDTO).collect(Collectors.toList());
    }
    //Tìm kiếm 1 model
    public ModelDTO findById(Long id) {
        return maptoDTO(modelRepository.findById(id).get());
    }
    //Thêm 1 model
    public ModelDTO add(ModelDTO dto) {
        //Kiểm tra brand có tồn tại không
        Brand brand = brandRepository.findById(dto.getBrandId()).orElseThrow(() -> new ResourceNotFoundException("Không tồn tại brands với id: " + dto.getBrandId()));
        Model model = new Model();
        model.setBrand(brand);
        maptoModel(model, dto);
        modelRepository.save(model);
        return maptoDTO(model);
    }
    //Cập nhật 1 model
    public ModelDTO update(ModelDTO dto, Long id) {
        // Kiểm tra tồn tài 1 model
        Model find = modelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy model có id: " + id));
        if(!find.getBrand().getId().equals(dto.getBrandId())) {
            Brand findB = brandRepository.findById(dto.getBrandId()).orElseThrow(() -> new ResourceNotFoundException("Không tồn tại brand với id: " + dto.getBrandId()));
            find.setBrand(findB);
        }
        maptoModel(find, dto);
        modelRepository.save(find);
        return maptoDTO(find);
    }
    //Xoá 1 model
    public void  delete(Long id) {
        modelRepository.deleteById(id);
    }
    public ModelDTO maptoDTO(Model model) {
        ModelDTO dto = new ModelDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setSlug(model.getSlug());
        dto.setSegment(model.getSegment());
        if(model.getBrand() != null) {
            dto.setBrandId(model.getBrand().getId());
            dto.setBrandName(model.getBrand().getName());
        }
        return dto;
    }
    public void maptoModel(Model model, ModelDTO dto) {
        Brand brand = model.getBrand();
        model.setName(dto.getName());
        model.setSlug(dto.getSlug());
        model.setSegment(dto.getSegment());
        brand.setId(dto.getBrandId());
    }
}
