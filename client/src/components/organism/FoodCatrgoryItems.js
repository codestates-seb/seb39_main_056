import React from 'react';
import styled from 'styled-components';

const FoodCategoryBox = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 20px;
`;

const FoodCategoryItemBox = styled.div`
	padding: 10px 40px;
	margin: 10px;
	border: none;
	cursor: pointer;
	border-radius: 10px;
	color: #33272a;
	background-color: ${props => props.isClick || '#c3f0ca'};
`;

const FoodCatrgoryItems = ({ Foodarr, isClick, setIsClick }) => {
	const onClick = () => {
		setIsClick(!isClick);
		console.log(isClick);
	};

	return (
		<FoodCategoryBox>
			{Foodarr.map((item, i) => {
				return (
					<FoodCategoryItemBox key={i} isClick={isClick} onClick={onClick}>
						{item}
					</FoodCategoryItemBox>
				);
			})}
		</FoodCategoryBox>
	);
};

export default FoodCatrgoryItems;
