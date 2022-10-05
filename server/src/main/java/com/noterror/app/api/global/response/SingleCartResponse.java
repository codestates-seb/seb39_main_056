package com.noterror.app.api.global.response;

import lombok.Getter;

@Getter
public class SingleCartResponse<T>  {

        private T cartDetail;

        public SingleCartResponse(T cartDetail){
            this.cartDetail = cartDetail;
        }

    }
