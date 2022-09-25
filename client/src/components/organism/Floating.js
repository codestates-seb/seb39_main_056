import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
	background-color: beige;
	position: fixed;
	bottom: 0;
	right: 0;
	margin: 20px;
	border-radius: 10px;
`;

const Item = styled.div`
	text-align: center;
	width: 60px;
	height: 20px;
	line-height: 20px;
	padding: 10px;
	:hover {
		text-decoration: underline;
	}
	font-size: x-small;
`;

const Floating = ({ Types }) => {
	const onClick = e => {
		console.log(e.target.textContent);
	};
	return (
		<Container>
			{Types.map((type, i) => {
				return (
					<Item key={i} onClick={onClick}>
						{type}
					</Item>
				);
			})}
		</Container>
	);
};

export default Floating;
