package com.noterror.app.api.domain.entity.order;

import lombok.Getter;

public enum OrdersStatus {
    ORDER_REQUEST(1, "주문 요청"),
    ORDER_CONFIRM(2, "주문 확정"),
    ORDER_COMPLETE(3, "주문 처리 완료"),
    ORDER_CANCEL(4, "주문 취소");

    @Getter
    private int stepNumber;

    @Getter
    private String stepDescription;

    OrdersStatus(int stepNumber, String stepDescription) {
        this.stepNumber = stepNumber;
        this.stepDescription = stepDescription;
    }
}
