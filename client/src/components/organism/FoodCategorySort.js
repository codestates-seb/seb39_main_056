import React from 'react';
import styled from 'styled-components';
import { useState } from 'react';

const SortContainer = styled.div`
	display: flex;
	justify-content: end;
	margin-bottom: 20px;
	flex-wrap: wrap;
`;
const SortBtn = styled.div`
	padding: 10px;
	margin: 10px;
	cursor: pointer;
	// state에 따라 클릭될때마다 그것만 바뀌게 하고 싶은데!
	background-color: ${props => props.isClick || '#c3f0ca'};
`;

const FoodCategorySort = ({ AssortArr }) => {
	const [isClick, setIsClick] = useState(false);
	const onClick = () => {
		setIsClick(!isClick);
		console.log(isClick);
	};
	return (
		<SortContainer>
			{AssortArr.map((btn, i) => {
				return (
					<SortBtn
						key={i}
						onClick={onClick}
						isClick={isClick}
						setIsClick={setIsClick}
					>
						{btn}
					</SortBtn>
				);
			})}
		</SortContainer>
	);
};

export default FoodCategorySort;
