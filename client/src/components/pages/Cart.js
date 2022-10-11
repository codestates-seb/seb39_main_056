import axios from 'axios';
import React, { useEffect, useState } from 'react';
import CartTemplate from '../templates/Cart/Cart';
import { setTokenHeader } from '../../service/setTokenHeader';
import { useNavigate } from 'react-router-dom';

const Cart = () => {
  const [cart, setCart] = useState([]);
  const token = setTokenHeader();
  const navigate = useNavigate();

  const getCartInfo = () => {
    axios({
      method: 'get',
      url: `${process.env.REACT_APP_API_URL}/cart`,
      headers: { ...token },
    }).then(res => {
      if (res.status === 200) {
        setCart(res.data.carts);
      }
    });
  };

  const deleteCartItem = id => {
    axios({
      method: 'delete',
      url: `${process.env.REACT_APP_API_URL}/cart/${id}`,
      headers: {
        ...token,
      },
    }).then(res => {
      if (res.status === 204) {
        getCartInfo();
      }
    });
  };
  const fixCartItemQuantity = (id, quantity) => {
    axios({
      method: 'put',
      url: `${process.env.REACT_APP_API_URL}/cart`,
      headers: {
        ...token,
        'Content-type': 'application/json',
      },
      data: JSON.stringify({
        cartDetailId: id,
        purchaseQuantity: quantity,
      }),
    }).then(res => {
      if (res.status === 200) {
        getCartInfo();
      }
    });
  };

  const orderCart = () => {
    axios({
      method: 'post',
      url: `${process.env.REACT_APP_API_URL}/orders/cart`,
      headers: {
        ...token,
      },
    }).then(res => {
      if (res.status === 200) {
        alert('구매가 완료됐습니다.');
        navigate('/mypage/history');
      }
    });
  };

  useEffect(() => {
    getCartInfo();
  }, []);

  return (
    <CartTemplate
      cart={cart}
      deleteCartItem={deleteCartItem}
      fixCartItemQuantity={fixCartItemQuantity}
      orderCart={orderCart}
    />
  );
};

export default Cart;
