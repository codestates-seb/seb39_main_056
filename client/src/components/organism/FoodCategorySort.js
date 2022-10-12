import React from 'react';
import styled from 'styled-components';

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

const Option = styled.option`
  &:first-child {
    display: none;
  }
`;

const FoodCategorySort = ({
  AssortArr,
  searchParams,
  setSearchParams,
  assortArr,
}) => {
  const onChangeSize = e => {
    const params = [];
    const paramsObj = {};
    for (let entry of searchParams.entries()) {
      params.push(entry);
    }
    for (let i = 0; i < params.length; i++) {
      paramsObj[`${params[i][0]}`] = params[i][1];
    }
    setSearchParams({
      ...paramsObj,
      page: '1',
      size: e.target.value.slice(0, 2),
    });
  };

  const onChangeSort = e => {
    const params = [];
    const paramsObj = {};
    for (let entry of searchParams.entries()) {
      params.push(entry);
    }
    for (let i = 0; i < params.length; i++) {
      paramsObj[`${params[i][0]}`] = params[i][1];
    }
    if (e.target.value === '신제품순') {
      setSearchParams({
        ...paramsObj,
        page: '1',
        sort: 'create_date',
        orderBy: 'asc',
      });

      // 추후 리팩토링할 것
      // const selectedOption = document.querySelector(`#${e.target.value}`);
      // selectedOption.setAttribute('selected', 'selected');
      // selectedOption.nextSibling.setAttribute('selected', '');
      // selectedOption.nextSibling.nextSibling.setAttribute('selected', '');
    } else if (e.target.value === '낮은가격순') {
      setSearchParams({
        ...paramsObj,
        page: '1',
        sort: 'price',
        orderBy: 'asc',
      });

      // 추후 리팩토링할 것
      // const selectedOption = document.querySelector(`#${e.target.value}`);
      // selectedOption.setAttribute('selected', 'selected');
      // selectedOption.nextSibling.setAttribute('selected', '');
      // selectedOption.previousSibling.setAttribute('selected', '');
    } else {
      setSearchParams({
        ...paramsObj,
        page: '1',
        sort: 'price',
        orderBy: 'desc',
      });

      // 추후 리팩토링할 것
      // const selectedOption = document.querySelector(`#${e.target.value}`);
      // selectedOption.setAttribute('selected', 'selected');
      // selectedOption.previousSibling.setAttribute('selected', '');
      // selectedOption.previousSibling.previousSibling.setAttribute(
      //   'selected',
      //   '',
      // );
    }
  };

  return (
    <SortContainer>
      <SortSelect onChange={e => onChangeSize(e)}>
        {assortArr.map((el, i) => {
          return <Option key={i}>{el}</Option>;
        })}
      </SortSelect>
      <SortSelect onChange={e => onChangeSort(e)}>
        {AssortArr.map((btn, i) => {
          return (
            <option key={i} id={btn}>
              {btn}
            </option>
          );
        })}
      </SortSelect>
    </SortContainer>
  );
};

export default FoodCategorySort;
