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
	border: 1px solid #c3f0ca;
	text-align: center;
	background-color: ${props => props.isClick || '#c3f0ca'};
`;

const FoodItem = ({ item }) => {
	const [isClick, setIsClick] = useState(false);
	const onClick = e => {
		setIsClick(!isClick);
		console.log(e.target.textContent, isClick);
		//그다음에 어떻게 하지?
	};
	return (
		<FoodCategoryBtn onClick={onClick} isClick={isClick}>
			<H2>{item}</H2>
		</FoodCategoryBtn>
	);
};
export default FoodItem;
