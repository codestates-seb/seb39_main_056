import * as Styled from './style';
import { ProductInfoContainer } from '../../molecule/ProductDetail/index';
import { useState } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

export const ProductPage = ({ productId, productData }) => {
  const loginState = useSelector(state => state.loginReducer);
  const navigate = useNavigate();

  const [quantity, setQuantity] = useState(0);
  const token = localStorage.getItem('JWT TOKEN');
  const url = `${process.env.REACT_APP_API_URL}/cart`;
  //장바구니에 담기 눌렀을때
  const BuyProduct = async () => {
    if (quantity === 0) return alert('수량을 선택하세요');
    if (!loginState) navigate('/login');
    try {
      fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          productId,
          purchaseQuantity: quantity,
        }),
      }).then(response => {
        if (!response.ok) throw new Error('No Response');
      });
    } catch (e) {
      console.log(e.message);
      navigate('/noresponse');
    }
  };

  return (
    <>
      <Styled.CategoryPageTitle>Home > Food Category</Styled.CategoryPageTitle>
      <Styled.CategoryPageBox>
        <ProductInfoContainer
          productData={productData}
          quantity={quantity}
          setQuantity={setQuantity}
          productId={productId}
        />
        <Styled.MoveToCartBtn onClick={BuyProduct}>
          장바구니에 담기
        </Styled.MoveToCartBtn>
        <Styled.DetailedProductDesc src={productData?.detailImage} />
      </Styled.CategoryPageBox>
    </>
  );
};
