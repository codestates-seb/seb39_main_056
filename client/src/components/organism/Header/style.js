import styled from 'styled-components';
export const SerchBar = styled.input`
  width: 100%;
  min-width : 500px;
  height: 50px;
  font-size: 1.2em;
  padding: 5px 10px 0 35px;
  font-family: "Spoqa Han Sans Neo", sans-serif;
  font-weight: 400;
  underline : none;
  border-radius: 4px;
  border : 2px solid #6ba543;
`;
export const InputBox = styled.div`
  flex-basis: 35%;
  margin-left: 200px;
  position: relative;
`;
export const Logo = styled.img`
  width: 80px;
  margin: 0 80px 0 100px;
`;
export const HeaderContainer = styled.div`
  display: flex;
  align-items: center;
  margin: 15px 0 50px;
`;
export const ButtonContainer = styled.div`
  margin-left : 120px;
  min-width : 300px;
`;
export const TextLink = styled.button`
  margin-right: 0 auto;
  background-color: white;
  border: none;
  border-right: 1px solid black;
  font-size: 1.2em;
  cursor : pointer;
  transition-duration : 1.5s;
  margin : 5px;
  &:hover {
    color: #6ba543;
  }
`;
