package com.noterror.app.api.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListVegeTypeResponse<T> {
    List<T> vegeTypeList;
}
