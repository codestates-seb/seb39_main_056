import * as Styled from './style';
import { ProductInfoContainer } from '../../molecule/ProductDetail/index';
import { useState } from 'react';

export const ProductPage = ({ productId, productData }) => {
  const [count, setCount] = useState(0);
  const url = `${process.env.REACT_APP_API_URL}/${memberId}/cart`;
  //장바구니에 담기 눌렀을때
  const BuyProduct = async () => {
    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        productId,
        count,
      }),
    }).then(response => console.log(response.json()));
  };
  return (
    <>
      <Styled.CategoryPageTitle>Home > Food Category</Styled.CategoryPageTitle>
      <Styled.CategoryPageBox>
        <ProductInfoContainer
          productData={productData}
          count={count}
          setCount={setCount}
        />
        <Styled.MoveToCartBtn onClick={BuyProduct}>
          장바구니에 담기
        </Styled.MoveToCartBtn>
        <Styled.DetailedProductDesc src={productData?.detailImage} />
      </Styled.CategoryPageBox>
    </>
  );
};
