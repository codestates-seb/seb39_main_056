import React, { useState } from 'react';
import styled from 'styled-components';
import H2 from '../atom/H2';

const FoodCategoryBtn = styled.div`
  flex-grow: 1;
  width: 110px;
  padding: 10px 40px;
  border: none;
  cursor: pointer;
  color: #33272a;
  text-align: center;
  :hover {
    background-color: beige;
  }
  /* background-color: ${props => props.isClick || 'beige'}; */
`;

const FoodItem = ({ item }) => {
  const [isClick, setIsClick] = useState(true);
  const onClick = e => {
    setIsClick(!isClick);
    // console.log(e.target.textContent, isClick);
    alert(`${e.target.textContent} 카테고리 이동 기능은 추후 구현 예정입니다.`);
  };
  return (
    <FoodCategoryBtn onClick={onClick} isClick={isClick}>
      <H2>{item}</H2>
    </FoodCategoryBtn>
  );
};
export default FoodItem;
