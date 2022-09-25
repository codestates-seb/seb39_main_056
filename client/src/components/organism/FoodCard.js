// import React, { useState } from 'react';
import H2 from '../atom/H2';
import H3 from '../atom/H3';
import styled from 'styled-components';

const Container = styled.div`
	display: flex;
	flex-direction: column;
	margin: 10px;
	cursor: pointer;
	transition: all 0.2s linear;
	:hover {
		transform: scale(1.1);
	}
`;
const Img = styled.img`
	width: 210px;
	margin: 0px auto;
	height: 210px;
	border-radius: 10px;
`;

const FoodCard = ({ card }) => {
	// const [isClick, setIsClick] = useState(false);
	// const onClick = () => {
	// 	setIsClick(!isClick);
	// 	console.log(isClick);
	// };

	const style = {
		fontWeight: '600',
	};
	return (
		<Container>
			<Img src={card?.thumbnailImage} />
			<H2 style={style}>{card?.productName}</H2>
			<H3>{card?.price}원</H3>
		</Container>
	);
};

export default FoodCard;
