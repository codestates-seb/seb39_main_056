package com.noterror.app.api.domain.vegeType.dto;

import com.noterror.app.api.domain.entity.VegeType;
import lombok.Getter;

@Getter
public class VegeTypeResponseDto {
    private String vegeTypeName;

    public VegeTypeResponseDto(VegeType vegeType) {
        this.vegeTypeName = vegeType.getVegeTypeName();
    }
}
