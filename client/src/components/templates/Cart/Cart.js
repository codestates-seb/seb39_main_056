import React from 'react';
import * as Styled from './Style';
import CartList from '../../organism/CartList';

const CartTemplate = ({ cart, deleteCartItem }) => {
  return (
    <Styled.CartContainer>
      <Styled.CartTitle></Styled.CartTitle>
      <CartList cart={cart} deleteCartItem={deleteCartItem}></CartList>
      <Styled.TotalPrice></Styled.TotalPrice>
      <Styled.BuyBtn>결제하기</Styled.BuyBtn>
    </Styled.CartContainer>
  );
};

export default CartTemplate;
