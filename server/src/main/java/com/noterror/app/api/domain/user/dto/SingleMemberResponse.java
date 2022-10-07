package com.noterror.app.api.domain.user.dto;

public class SingleMemberResponse<T> {
    T member;
    public SingleMemberResponse(T member) {
        this.member = member;
    }
}
