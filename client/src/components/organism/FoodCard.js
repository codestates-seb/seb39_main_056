import H2 from '../atom/H2';
import H3 from '../atom/H3';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Container = styled.div`
  width: 210px;
  display: flex;
  flex-direction: column;
  margin: 10px;
  cursor: pointer;
  transition: all 0.2s linear;
  text-decoration: none;
  :hover {
    transform: scale(1.1);
  }
`;
const Img = styled.img`
  width: 100%;
  margin: 0px auto;
  height: 210px;
  border-radius: 10px;
`;

const Text = styled.div`
  text-decoration: none;
  text-align: center;
`;

const FoodCard = ({ card }) => {
  const style = {
    fontWeight: '600',
  };

  return (
    <Link to={`/product/${card.productId}`}>
      <Container>
        <Img src={card?.thumbnailImage} />
        <Text>
          <H2 style={style}>{card?.productName}</H2>
        </Text>
        <Text>
          <H3>{card?.price}원</H3>
        </Text>
      </Container>
    </Link>
  );
};

export default FoodCard;
