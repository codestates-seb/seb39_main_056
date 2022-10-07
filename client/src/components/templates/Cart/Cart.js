import React from 'react';
import * as Styled from './Style';
import CartList from '../../organism/CartList';

const CartTemplate = ({ cart, deleteCartItem }) => {
  return (
    <Styled.CartContainer>
      <Styled.CartTitle>장바구니</Styled.CartTitle>
      <CartList cart={cart} deleteCartItem={deleteCartItem}></CartList>
      <Styled.TotalPrice></Styled.TotalPrice>
      <Styled.BuyBtn></Styled.BuyBtn>
    </Styled.CartContainer>
  );
};

export default CartTemplate;
