package com.noterror.app.api.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@AllArgsConstructor
public class ProductPatchDto {
    private Long productId;

    @NotBlank(message = "이름을 입력해주세요.")
    @Length(max=50)
    private String productName;

    @NotBlank(message = "가격을 입력해주세요.")
    @Min(100)
    private int price;

    @NotBlank(message = "수량을 입력해주세요.")
    @Min(1)
    private int quantity;

    @NotBlank(message = "썸네일 이미지를 첨부해주세요.")
    private String thumbnailImage;

    @NotBlank(message = "상세 설명 이미지를 첨부해주세요.")
    private String detailImage;

    public void setProductId(Long productId){
        this.productId = productId;
    }
}
