import * as Styled from './style';
import { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';

export const ProductInfoContainer = ({
  productData,
  quantity,
  setQuantity,
  productId,
}) => {
  const productListData = useSelector(state => state.productDataReducer);
  const [thumnailImg, setThumnailImg] = useState();
  useEffect(() => {
    const filtered = productListData?.filter(
      el => el.productId === Number(productId),
    );
    setThumnailImg(filtered[0]?.thumbnailImage);
    console.log('2지워주세요');
  }, [productListData]);

  const plusCount = () => {
    if (quantity < productData?.stockQuantity) {
      setQuantity(prev => prev + 1);
    } else {
      alert('재고수를 초과할 수 없습니다');
    }
  };
  const minusCount = () => {
    if (quantity === 0) return alert('수량을 선택하세요');
    setQuantity(prev => prev - 1);
  };
  return (
    <Styled.ProductInfoContainer>
      <Styled.ProductImgBox>
        <Styled.ThumbnailImage src={thumnailImg} />
      </Styled.ProductImgBox>
      <Styled.ProductDesc>
        <Styled.ProductTitle>{productData?.productName}</Styled.ProductTitle>
        <Styled.ProductDescBox>
          <Styled.ProductContent>
            가격 : {productData?.price}원
          </Styled.ProductContent>
          <Styled.ProductContent>
            재고수: {productData?.stockQuantity}개
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
