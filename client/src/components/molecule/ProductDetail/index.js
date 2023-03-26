import * as Styled from './style';

export const ProductInfoContainer = ({
  productData,
  quantity,
  setQuantity,
}) => {
  const plusCount = () => {
    // if (quantity < productData?.stockQuantity)
    setQuantity(prev => prev + 1);
  };
  const minusCount = () => {
    if (quantity === 1) return;
    setQuantity(prev => prev - 1);
  };

  return (
    <Styled.ProductInfoContainer>
      <Styled.ProductImgBox>
        <Styled.ProductImg src={productData?.thumbnailImage} />
      </Styled.ProductImgBox>
      <Styled.ProductDesc>
        <Styled.ProductBadge>{productData?.vegetarianType}</Styled.ProductBadge>
        <Styled.ProductTitle>{productData?.productName}</Styled.ProductTitle>
        <Styled.ProductDescBox>
        <Styled.ProductContent>
          <Styled.ProductContentField>상품가격</Styled.ProductContentField>
          <Styled.ProductContentValue>{productData?.price}원</Styled.ProductContentValue>
        </Styled.ProductContent>
        <Styled.ProductContent>
          <Styled.ProductContentField>섭취가능유형</Styled.ProductContentField>
          <Styled.ProductContentValue>플렉시테리언, 폴로-페스코, 페스코, 폴로, 락토-오보, 락토, 오보, 비건, 프루테리언</Styled.ProductContentValue>
        </Styled.ProductContent>

        <Styled.ProductContentSpace></Styled.ProductContentSpace>

        <Styled.ProductContent>
        <Styled.ProductContentField>구매수량</Styled.ProductContentField>
          <Styled.ProductPurchaseCountBox>
            <button onClick={minusCount}>-</button>
            {quantity} 개
            <button onClick={plusCount}>+</button>
          </Styled.ProductPurchaseCountBox>
        </Styled.ProductContent>
        </Styled.ProductDescBox>
      </Styled.ProductDesc>
    </Styled.ProductInfoContainer>
  );
};
