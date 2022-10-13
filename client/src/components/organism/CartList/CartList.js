import React from 'react';
import CartItem from '../CartItem/CartItem';
import * as Styled from './style';
import { NoResult } from '../../molecule/NoResult';

const CartList = ({ cart, deleteCartItem, fixCartItemQuantity }) => {
  return (
    <Styled.CartList>
      {cart.length === 0 ? (
        <NoResult>현재 장바구니가 비어있어요. </NoResult>
      ) : (
        cart.map((el, idx) => {
          return (
            <CartItem
              el={el}
              key={idx}
              deleteCartItem={deleteCartItem}
              fixCartItemQuantity={fixCartItemQuantity}
            />
          );
        })
      )}
    </Styled.CartList>
  );
};

export default CartList;
