package com.noterror.app.api.domain.vegetarian.dto;

import com.noterror.app.api.entity.VegetarianType;
import lombok.Getter;

@Getter
public class VegetarianTypeResponseDto {
    private String vegetarianName;

    public VegetarianTypeResponseDto(VegetarianType vegetarianType) {
        this.vegetarianName = vegetarianType.getVegetarianTypeName();
    }
}
