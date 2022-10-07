import React from 'react';
import FoodList from '../organism/FoodList';
import FoodCategory from '../organism/FoodCategory';
import PageHeader from '../organism/PageHeader';
import Floating from '../organism/Floating';

const MainPage = () => {
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

  return (
    <>
      <PageHeader />
      <FoodCategory Foodarr={Foodarr} AssortArr={AssortArr} />
      <FoodList />
      <Floating Types={Types} />
    </>
  );
};

export default MainPage;
