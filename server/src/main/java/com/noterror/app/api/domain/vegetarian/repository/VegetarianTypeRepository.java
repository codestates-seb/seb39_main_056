package com.noterror.app.api.domain.vegetarian.repository;

import com.noterror.app.api.entity.VegetarianType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VegetarianTypeRepository extends JpaRepository<VegetarianType, String> {
  @Query(nativeQuery = true,
          value = "select * from vegetarian_type where VEGETARIAN_TYPE_LEVEL < " +
                  "(select VEGETARIAN_TYPE_LEVEL from vegetarian_type where vegetarian_type_name = :vegetarianTypeName) or vegetarian_type_name = :vegetarianTypeName")
  List<VegetarianType> findVegetarianTypes(@Param("vegetarianTypeName") String vegetarianTypeName);
}
