package com.noterror.app.api.domain.vegetarian.controller;


//유형 컨트롤러 양식(보존용)
/*

import com.noterror.app.api.domain.entity.VegeType;
import com.noterror.app.api.domain.vegeType.dto.VegeTypeRequestDto;
import com.noterror.app.api.domain.vegeType.dto.VegeTypeResponseDto;
import com.noterror.app.api.domain.vegeType.service.VegeTypeService;
import com.noterror.app.api.global.response.ListVegeTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class VegeTypeController {
    private final VegeTypeService vegeTypeService;

    @GetMapping
    public ResponseEntity getVegeTypes(@RequestParam(value = "vegeTypeName") String vegeTypeName) {
        List<VegeTypeResponseDto> findVegeTypes = vegeTypeService.getVegeTypes(vegeTypeName);
        return new ResponseEntity(findVegeTypes,
                HttpStatus.OK);
    }
}

 */
