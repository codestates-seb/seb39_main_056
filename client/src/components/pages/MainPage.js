import React from 'react';
import FoodList from '../organism/FoodList';
import FoodCategory from '../organism/FoodCategory';
import PageHeader from '../organism/PageHeader';

const MainPage = () => {
	// 음식 카테고리
	const Foodarr = ['간편식', '간식', '음료', '반찬', '조미료'];
	// 메인화면 정렬 버튼
	const AssortArr = ['신제품순', '낮은가격순', '높은가격순'];

	return (
		<>
			<PageHeader />
			<FoodCategory Foodarr={Foodarr} AssortArr={AssortArr} />
			<FoodList />
		</>
	);
};

export default MainPage;
