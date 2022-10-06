import styled from 'styled-components';
import MainColorBtn from '../../atom/MainColorBtn';

export const InnerBox = styled.div`
  top: 0;
  width: fit-content;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  /* background-color: powderblue; */
`;

export const Title = styled.div`
  text-align: center;
  font-size: 1.4rem;
  margin-bottom: 1rem;
`;

export const InputContainer = styled.div`
  margin: 10px 0;
`;

export const SignUpBtn = styled(MainColorBtn)`
  margin-top: 2rem;
`;
