import styled from 'styled-components';

export const ProductInfoContainer = styled.div`
  display: flex;
  justify-content: space-between;
  height: fit-content;
  width: 100%;
  height: 100%;
`;
export const ProductImgBox = styled.div`
  flex-basis: 50%;
`;

export const ProductImg = styled.img`
  width: 100%;
  height: 100%;
`;
export const ProductDesc = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  flex-basis: 45%;
`;
export const ProductTitle = styled.p`
  font-size: 2em;
  margin-bottom: 10px;
`;
export const ProductContent = styled.p`
  font-size: 1.5em;
  margin-bottom: 10px;
`;

export const ProductDescBox = styled.div`
  border: 1px solid black;
  width: 100%;
  height: 100%;
  flex-grow: 1;
  padding: 10px;
`;
