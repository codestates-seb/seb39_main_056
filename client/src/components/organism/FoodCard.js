import React from 'react';
import H2 from '../atom/H2';
import H3 from '../atom/H3';
import styled from 'styled-components';

const Container = styled.div`
	display: flex;
	flex-direction: column;
	margin: 10px;
	cursor: pointer;
`;
const Img = styled.img`
	width: 210px;
	height: 210px;
	border-radius: 10px;
`;

const FoodCard = ({ card }) => {
	console.log(card);
	return (
		<Container>
			<Img src={card?.thumbnailImage} />
			<H2>{card?.productName}</H2>
			<H3>{card?.price}원</H3>
		</Container>
	);
};

export default FoodCard;
