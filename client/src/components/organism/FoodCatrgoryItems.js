import React from 'react';
import styled from 'styled-components';

const FoodCategoryContainer = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 20px;
`;

const FoodCategoryBtn = styled.div`
	padding: 10px 40px;
	border: none;
	cursor: pointer;
	color: #33272a;
	border: 1px solid #c3f0ca;
	:hover {
		text-decoration: underline;
	}
`;

const FoodCatrgoryItems = ({ Foodarr }) => {
	return (
		<FoodCategoryContainer>
			{Foodarr.map((item, i) => {
				return <FoodCategoryBtn key={i}>{item}</FoodCategoryBtn>;
			})}
		</FoodCategoryContainer>
	);
};

export default FoodCatrgoryItems;
