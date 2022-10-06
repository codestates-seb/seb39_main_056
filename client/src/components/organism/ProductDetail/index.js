import * as Styled from './style';
import { ProductInfoContainer } from '../../molecule/ProductDetail/index';
import { useState } from 'react';

export const ProductPage = ({ productId, productData }) => {
  const [quantity, setQuantity] = useState(0);
  const token = localStorage.getItem('JWT TOKEN');
  const url = `${process.env.REACT_APP_API_URL}/cart`;
  //장바구니에 담기 눌렀을때
  const BuyProduct = async () => {
    try {
      fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          productId,
          purchaseQuantity: quantity,
        }),
      }).then(response => {
        if (!response.ok) throw new Error('No Response');
        console.log(response.json());
      });
    } catch (e) {
      console.log(e.message);
    }
  };

  return (
    <>
      <Styled.CategoryPageTitle>Home > Food Category</Styled.CategoryPageTitle>
      <Styled.CategoryPageBox>
        <ProductInfoContainer
          productData={productData}
          quantity={quantity}
          setQuantity={setQuantity}
        />
        <Styled.MoveToCartBtn onClick={BuyProduct}>
          장바구니에 담기
        </Styled.MoveToCartBtn>
        <Styled.DetailedProductDesc src={productData?.detailImage} />
      </Styled.CategoryPageBox>
    </>
  );
};
