package com.noterror.app.api.domain.vegetarian.service;

import com.noterror.app.api.domain.vegetarian.dto.VegetarianTypeResponseDto;
import com.noterror.app.api.domain.vegetarian.repository.VegetarianTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VegetarianTypeServiceImpl implements VegetarianTypeService {
    private final VegetarianTypeRepository vegetarianTypeRepository;

    @Override
    public List<VegetarianTypeResponseDto> getVegetarianType(String vegetarianTypeName) {
        List<VegetarianTypeResponseDto> vegeTypes = vegetarianTypeRepository.findVegetarianTypes(vegetarianTypeName)
                .stream().map(VegetarianTypeResponseDto::new).collect(Collectors.toList());
        return vegeTypes;
    }
}