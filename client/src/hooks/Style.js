import styled from 'styled-components';
import MainColorBtn from '../../atom/MainColorBtn';
import StyledInput from '../../atom/StyledInput';
import H3 from '../../atom/H3';

export const InnerBox = styled.div`
  width: fit-content;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  /* background-color: powderblue; */
`;

export const SocialContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

export const LoginContainer = styled.div`
  width: 100%;
  /* display: flex;
  flex-direction: column;
  align-items: center; */

  > form {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
`;

export const LoginBtn = styled(MainColorBtn)`
  margin: 1rem;
`;

export const LoginInput = styled(StyledInput)`
  width: 100%;
`;

export const LoginH3 = styled(H3)`
  margin: 1rem 0;
`;
