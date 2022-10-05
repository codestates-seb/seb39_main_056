package com.noterror.app.api.domain.vegeType.service;

import com.noterror.app.api.domain.vegeType.dto.VegeTypeResponseDto;

import java.util.List;

public interface VegeTypeService  {

    List<VegeTypeResponseDto> getVegeTypes(String vegeTypeName);
}
