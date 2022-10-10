import React from 'react';
import styled from 'styled-components';
import { useState } from 'react';

const SortContainer = styled.form`
  display: flex;
  justify-content: end;
  margin: 20px;
  padding: 10px;
`;
const SortSelect = styled.select`
  padding: 10px;
  width: 130px;
  cursor: pointer;
  margin-right: 5px;
  border: 1px solid gray;
  border-radius: 5px;
`;

const FoodCategorySort = ({ AssortArr }) => {
  const onChange = e => {
    console.log(e.target.value);
  };
  return (
    <SortContainer>
      <SortSelect>
        <option>20개씩 보기</option>
        <option>40개씩 보기</option>
      </SortSelect>
      <SortSelect onChange={onChange}>
        {AssortArr.map((btn, i) => {
          return <option key={i}>{btn}</option>;
        })}
      </SortSelect>
    </SortContainer>
  );
};

export default FoodCategorySort;
