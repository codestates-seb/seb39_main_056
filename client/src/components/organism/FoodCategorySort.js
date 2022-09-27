import React from 'react';
import styled from 'styled-components';
import { useState } from 'react';

const SortContainer = styled.form`
	display: flex;
	justify-content: end;
	margin: 20px;
	padding: 10px;
`;
const SortSelect = styled.select`
	padding: 10px;
	width: 130px;
	cursor: pointer;
`;

const FoodCategorySort = ({ AssortArr }) => {
	const onChange = e => {
		console.log(e.target.value);
	};
	return (
		<SortContainer>
			<SortSelect onChange={onChange}>
				{AssortArr.map((btn, i) => {
					return <option key={i}>{btn}</option>;
				})}
			</SortSelect>
		</SortContainer>
	);
};

export default FoodCategorySort;
