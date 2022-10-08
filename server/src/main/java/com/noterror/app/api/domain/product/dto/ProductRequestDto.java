package com.noterror.app.api.domain.product.dto;

import com.noterror.app.api.entity.VegetarianType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "이름을 입력해주세요.")
    @Length(max=50)
    private String productName;

    @Min(value = 1, message = "수량은 최소 1개 이상이어야 합니다.")
    @Positive
    private int StockQuantity;

    @Min(value = 100, message = "금액은 100원 이상부터 등록가능합니다.")
    @Positive
    private int price;

    @NotBlank(message = "썸네일 이미지를 첨부해주세요.")
    private String thumbnailImage;

    @NotBlank(message = "상세 설명 이미지를 첨부해주세요.")
    private  String detailImage;

    @NotBlank(message = "제품이 해당하는 채식 유형을 선택해주세요.")
    private String vegetarianType;
}
