package com.noterror.app.api.domain.vegetarian.service;

import com.noterror.app.api.domain.vegetarian.dto.VegetarianTypeResponseDto;

import java.util.List;

public interface VegetarianTypeService {

    List<VegetarianTypeResponseDto> getVegetarianType(String vegetarianTypeName);
}
