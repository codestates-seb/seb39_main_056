import styled from 'styled-components';
export const SerchBar = styled.input`
  width: 100%;
  height: 50px;
  font-size: 1.5em;
  padding-left: 50px;
`;
export const InputBox = styled.div`
  flex-basis: 30%;
  position: relative;
`;
export const Logo = styled.img``;
export const HeaderContainer = styled.div`
  display: flex;
  justify-content: space-between;
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
