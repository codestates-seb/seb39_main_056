import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import FoodCard from './FoodCard';

const Container = styled.div`
	display: flex;
	width: 250px;
	margin: 10px;
`;

const FoodList = () => {
	const [cardList, setCardList] = useState([]);
	const getProducts = async () => {
		let url = `http://localhost:3001/products`;
		let res = await fetch(url);
		let data = await res.json();
		setCardList(data);
		console.log(cardList);
	};
	useEffect(() => {
		getProducts();
	}, []);

	return (
		<>
			<Container>
				{/* {cardList.map(card => {
					<FoodCard card={card} />;
				})} */}
				<FoodCard />
				<FoodCard />
				<FoodCard />
			</Container>
		</>
	);
};

export default FoodList;
