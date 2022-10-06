import React from 'react';
import * as Styled from './Style';

const CartItem = ({ el, deleteCartItem }) => {
  const { productName, price, count } = el;
  return (
    <Styled.ItemWrapper>
      <Styled.ImgBox></Styled.ImgBox>
      <Styled.InfoBox>
        <Styled.ItemName>품명 : {productName}</Styled.ItemName>
        <Styled.ItemPrice>가격 : {price}</Styled.ItemPrice>
        <Styled.ItemName>구매수량 : {count}</Styled.ItemName>
      </Styled.InfoBox>
    </Styled.ItemWrapper>
  );
};

export default CartItem;
