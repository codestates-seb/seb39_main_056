package com.noterror.app.api.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListVegetarianTypeResponse<T> {
    List<T> vegetarianTypeList;
}
