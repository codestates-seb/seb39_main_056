import styled from 'styled-components';
import MainColorBtn from '../../atom/MainColorBtn';
import StyledInput from '../../atom/StyledInput';
import H3 from '../../atom/H3';

export const InnerBox = styled.div`
  width: fit-content;
  height: 300px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
`;

export const SocialContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

export const LoginContainer = styled.div`
  width: 100%;

  > form {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
`;

export const LoginBtn = styled(MainColorBtn)`
  width : 250px;
  margin : 15px;
`;

export const LoginInput = styled(StyledInput)`
  width: 250px;
  height: 38px;
  font-size: 1em;
`;

export const LoginH3 = styled(H3)`
  margin: 1rem 0;
`;
