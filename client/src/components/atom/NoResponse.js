import * as Styled from './style';

export const NoResponse = () => {
  return (
    <Styled.ErrorPageWrapper>
      <Styled.ErrorPageText>잘못된 요청입니다</Styled.ErrorPageText>
    </Styled.ErrorPageWrapper>
  );
};
