import styled from 'styled-components';

export const ProductInfoContainer = styled.div`
  display: flex;
  justify-content: space-between;
  height: fit-content;
  width: 100%;
  height: 100%;
  padding: 0 20px;
`;
export const ProductImgBox = styled.div`
  margin: 0 30px;
`;

export const ProductImg = styled.img`
  width: 420px;
  height: 420px;
`;
export const ProductDesc = styled.div`
  width: 100%;
  margin: 0 40px;
  padding : 5px 20px;
  display: flex;
  flex-direction: column;
`;


export const BadgeBlock = styled.div`
  display: flex;
`;

export const ProductBadge = styled.span `
    display: inline-block;
    background: #6ba543;
    width: fit-content;
    height: 20px;
    margin-right: 3px;
    padding: 0 5px;
    border-radius: 4px;
    font-size: 12px;
    color: #fff;
    line-height: 20px;
    word-break: keep-all;
    margin-bottom : 5px
`;

export const SoldOutBadge = styled.span `
    display: inline-block;
    background: #c2c2c2;
    width: fit-content;
    height: 20px;
    margin-right: 3px;
    padding: 0 5px;
    border-radius: 4px;
    font-size: 12px;
    color: #fff;
    line-height: 20px;
    word-break: keep-all;
    margin-bottom : 5px
`;

export const AlmostSoldOutBadge = styled.span `
    display: inline-block;
    background: #ffd500;
    width: fit-content;
    height: 20px;
    margin-right: 3px;
    padding: 0 5px;
    border-radius: 4px;
    font-size: 12px;
    color: #fff;
    line-height: 20px;
    word-break: keep-all;
    margin-bottom : 5px
`;


export const ProductTitle = styled.p`
  padding: 8px 0px;
  line-height: 30px;
  font-size: 2em;
  font-weight: 700;
  padding-right: 33px;
  border-bottom: 3px solid #f7f7f7;
  margin-bottom : 10px;
`;
export const ProductContent = styled.p`
  font-size: 1.5em;
  margin-bottom: 10px;
`;

export const ProductContentField = styled.dd`
float: left;
width: 130px;
padding: 10px 10px;
line-height: 24px;
font-size: 20px;
color: #828282;
`;

export const ProductContentValue = styled.dt`
    margin-left: 130px;
    color: #333;
    padding: 10px 0px;
    line-height: 24px;
    font-size: 20px;
    font-weight : 600;
`;

export const ProductContentSpace = styled.div`
    width:100%;
    min-width: 450px;
    height : 30%;
    border-bottom: 2px solid #f7f7f7;
    margin-bottom : 10px;
`;

export const ProductPurchaseCountBox = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100px;
  height: 40px;
  font-size : 20px;
  overflow: hidden;
`;

export const ProductDescBox = styled.div`
  width: 80%;
  height: 100%;
  flex-grow: 1;
  padding: 10px;
`;
