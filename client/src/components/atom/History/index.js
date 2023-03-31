import * as Styled from './style';

const OrderTable = ({ orderProductList }) => {
  return (
    <Styled.OrderContainer>
      <Styled.OrderName>이름 : {orderProductList.product.productName}</Styled.OrderName>
      <Styled.OrderQuntity>
        수량 : {orderProductList.ordersQuantity}
      </Styled.OrderQuntity>
      <Styled.OrderPrice>
        개별 가격 : ₩{orderProductList.product.price}
      </Styled.OrderPrice>
    </Styled.OrderContainer>
  );
};
export default OrderTable;
