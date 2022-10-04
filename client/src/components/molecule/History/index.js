import * as Styled from './style';

export const UserHistoryContainer = ({ HistoryData }) => {
  return (
    <Styled.HistoryItemContainer>
      {HistoryData.image} {HistoryData.username} {HistoryData.product}
    </Styled.HistoryItemContainer>
  );
};
