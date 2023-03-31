package com.noterror.app.api.global.response;

import lombok.Getter;

@Getter
public class AiAnswerResponse {

    String answer;

    public AiAnswerResponse(String answer) {
        this.answer = answer;
    }
}
