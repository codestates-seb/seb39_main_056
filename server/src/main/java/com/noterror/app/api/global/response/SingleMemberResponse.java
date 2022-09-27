package com.noterror.app.api.global.response;

public class SingleMemberResponse<T> {
    private T member;
    public SingleMemberResponse(T member) {
        this.member = member;
    }
}
