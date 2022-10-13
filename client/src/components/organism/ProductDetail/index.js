import * as Styled from './style';
import { ProductInfoContainer } from '../../molecule/ProductDetail/index';
import { useState } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { PostData } from '../../../hooks/fetchApi';
export const ProductPage = ({ productId, productData }) => {
  const loginState = useSelector(state => state.loginReducer);
  const navigate = useNavigate();

  const [quantity, setQuantity] = useState(0);
  const token = localStorage.getItem('JWT TOKEN');
  const url = `${process.env.REACT_APP_API_URL}/cart`;
  //const url = 'http://localhost:3001/cart';

  //장바구니에 담기 눌렀을때
  const BuyProduct = async () => {
    if (quantity === 0) return alert('수량을 선택하세요');
    if (!loginState) return navigate('/login');
    PostData(
      url,
      {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
      {
        productId,
        purchaseQuantity: quantity,
      },
    ).then(result => {
      if (result === 'fail') {
        navigate('/noresponse');
      } else {
        alert('장바구니에 물건이 담겼습니다');
      }
    });
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
