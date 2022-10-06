import * as Styled from './style';
import { UserHistoryContainer } from '../../molecule/History';

export const UserHistory = ({ productHistory }) => {
  return (
    <Styled.HistoryPageBox>
      <Styled.HistoryPageTitle>구매내역</Styled.HistoryPageTitle>
      {productHistory?.map(el => (
        <UserHistoryContainer key={el.orderId} HistoryData={el} />
      ))}
    </Styled.HistoryPageBox>
  );
};
