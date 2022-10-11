import styled from 'styled-components';

export const ItemWrapper = styled.div`
  width: 80%;
  height: 150px;
  border-bottom: 2px solid black;
  display: flex;
  align-items: center;
  margin: 0 auto;
  padding: 0 5%;
  /* background-color: powderblue; */
  display: flex;
  justify-content: space-around;

  &:first-child {
    border-top: 2px solid black;
  }
`;

export const ImgBox = styled.div`
  border: 1px solid black;
  width: 120px;
  height: 120px;

  > img {
    width: 100%;
  }
`;

export const InfoBox = styled.div`
  width: 60%;
  min-width: fit-content;
  height: 120px;
  /* border: 1px solid black; */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

export const ItemName = styled.div`
  font-size: 1.2rem;
  font-family: 'MICEGothic_Bold';
`;

export const ItemPrice = styled.p``;

export const ItemQuantity = styled.input`
  padding: 0.2rem 0;
`;

export const TotalPrice = styled.p``;
