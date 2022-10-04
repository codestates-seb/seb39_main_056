import * as Styled from './style';

export const ProductInfoContainer = ({ productData, count, setCount }) => {
  const plusCount = () => {
    if (count < productData?.quantity) setCount(prev => prev + 1);
  };
  const minusCount = () => {
    if (count === 0) return;
    setCount(prev => prev - 1);
  };
  return (
    <Styled.ProductInfoContainer>
      <Styled.ProductImgBox>
        <Styled.ProductImg src={productData?.thumbnailImage} />
      </Styled.ProductImgBox>
      <Styled.ProductDesc>
        <Styled.ProductTitle>{productData?.productName}</Styled.ProductTitle>
        {/* <Styled.ProductContent>제품설명: 제품 한줄 설명</Styled.ProductContent> */}
        <Styled.ProductDescBox>
          <Styled.ProductContent>
            가격 : {productData?.price}원
          </Styled.ProductContent>
          <Styled.ProductContent>
            재고수: {productData?.quantity}개
          </Styled.ProductContent>
          <Styled.ProductContent>
            수량: <button onClick={minusCount}>-</button>
            {count} 개<button onClick={plusCount}>+</button>
          </Styled.ProductContent>
        </Styled.ProductDescBox>
      </Styled.ProductDesc>
    </Styled.ProductInfoContainer>
  );
};
