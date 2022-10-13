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
  position: relative;
  width: 100px; // 자를 사이즈를 명시해준다.
  height: 500px;
  overflow: hidden;
`;
export const ThumbnailImage = styled.img`
  width: 100%;
  height: 100%;
`;
export const ProductImg = styled.img`
  width: 100%;
  //height: 400%;
  position: absolute; // 포지션을 주고,
  top: 0; // 보이기 원하는 위치를 지정
  left: 0;
  //width: 500px; // 오리지널 사이즈
  height: 1500px;
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
