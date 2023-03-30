import React, { useState } from 'react';
import FoodList from '../organism/FoodList';
import FoodCategory from '../organism/FoodCategory';
import PageHeader from '../organism/PageHeader';
import Floating from '../organism/Floating';
import { useSearchParams } from 'react-router-dom';
import Page from '../organism/Page/Page';
import { PageBox } from '../organism/Page/style';
import styled from 'styled-components';
import { setSelectionRange } from '@testing-library/user-event/dist/utils';


const MainPage = () => {

  const CurrentPageBlock = styled.div `
  width: 120px;
  height: 60px;
  position: fixed;
  bottom: 370px;
  right: 0;
  margin: 20px;
  border-radius: 10px;
  background-color: #6BA544;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  `;

  const TypeTitle = styled.div`
    font-weight: 600;
  `;
  const TypeName = styled.p`
    widht: 100%;
    color: #FFF;
  `;


  const [pages, setPages] = useState([]);
  const [searchParams, setSearchParams] = useSearchParams();

  // 음식 카테고리
  const Foodarr = ['전체보기', '간편식', '간식', '음료', '반찬', '조미료'];
  // 메인화면 정렬 버튼
  const AssortArr = ['신제품순', '낮은가격순', '높은가격순'];
  // 채식유형 플로팅배너
  const Types = [
    '플렉시테리언',
    '폴로-페스코',
    '페스코',
    '폴로',
    '락토-오보',
    '락토',
    '오보',
    '비건',
    '프루테리언',
  ];

  const [assortArr, setAssortArr] = useState(['20개씩 보기', '40개씩 보기']);
  const [CurrentType, setCurrentType] = useState('');

  const orderBy = searchParams.get('orderBy');
  const sort = searchParams.get('sort');

  const changeAssortArr = arr => {
    const perPage = searchParams.get('size');
    if (perPage === '20' && arr.length === 2) {
      console.log('1');
      return ['20개씩 보기', ...assortArr];
    } else if (perPage === '40' && arr.length === 2) {
      console.log('2');

      return ['40개씩 보기', ...assortArr];
    } else if (perPage === '20' && arr.length === 3) {
      arr[0] = '20개씩 보기';
      console.log('3');

      return [...arr];
    } else if (perPage === '40' && arr.length === 3) {
      arr[0] = '40개씩 보기';
      console.log('4');

      return [...arr];
    } else if (perPage === null) {
      // arr[0] = '20개씩 보기';
      // return [...arr];
      console.log('5');
      return ['20개씩 보기', '20개씩 보기', '40개씩 보기'];
    } else {
      console.log('6');
      return arr;
    }
  };

  const pagesQuantity = [];

  for (let i = 1; i <= pages; i++) {
    pagesQuantity.push(i);
  }

  return (
    <>
      <PageHeader />
      <FoodCategory
        Foodarr={Foodarr}
        AssortArr={AssortArr}
        searchParams={searchParams}
        setSearchParams={setSearchParams}
        assortArr={assortArr}
        setAssortArr={setAssortArr}
        changeAssortArr={changeAssortArr}
      />
      <FoodList
        searchParams={searchParams}
        setPages={setPages}
        assortArr={assortArr}
        setAssortArr={setAssortArr}
        changeAssortArr={changeAssortArr}
        setCurrentType={setCurrentType}
      />
      <PageBox>
        {pagesQuantity.map((page, idx) => {
          return (
            <Page
              key={idx}
              searchParams={searchParams}
              setSearchParams={setSearchParams}
              page={page}
            ></Page>
          );
        })}
      </PageBox>
      <CurrentPageBlock>
        <TypeTitle>CURRENT</TypeTitle>
        <TypeName>{CurrentType}</TypeName>
      </CurrentPageBlock>
      <Floating
        Types={Types}
        setSearchParams={setSearchParams}
        searchParams={searchParams}
      />
    </>
  );
};

export default MainPage;