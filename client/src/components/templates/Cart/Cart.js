import React from 'react';
import * as Styled from './Style';
import CartList from '../../organism/CartList/CartList';

const CartTemplate = ({
  cart,
  deleteCartItem,
  fixCartItemQuantity,
  orderCart,
}) => {
  const handleClick = () => {
    orderCart();
  };
  return (
    <Styled.CartContainer>
      <Styled.Title>장바구니</Styled.Title>
      <Styled.Info>현재 장바구니에 담긴 물건들을 확인할 수 있어요.</Styled.Info>
      <CartList
        cart={cart}
        deleteCartItem={deleteCartItem}
        fixCartItemQuantity={fixCartItemQuantity}
        orderCart={orderCart}
      ></CartList>
      <Styled.TotalPrice></Styled.TotalPrice>
      <Styled.BuyBtn onClick={handleClick}>전체 주문하기</Styled.BuyBtn>
    </Styled.CartContainer>
  );
};

export default CartTemplate;
