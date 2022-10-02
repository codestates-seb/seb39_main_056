package com.noterror.app.api.domain.vegetarianType.repository;

import com.noterror.app.api.domain.entity.VegetarianType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VegetarianTypeRepository extends JpaRepository<VegetarianType, String> {

    Optional<VegetarianType> findByVegetarianTypeName(String s);
}
