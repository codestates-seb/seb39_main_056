import React from 'react';
import styled from 'styled-components';
//여기서 스타일을 바꿔줘야함 뭐에 따라? state가 true인지 false인지 따라!
//그럼 page가 모든 state랑 함수를 들고 있어야지
//어떤 state가 필요한가?

const FoodCategoryBox = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 20px;
`;

const FoodCategoryItemBox = styled.div`
	padding: 10px 40px;
	border: 1px solid lightgray;
	cursor: pointer;
	:hover {
		color: blue;
	}
	background-color: ${props => props.isClick || 'red'};
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
