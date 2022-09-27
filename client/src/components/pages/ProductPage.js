import styled from 'styled-components';
import FoodImg from '../../assets/aa.jpg';

const CategoryPageBox = styled.div`
	width: 70%;
	height: 100vh;
	margin: 0 auto;
	display: flex;
	flex-direction: column;
`;
const ProductInfoContainer = styled.div`
	display: flex;
	justify-content: space-between;
	height: fit-content;
	width: 100%;
	height: 100%;
`;
const ProductImg = styled.img`
	width: 100%;
	height: 100%;
`;
const ProductDesc = styled.div`
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: column;
`;
const ProductTitle = styled.p``;
const ProductContent = styled.p``;
const ProductDescBox = styled.div`
	border: 1px solid black;
	width: 100%;
	flex-grow: 1;
`;
const MoveToCartBtn = styled.button`
	width: fit-content;
	margin: 10px 0px 10px auto;
	background-color: white;
	border: 2px solid black;
	border-radius: 5px;
	padding: 10px;
	font-size: 1.2em;
	font-weight: bold;
	&:hover {
		background-color: lightgray;
	}
`;
const DetailedProductDesc = styled.img`
	margin-top: 20px;
	width: 100%;
	height: 100%;
`;

const ProductPage = () => {
	return (
		<>
			<p>Home > Food Category</p>
			<CategoryPageBox>
				<ProductInfoContainer>
					<ProductImg src={FoodImg} />
					<ProductDesc>
						<ProductTitle>제품명</ProductTitle>
						<ProductContent>제품설명: 제품 한줄 설명</ProductContent>
						<ProductDescBox>
							<p>가격 : 0원</p> <p>재고수: 0개</p>
						</ProductDescBox>
					</ProductDesc>
				</ProductInfoContainer>
				<MoveToCartBtn>구매하기</MoveToCartBtn>
				<DetailedProductDesc src={FoodImg} />
			</CategoryPageBox>
		</>
	);
};

export default ProductPage;
