import styled from 'styled-components';
export const SerchBar = styled.input`
  width: 100%;
  height: 50px;
  font-size: 1.5em;
  padding-left: 50px;
  border-radius: 25px;
`;
export const LogoBox = styled.div`
  flex-basis: 15%;
`;

export const InputBox = styled.div`
  flex-basis: 42%;
  //position: relative;
`;
export const Logo = styled.img`
  width: 100%;
  height: 30%;
`;

export const Logo = styled.img`
  width: 80px;
`;

export const HeaderContainer = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: center;
`;
export const ButtonContainer = styled.div``;
export const TextLink = styled.button`
  margin-right: 0 auto;
  background-color: white;
  border: none;
  border-right: 1px solid black;
  font-size: 1.2em;
  &:hover {
    color: blue;
    background-color: skyblue;
  }
`;
