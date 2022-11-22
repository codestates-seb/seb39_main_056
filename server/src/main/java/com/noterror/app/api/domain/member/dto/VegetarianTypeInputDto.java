package com.noterror.app.api.domain.member.dto;

import lombok.Getter;

@Getter
public class VegetarianTypeInputDto {
    String vegetarianType;

    public VegetarianTypeInputDto(String vegetarianType) {
        this.vegetarianType = vegetarianType;
    }
}
