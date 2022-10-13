import * as Styled from './style';
import { UserHistoryContainer } from '../../molecule/History';
import { NoResult } from '../../molecule/NoResult';

export const UserHistory = ({ productHistory }) => {
  // const orders = { productHistory };
  return (
    <Styled.HistoryPageBox>
      <Styled.HistoryPageTitle>구매내역</Styled.HistoryPageTitle>

      {productHistory === undefined || productHistory.length === 0 ? (
        <NoResult>현재 주문목록이 비어있습니다.</NoResult>
      ) : (
        productHistory?.map(el => (
          <UserHistoryContainer key={el.orderId} HistoryData={el} />
        ))
      )}
    </Styled.HistoryPageBox>
  );
};
