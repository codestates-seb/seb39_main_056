import * as Styled from './style';

const OrderTable = ({ orderProductList }) => {
  return (
    <Styled.OrderContainer>
      <Styled.OrderName>이름 : {orderProductList.productName}</Styled.OrderName>
      <Styled.OrderQuntity>
        수량 : {orderProductList.quantity}
      </Styled.OrderQuntity>
      <Styled.OrderPrice>
        가격 : ₩{orderProductList.productPrice}
      </Styled.OrderPrice>
    </Styled.OrderContainer>
  );
};
export default OrderTable;
