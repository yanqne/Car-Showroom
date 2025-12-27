package com.Canhan.Car.service;

import com.Canhan.Car.dto.BrandDTO;
import com.Canhan.Car.exception.ResourceNotFoundException;
import com.Canhan.Car.model.entity.Brand;
import com.Canhan.Car.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    //Lấy tất cả
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    //Lấy 1
    public Optional<BrandDTO> findById(Long id) {
        return brandRepository.findById(id).map(this::mapToDTO);
    }
    //Tạo mới
    public BrandDTO save(BrandDTO dto) {
        Brand brand = new Brand();
        brand.setName(dto.getName());
        brand.setSlug(dto.getSlug());
        brandRepository.save(brand);
        return mapToDTO(brand);
    }
    //Cập nhật
    public BrandDTO Update(BrandDTO brandDTO, Long id) {
        Brand find = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Brand có Id: " + id));
        mapToBrand(brandDTO, find);
        return mapToDTO(brandRepository.save(find));
    }
    //Xoá
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }
    public BrandDTO mapToDTO(Brand brand) {
        BrandDTO dto = new BrandDTO();
        dto.setId(brand.getId());
        dto.setName(brand.getName());
        dto.setSlug(brand.getSlug());
        return dto;
    }
    public void mapToBrand(BrandDTO dto, Brand brand) {
        brand.setName(dto.getName());
        brand.setSlug(dto.getSlug());
    }
}
