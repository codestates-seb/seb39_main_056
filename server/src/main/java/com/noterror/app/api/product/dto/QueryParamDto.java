package com.noterror.app.api.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryParamDto {
    int page;
    int size;
    String sort;
    String orderBy;
    String vegetarian;
}
