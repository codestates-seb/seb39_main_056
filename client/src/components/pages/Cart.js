import axios from 'axios';
import React, { useEffect, useState } from 'react';
import CartTemplate from '../templates/Cart/Cart';

const Cart = () => {
  const [cart, setCart] = useState([]);
  const id = 1;

  const getCartInfo = () => {
    axios({
      method: 'get',
      url: `http://localhost:4000/cartProducts`,
    }).then(res => {
      if (res.status === 200) {
        // console.log(res.data);
        setCart(res.data);
      }
    });
  };

  const deleteCartItem = id => {
    axios({
      method: 'delete',
      url: '',
    }).then(res => {
      if (res.statusText === 'OK') {
        getCartInfo();
      }
    });
  };

  useEffect(() => {
    getCartInfo();
  }, []);

  return <CartTemplate cart={cart} deleteCartItem={deleteCartItem} />;
};

export default Cart;
