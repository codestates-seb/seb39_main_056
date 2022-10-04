import logo from '../../assets/logo.jpg';
import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';

const SerchBar = styled.input`
  width: 100%;
  height: 50px;
  font-size: 1.5em;
  padding-left: 50px;
`;
const InputBox = styled.div`
  flex-basis: 30%;
  position: relative;
`;
const Logo = styled.img``;
const HeaderContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
const ButtonContainer = styled.div``;
const TextLink = styled.button`
  margin-right: 0 auto;
  background-color: white;
  border: none;
  border-right: 1px solid black;
  font-size: 1.2em;
  &:hover {
    color: red;
  }
`;
const Header = () => {
  return (
    <HeaderContainer>
      <Link to="/">
        <Logo src={logo} />
      </Link>
      <InputBox>
        <FontAwesomeIcon
          icon={faMagnifyingGlass}
          size="2x"
          style={{ position: 'absolute', margin: '10px' }}
        />
        <SerchBar />
      </InputBox>
      <ButtonContainer>
        <Link to="/login">
          <TextLink>로그인</TextLink>
        </Link>
        <Link to="/signup">
          <TextLink>회원가입</TextLink>
        </Link>
        <Link to="/cart">
          <TextLink>장바구니</TextLink>
        </Link>
      </ButtonContainer>
    </HeaderContainer>
  );
};

export default Header;
