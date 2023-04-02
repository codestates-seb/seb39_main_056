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
        setCart(res.data.cart.cartDetails);
      }
    });
  };

  const deleteCartItem = id => {
    axios({
      method: 'delete',
      url: `${process.env.REACT_APP_API_URL}/cart/cart-detail/${id}`,
      headers: {
        ...token,
      },
    }).then(res => {
      if (res.status === 204) {
        getCartInfo();
      }
    })
  };
  const fixCartItemQuantity = (id, quantity) => {
    axios({
      method: 'put',
      url: `${process.env.REACT_APP_API_URL}/cart/cart-detail/${id}`,
      headers: {
        ...token,
        'Content-type': 'application/json',
      },
      data: JSON.stringify({
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
      url: `${process.env.REACT_APP_API_URL}/order/cart`,
      headers: {
        ...token,
      },
    }).then(res => {
      if (res.status === 200) {
        alert('구매가 완료됐습니다.');
        navigate('/mypage/history');
      }
    }).catch(e => {
      alert('죄송합니다. 현재 장바구니 내에 재고 수량보다 많은 제품이 있습니다.');
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
