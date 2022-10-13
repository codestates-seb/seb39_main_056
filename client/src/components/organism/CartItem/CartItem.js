import React, { useState } from 'react';
import * as Styled from './Style';

const CartItem = ({ el, deleteCartItem, fixCartItemQuantity }) => {
  const { productName, price, purchaseQuantity, thumbnailImage, cartDetailId } =
    el;
  const [totalPrice, setTotalPrice] = useState(price * purchaseQuantity);

  const deleteItem = () => {
    deleteCartItem(cartDetailId);
  };

  const changeQuantity = e => {
    setTotalPrice(e.target.value * price);
  };

  const fixQuantity = e => {
    e.preventDefault();
    fixCartItemQuantity(cartDetailId, e.target[0].value);
  };

  return (
    <Styled.ItemWrapper>
      <Styled.ImgBox>
        <img src={thumbnailImage} alt="제품이미지" />
      </Styled.ImgBox>
      <Styled.InfoBox>
        <Styled.ItemName>{productName}</Styled.ItemName>
        <form onSubmit={e => fixQuantity(e)}>
          <Styled.ItemQuantity
            defaultValue={purchaseQuantity}
            type="number"
            onChange={changeQuantity}
          />
          <button>수량 저장하기</button>
        </form>
        <Styled.ItemPrice>{price}</Styled.ItemPrice>
        <Styled.TotalPrice>{totalPrice}</Styled.TotalPrice>
        {/* 수량은 버튼식으로 조정할 것 */}
      </Styled.InfoBox>
      <button onClick={deleteItem}>삭제하기</button>
    </Styled.ItemWrapper>
  );
};

export default CartItem;
