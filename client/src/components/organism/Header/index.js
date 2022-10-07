// import logo from '../../../assets/headerLogo.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';
import * as Styled from './style';
import { useDispatch, useSelector } from 'react-redux';
import { setLoginChange } from '../../../actions';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

const HHeader = styled.div`
  background-color: #c3f0ca;
  height: 10%;
  display: flex;
  font-weight: 600;
  justify-content: center;
  align-items: center;
  color: #145f37;
`;

const HeaderComponent = () => {
  const loginState = useSelector(state => state.loginReducer);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const logoutButton = () => {
    dispatch(setLoginChange(false));
    navigate('/');
  };

  return (
    <>
      <HHeader>
        {' '}
        채식유형만 선택하세요, 상품은 채식이들이 찾아드릴게요.🥕
      </HHeader>
      <Styled.HeaderContainer>
        <Link to="/">
          <Styled.Logo src="https://cdn.discordapp.com/attachments/1010081876670681199/1027579545961910272/5.png" />
        </Link>
        <Styled.InputBox>
          <FontAwesomeIcon
            icon={faMagnifyingGlass}
            size="2x"
            style={{ position: 'absolute', margin: '10px' }}
          />
          <Styled.SerchBar />
        </Styled.InputBox>
        <Styled.ButtonContainer>
          {loginState ? (
            <Styled.TextLink onClick={logoutButton}>로그아웃</Styled.TextLink>
          ) : (
            <Link to="/login">
              <Styled.TextLink>로그인 </Styled.TextLink>
            </Link>
          )}
          <Link to="/resister">
            <Styled.TextLink>회원가입</Styled.TextLink>
          </Link>
          <Link to="/cart">
            <Styled.TextLink>장바구니</Styled.TextLink>
          </Link>
        </Styled.ButtonContainer>
      </Styled.HeaderContainer>
    </>
  );
};

export default HeaderComponent;
