import styled from 'styled-components';

export const OrderContainer = styled.div`
  font-size: 1.3em;
  margin-top: 10px;
  display: flex;
  justify-content: space-between;
`;

export const OrderName = styled.div`
  flex-basis: 30%;
  text-align: left;
`;
export const OrderQuntity = styled.div`
  flex-basis: 30%;
  text-align: right;
`;
export const OrderPrice = styled.div`
  flex-basis: 40%;
  text-align: right;
`;
