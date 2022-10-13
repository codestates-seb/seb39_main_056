package com.noterror.app.api.entity.member;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * JPA 내장 타입, 스펙상 생성
 *
 * @Field zipcode : 우편번호
 * @Field city : 도로명 및 도시 주소
 * @Field detailAddress : 상세주소
 */
@Embeddable
@Getter
public class Address {

    private String zipCode;

    private String city;

    private String detailAddress;

    protected Address() {
    }

    public Address(String zipCode, String city, String detailAddress) {
        this.zipCode = zipCode;
        this.city = city;
        this.detailAddress = detailAddress;
    }
}
