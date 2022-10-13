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
  padding: 1.5rem 0;
  display: flex;
  font-family: 'MICEGothic_Bold';
  justify-content: center;
  align-items: center;
  color: #145f37;
`;

const HeaderComponent = () => {
  const loginState = useSelector(state => state.loginReducer);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const logoutButton = () => {
    localStorage.removeItem('JWT TOKEN');
    dispatch(setLoginChange(false));
    // window.location.reload();
    alert('로그아웃 되었습니다.');
    navigate('/');
  };

  const handleSubmit = () => {
    alert('검색 기능은 추후 구현 예정입니다.');
  };

  return (
    <>
      <HHeader>
        채식유형만 선택하세요, 상품은 채식이들이 찾아드릴게요.🥕
      </HHeader>
      <Styled.HeaderContainer>
        <Link to="/">
          <Styled.Logo src="https://cdn.discordapp.com/attachments/1010081876670681199/1027579545961910272/5.png" />
        </Link>
        <Styled.InputBox>
          <form onSubmit={handleSubmit}>
            <FontAwesomeIcon
              icon={faMagnifyingGlass}
              size="2x"
              style={{ position: 'absolute', margin: '10px' }}
            />
            <Styled.SerchBar />
          </form>
        </Styled.InputBox>
        <Styled.ButtonContainer>
          {loginState ? (
            <Styled.TextLink onClick={logoutButton}>로그아웃</Styled.TextLink>
          ) : (
            <Link to="/login">
              <Styled.TextLink>로그인 </Styled.TextLink>
            </Link>
          )}
          {loginState ? (
            <Link to="/mypage">
              <Styled.TextLink>마이페이지</Styled.TextLink>
            </Link>
          ) : (
            <Link to="/signup">
              <Styled.TextLink>회원가입</Styled.TextLink>
            </Link>
          )}
          {loginState ? (
            <Link to="/cart">
              <Styled.TextLink>장바구니</Styled.TextLink>
            </Link>
          ) : (
            <Link to="/login">
              <Styled.TextLink>장바구니</Styled.TextLink>
            </Link>
          )}
        </Styled.ButtonContainer>
      </Styled.HeaderContainer>
    </>
  );
};

export default HeaderComponent;
