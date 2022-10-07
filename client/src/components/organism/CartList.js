import React from 'react';
import CartItem from './CartItem/CartItem';
import styled from 'styled-components';

const Container = styled.table`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const THeader = styled.th`
  font-size: 28px;
  font-weight: 600;
  text-align: center;
  padding-bottom: 30px;
`;

const TBody = styled.tbody`
  border-top: 2px solid gray;
  border-bottom: 2px solid gray;
  display: flex;
  flex-direction: column;
  padding-bottom: 10px;
  padding-top: 10px;
`;

const Tr = styled.tr`
  color: gray;
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding-top: 10px;
  padding-bottom: 10px;
`;
const Td = styled.td`
  padding: 10px;
`;
const Img = styled.img`
  width: 70px;
  height: 70px;
`;
const TFooter = styled.div`
  margin-left: auto;
  padding: 20px;
  padding-bottom: 30px;
  font-size: 20px;
`;
const Input = styled.input`
  width: 40px;
`;
const CartList = ({ cart, deleteCartITem }) => {
  return (
    <div>
      <Container>
        <thead>
          <tr>
            <th colspan="5">
              <THeader>Shopping Cart</THeader>
            </th>
          </tr>
        </thead>
        <TBody>
          <Tr>
            <Td>Proto</Td>
            <Td>Name</Td>
            <Td>Price</Td>
            <Td>Quility</Td>
            <Td>Total</Td>
          </Tr>
          <Tr>
            <Td>
              <Img src="https://www.oasis.co.kr:48581/product/52688/detail/detail_52688_0_ca68763b-efbf-4fea-bf23-439ebbc27e69.jpg"></Img>
            </Td>
            <Td>샤인머스켓</Td>
            <Td>3000원</Td>
            <Td>
              <Input type="number" min="1" max="20" value={1} />
            </Td>
            <Td>9000원</Td>
          </Tr>
          <Tr>
            <Td>
              <Img src="https://www.oasis.co.kr:48581/product/52688/detail/detail_52688_0_ca68763b-efbf-4fea-bf23-439ebbc27e69.jpg"></Img>
            </Td>
            <Td>가지라면</Td>
            <Td>3000원</Td>
            <Td>
              <Input type="number" min="1" max="20" value={1} />
            </Td>
            <Td>9000원</Td>
          </Tr>
          <Tr>
            <Td>
              {' '}
              <Img src="https://www.oasis.co.kr:48581/product/52688/detail/detail_52688_0_ca68763b-efbf-4fea-bf23-439ebbc27e69.jpg"></Img>
            </Td>
            <Td>가지라면</Td>
            <Td>3000원</Td>
            <Td>
              <Input type="number" min="1" max="20" value={1} />
            </Td>
            <Td>9000원</Td>
          </Tr>
        </TBody>
        <TFooter>총 결제금액 : 0 원</TFooter>
      </Container>
      {cart.map((el, idx) => {
        return <CartItem el={el} key={idx} deleteCartITem={deleteCartITem} />;
      })}
    </div>
  );
};

export default CartList;
