package com.noterror.app.api.global.response;

import lombok.Getter;

@Getter
public class SingleMemberResponse<T> {
    T member;
    public SingleMemberResponse(T member) {
        this.member = member;
    }
}
