package com.noterror.app.api.domain.vegetarian.repository;

import com.noterror.app.api.entity.VegetarianType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VegetarianTypeRepository extends JpaRepository<VegetarianType, String> {
  @Query(nativeQuery = true,
          value = "select vegetarian_type_name from vegetarian_type where vegetarian_type_level < " +
                  "(select vegetarian_type_level from vegetarian_type where vegetarian_type_name = :vegetarianTypeName) " +
                  "or vegetarian_type_name = :vegetarianTypeName")
  List<String> findVegetarianTypes(@Param("vegetarianTypeName") String vegetarianTypeName);
}
