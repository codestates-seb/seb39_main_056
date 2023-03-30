import styled from 'styled-components';

export const CategoryPageTitle = styled.p`
  display : inline-block;
  margin-top: 20px;
  margin-bottom: 20px;
  margin-right: 6px;
  font-size: 1.2em;
`;

export const CategoryPageSubTitle = styled.p`
  display : inline-block;
  margin-top: 20px;
  margin-bottom: 20px;
  font-size : 1.2em;
  font-weight : bold;
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

export const BtnBlock = styled.div`
  display : flex;
  justify-content: end;
  padding : 10px 20px;
  margin : 0 100px
`;

export const CartBtn = styled.button`
  border: 0;
  outline; 0;
  cursor: pointer;
  border-radius: 2px;
  padding: 10px;
  font-size: 1.2em;
  font-family: 'MICEGothic_Bold';
  background-color: #6aa338;
  color: #fff;
  display: inline-block;
  width: 200px;
  height: 50px;
  font-weight: 500;
  font-size: 18px;
  text-align: center;
  margin-right : 10px;
`;

export const BuyBtn = styled.button`
  border: 0;
  outline; 0;
  cursor: pointer;
  border-radius: 2px;
  padding: 10px;
  font-size: 1.2em;
  font-family: 'MICEGothic_Bold';
  background-color: #6aa338;
  color: #fff;
  display: inline-block;
  width: 200px;
  height: 50px;
  font-weight: 500;
  font-size: 18px;
  text-align: center;
  margin : 0 20px;
`;

export const SoldOutBtn = styled.button`
  border: 0;
  outline; 0;
  border-radius: 2px;
  padding: 10px;
  font-size: 1.2em;
  font-family: 'MICEGothic_Bold';
  background-color: #c2c2c2;
  color: #fff;
  display: inline-block;
  width: 200px;
  height: 50px;
  font-weight: 500;
  font-size: 18px;
  text-align: center;
  margin : 0 20px;
`;

export const Space = styled.div`
  padding: 5px 0px 44px 0px;
  margin-bottom: 20px;
  border-bottom: 2px solid #f7f7f7;
  overflow: auto;

}
`;

export const DetailedProductTitle = styled.p`
  padding: 8px 0px;
  line-height: 30px;
  font-size: 1.65em;
  font-weight: 600;
  padding-right: 33px;
  margin: 30px auto;
`;

export const DetailedProductDesc = styled.img`
  max-width: 800px;
  margin : 0 auto;
  height: 100%;
`;
