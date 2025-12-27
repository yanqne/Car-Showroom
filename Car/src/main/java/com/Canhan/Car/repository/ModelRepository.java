package com.Canhan.Car.repository;

import com.Canhan.Car.dto.ModelDTO;
import com.Canhan.Car.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
//    Optional<Model> findBySlug(String slug);
//    Optional<ModelDTO> findById(Long id);
//    List<Model> findByBrandId(Long BrandId);

}
