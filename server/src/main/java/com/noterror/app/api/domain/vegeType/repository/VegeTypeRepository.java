package com.noterror.app.api.domain.vegeType.repository;

import com.noterror.app.api.domain.entity.VegeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VegeTypeRepository extends JpaRepository<VegeType, Long> {
  @Query(nativeQuery = true,
          value = "select * from vege_type where VEGE_TYPE_LEVEL < " +
                  "(select VEGE_TYPE_LEVEL from vege_type where vege_type_name = :vegeTypeName) or vege_type_name = :vegeTypeName")
  List<VegeType> findVegeTypes(@Param("vegeTypeName") String vegeTypeName);
}
