import styled from 'styled-components';

export const CategoryPageTitle = styled.p`
  margin-top: 20px;
  margin-bottom: 20px;
  font-size: 1.5em;
`;
export const CategoryPageBox = styled.div`
  //width: 70%;
  //height: 100vh;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
`;

export const MoveToCartBtn = styled.button`
  width: fit-content;
  margin: 10px 0px 10px auto;
  background-color: white;
  border: 2px solid black;
  border-radius: 5px;
  padding: 10px;
  font-size: 1.2em;
  font-family: 'MICEGothic_Bold';
  &:hover {
    background-color: lightgray;
  }
`;

export const BuyBtn = styled.button`
  width: fit-content;
  margin: 10px 0px 10px auto;
  background-color: white;
  border: 2px solid black;
  border-radius: 5px;
  padding: 10px;
  font-size: 1.2em;
  font-family: 'MICEGothic_Bold';
  &:hover {
    background-color: lightgray;
  }
`;
export const DetailedProductDesc = styled.img`
  margin-top: 20px;
  width: 100%;
  height: 100%;
`;
