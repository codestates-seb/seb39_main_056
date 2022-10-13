import styled from 'styled-components';
import MaincolorBtn from '../../atom/MainColorBtn';

export const CartContainer = styled.div`
  width: 60%;
  height: 100%;
  margin: 0 auto;
  margin-top: 4rem;
  /* background-color: beige; */
`;

export const Title = styled.div`
  font-size: 28px;
  font-family: 'MICEGothic_Bold';
  text-align: center;
  padding-bottom: 1rem;
`;

export const Info = styled.div`
  text-align: center;
`;

export const TotalPrice = styled.div``;

export const BuyBtn = styled(MaincolorBtn)`
  width: 200px;
  height: 50px;
  margin-top: 10px;
`;

export const CartTitle = styled.div`
  font-size: 1.6rem;
  margin: 1.6rem 0;
  text-align: center;
`;
