import * as Styled from './style';
import { ProductInfoContainer } from '../../molecule/ProductDetail/index';
import { setTokenHeader } from '../../../service/setTokenHeader';
import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export const ProductPage = ({ productId, productData }) => {
  const navigate = useNavigate();
  const [quantity, setQuantity] = useState(1);
  const tokenHeader = setTokenHeader();
  const url = `${process.env.REACT_APP_API_URL}/cart/product`;
  //장바구니에 담기 눌렀을때
  const AddToCartProduct = async () => {
    if (productData.stockQuantity < quantity) {
      alert(
        `장바구니에 담을 수 있는 최대 재고량은 ${productData.stockQuantity}입니다.`,
      );
    } else {
      try {
        fetch(url, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            ...tokenHeader,
          },
          body: JSON.stringify({
            productId,
            purchaseQuantity: quantity,
          }),
        })
          .then(response => {
            if (!response.ok) throw new Error('No Response');
            console.log(response.json());
            response.json();
          })
          .then(data => {
            alert('장바구니에 추가되었습니다!');
            window.location.reload();
          });
      } catch (e) {
        console.log(e.message);
      }
    }
  };

  const BuyProduct = () => {
    if (productData.stockQuantity < quantity) {
      alert(
        `현재 구매 가능한 최대 재고량은 ${productData.stockQuantity}입니다.`,
      );
    } else {
      axios({
        method: 'post',
        url: `${process.env.REACT_APP_API_URL}/orders`,
        headers: {
          Accept: 'application/json',
          'Content-type': 'application/json',
          ...tokenHeader,
        },
        data: JSON.stringify({
          productId,
          ordersQuantity: quantity,
        }),
      }).then(res => {
        if (res.status === 200) {
          alert('주문이 완료되었습니다!');
          navigate('/mypage/history');
        } else {
          alert('다시 한 번 시도해주세요!');
        }
      });
    }
  };

  return (
    <>
      <Styled.CategoryPageTitle>Home {'>'} </Styled.CategoryPageTitle>
      <Styled.CategoryPageSubTitle> Food Category</Styled.CategoryPageSubTitle>
      <Styled.CategoryPageBox>
        <ProductInfoContainer
          productData={productData}
          quantity={quantity}
          setQuantity={setQuantity}
        />
        <Styled.BtnBlock>
          {productData !== undefined && productData.stockQuantity === 0 ? (
            <Styled.SoldOutBtn disabled>품절</Styled.SoldOutBtn>
          ) : (
            <Styled.CartBtn onClick={AddToCartProduct}>
              장바구니
            </Styled.CartBtn>
          )}
          {productData !== undefined && productData.stockQuantity === 0 ? (
            <Styled.SoldOutBtn disabled>품절</Styled.SoldOutBtn>
          ) : (
            <Styled.BuyBtn onClick={BuyProduct}>구매하기</Styled.BuyBtn>
          )}
        </Styled.BtnBlock>
        <Styled.Space></Styled.Space>
        <Styled.DetailedProductTitle>제품 정보 자세히 보기</Styled.DetailedProductTitle>
        <Styled.DetailedProductDesc src={productData?.detailImage} />
      </Styled.CategoryPageBox>
    </>
  );
};
