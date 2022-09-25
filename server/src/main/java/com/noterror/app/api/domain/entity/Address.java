package com.noterror.app.api.domain.entity;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
public class Address {

    private String zipcode;
    private String city;
    private String detailAddress;

    protected Address() {}

    public Address(String zipcode, String city, String detailAddress) {
        this.zipcode = zipcode;
        this.city = city;
        this.detailAddress = detailAddress;
    }
}
