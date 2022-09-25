import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import FoodCard from './FoodCard';

const Container = styled.div`
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
	justify-content: center;
`;

const FoodList = () => {
	const [cardList, setCardList] = useState([]);
	const getProducts = async () => {
		let url = `http://localhost:3001/products`;
		let res = await fetch(url);
		let data = await res.json();
		setCardList(data);
	};
	useEffect(() => {
		getProducts();
	}, []);

	return (
		<>
			<Container>
				{cardList.map((card, i) => {
					return <FoodCard card={card} key={i} />;
				})}
			</Container>
		</>
	);
};

export default FoodList;
