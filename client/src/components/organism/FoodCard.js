import React from 'react';
import H2 from '../atom/H2';
import H3 from '../atom/H3';
import styled from 'styled-components';

const Img = styled.img`
	width: 300px;
	border-radius: 10px;
`;

const Container = styled.div`
	display: flex;
	flex-direction: column;
	width: 300px;
	margin: 10px;
`;

const FoodCard = () => {
	return (
		<Container>
			<Img src="https://www.oasis.co.kr:48581/product/52688/detail/detail_52688_0_ca68763b-efbf-4fea-bf23-439ebbc27e69.jpg" />
			<H2>상품명</H2>
			<H3>상품가격</H3>
		</Container>
	);
};

export default FoodCard;
