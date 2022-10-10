package com.noterror.app.api.domain.vegetarian.repository;

import com.noterror.app.api.entity.VegetarianType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface VegetarianTypeRepository extends JpaRepository<VegetarianType, String> {
}
