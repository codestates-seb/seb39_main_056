import * as Styled from './style';
import { UserHistoryContainer } from '../../molecule/History';

export const UserHistory = ({ productHistory }) => {
  return (
    <Styled.HistoryPageBox>
      {productHistory?.map(el => (
        <UserHistoryContainer HistoryData={el} />
      ))}
    </Styled.HistoryPageBox>
  );
};
