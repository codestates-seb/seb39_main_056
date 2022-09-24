import React from 'react';
import { useState } from 'react';

import FoodCategory from '../organism/FoodCategory';
import PageHeader from '../organism/PageHeader';

const MainPage = () => {
	// 음식 카테고리
	const Foodarr = ['간편식', '간식', '음료', '반찬', '조미료'];
	// 음식 카테고리 변경 state
	const [isClick, setIsClick] = useState(false);

	return (
		<div>
			<PageHeader />
			<FoodCategory
				Foodarr={Foodarr}
				isClick={isClick}
				setIsClick={setIsClick}
			/>
		</div>
	);
};

export default MainPage;
