import * as Styled from './style';
import { ProductInfoContainer } from '../../molecule/ProductDetail/index';

export const ProductPage = ({ productData }) => {
  const url = 'http://localhost:3001/products';
  //구매하기 눌렀을때
  const BuyProduct = async () => {
    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        id: '2',
      }),
    }).then(response => console.log(response.json()));
  };
  return (
    <>
      <Styled.CategoryPageTitle>Home > Food Category</Styled.CategoryPageTitle>
      <Styled.CategoryPageBox>
        <ProductInfoContainer productData={productData} />
        <Styled.MoveToCartBtn onClick={BuyProduct}>
          구매하기
        </Styled.MoveToCartBtn>
        <Styled.DetailedProductDesc src={productData?.detailImage} />
      </Styled.CategoryPageBox>
    </>
  );
};
