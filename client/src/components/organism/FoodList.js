import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import FoodCard from './FoodCard';
import axios from 'axios';
import { setTokenHeader } from '../../service/setTokenHeader';

const Container = styled.div`
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
`;

const FoodList = ({
  searchParams,
  setPages,
  assortArr,
  setAssortArr,
  changeAssortArr,
  setCurrentType
}) => {
  const selectedPage =
    searchParams.get('page') === null ? '' : `page=${searchParams.get('page')}`;
  const selectedSize =
    searchParams.get('size') === null ? '' : `size=${searchParams.get('size')}`;
  const selectedSort =
    searchParams.get('sort') === null ? '' : `sort=${searchParams.get('sort')}`;
  const selectedOrderBy =
    searchParams.get('orderBy') === null
      ? ''
      : `orderBy=${searchParams.get('orderBy')}`;
  const selectedVegetarian =
    searchParams.get('vegetarian') === null
      ? ''
      : `vegetarian=${searchParams.get('vegetarian')}`;

  const token = setTokenHeader();
  
  const [cardList, setCardList] = useState([]);
  const getProducts = () => {
    let url = `${process.env.REACT_APP_API_URL}/products/list?${selectedPage}&${selectedSize}&${selectedSort}&${selectedOrderBy}&${selectedVegetarian}`;
    // axios로 get요청하기
    axios
      .get(url, { headers: { ...token } }) //
      .then(res => {
        setCardList(res.data.products);
        setPages(res.data.pageInfo.totalPages);
        // setAssortArr(changeAssortArr(assortArr));
        const result = changeAssortArr(assortArr);
        setAssortArr(result);
        setCurrentType(res.data.pageInfo.currentType)
        console.log(assortArr);
      });
    // .then(data => {
    //   console.log(cardList);
    // });
  };
  useEffect(() => {
    getProducts();
  }, [
    selectedPage,
    selectedSize,
    selectedSort,
    selectedOrderBy,
    selectedVegetarian,
  ]);

  return (
    <>
      <Container>
        {cardList.map((card, i) => {
          return <FoodCard card={card} key={i} />;
        })}
      </Container>
    </>
  );
};

export default FoodList;
