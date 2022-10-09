import * as Styled from './style';
import OrderTable from '../../atom/OrderTable';

export const UserHistoryContainer = ({ HistoryData }) => {
  return (
    <Styled.BorderBotom>
      <Styled.HistoryItemContainer>
        <div>구매일자 : {HistoryData.orderDate.slice(0, 10)}</div>
        <div>총가격 : ₩{HistoryData.totalPrice}</div>
      </Styled.HistoryItemContainer>
      {HistoryData.orderProductList.map(el => (
        <OrderTable key={el.productId} orderProductList={el} />
      ))}
    </Styled.BorderBotom>
  );
};
