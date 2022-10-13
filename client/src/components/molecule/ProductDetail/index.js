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
        <Styled.ProductTitle>{productData?.productName}</Styled.ProductTitle>
        <Styled.ProductDescBox>
          <Styled.ProductContent>
            가격 : {productData?.price}원
          </Styled.ProductContent>
          <Styled.ProductContent>
            채식유형: {productData?.vegetarianType}
          </Styled.ProductContent>
          <Styled.ProductContent>
            수량: <button onClick={minusCount}>-</button>
            {quantity} 개<button onClick={plusCount}>+</button>
          </Styled.ProductContent>
        </Styled.ProductDescBox>
      </Styled.ProductDesc>
    </Styled.ProductInfoContainer>
  );
};
