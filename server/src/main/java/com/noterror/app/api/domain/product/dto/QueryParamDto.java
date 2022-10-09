package com.noterror.app.api.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QueryParamDto {
    int page;
    int size;
    String sort;
    String orderBy;
    String vegetarianTypeName;
}
