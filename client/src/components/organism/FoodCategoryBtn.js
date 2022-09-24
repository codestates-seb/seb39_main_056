import React from 'react';
import styled from 'styled-components';

const BtnBox = styled.div`
	display: flex;
	justify-content: end;
`;

const BtnItem = styled.div`
	padding: 10px;
	border: 1px solid #c3f0ca;
`;

const FoodCategoryBtn = () => {
	return (
		<BtnBox>
			<BtnItem>신제품순</BtnItem>
			<BtnItem>낮은가격순</BtnItem>
			<BtnItem>높은가격순</BtnItem>
		</BtnBox>
	);
};

export default FoodCategoryBtn;
