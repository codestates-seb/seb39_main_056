import H2 from '../atom/H2';
import H3 from '../atom/H3';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { faBorderNone } from '@fortawesome/free-solid-svg-icons';

const Container = styled.div`
  width: 210px;
  display: flex;
  flex-direction: column;
  margin: 10px;
  cursor: pointer;
  transition: all 0.2s linear;
  :hover {
    transform: scale(1.1);
  }
`;

const Badge = styled.span`
    display: inline-block;
    background: #6ba543;
    width: fit-content;
    margin-right: 3px;
    margin-bottom : 5px;
    padding: 0 5px;
    border-radius: 4px;
    font-size: 12px;
    color: #fff;
    line-height: 18px;
    word-break: keep-all;
`;

const Img = styled.img`
  width: 100%;
  margin: 0 auto;
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
    <Link to={`/product/${card.productId}` }>
      <Container>
      <Badge>{card?.vegetarianType}</Badge>
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
