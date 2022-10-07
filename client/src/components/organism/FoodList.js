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

const FoodList = () => {
  const token = setTokenHeader;
  const [cardList, setCardList] = useState([]);
  const getProducts = async () => {
    let url = `${process.env.REACT_APP_API_URL}/products/list`;
    // axios로 get요청하기
    axios
      .get(url, { header: { ...token } })
      .then(res => setCardList(res.data.products));
  };
  useEffect(() => {
    getProducts();
  }, []);

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
