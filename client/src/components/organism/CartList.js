import React from 'react';
import CartItem from './CartItem/CartItem';

const CartList = ({ cart, deleteCartITem }) => {
  return (
    <div>
      {cart.map((el, idx) => {
        return <CartItem el={el} key={idx} deleteCartITem={deleteCartITem} />;
      })}
    </div>
  );
};

export default CartList;
