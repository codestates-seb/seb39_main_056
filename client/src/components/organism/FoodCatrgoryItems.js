import React from 'react';
import styled from 'styled-components';

import FoodItem from './FoodItem';

const FoodCategoryContainer = styled.div`
	display: flex;
	justify-content: flex-start;
	flex-wrap: wrap;
`;

const FoodCatrgoryItems = ({ Foodarr }) => {
	return (
		<FoodCategoryContainer>
			{Foodarr.map((item, i) => {
				return <FoodItem key={i} item={item} />;
			})}
		</FoodCategoryContainer>
	);
};

export default FoodCatrgoryItems;
