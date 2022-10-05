package com.noterror.app.api.domain.vegeType.service;

import com.noterror.app.api.domain.vegeType.dto.VegeTypeResponseDto;
import com.noterror.app.api.domain.vegeType.repository.VegeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VegeTypeServiceImpl implements VegeTypeService {
    private final VegeTypeRepository vegeTypeRepository;

    @Override
    public List<VegeTypeResponseDto> getVegeTypes(String vegeTypeName) {
        List<VegeTypeResponseDto> vegeTypes = vegeTypeRepository.findVegeTypes(vegeTypeName)
                .stream().map(VegeTypeResponseDto::new).collect(Collectors.toList());
        return vegeTypes;
    }
}