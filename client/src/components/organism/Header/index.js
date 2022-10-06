import logo from '../../../assets/headerLogo.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';
import * as Styled from './style';
import { useDispatch, useSelector } from 'react-redux';
import { setLoginChange } from '../../../actions';
import { useNavigate } from 'react-router-dom';

const HeaderComponent = () => {
  const loginState = useSelector(state => state.loginReducer);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const logoutButton = () => {
    dispatch(setLoginChange(false));
    navigate('/');
  };

  return (
    <Styled.HeaderContainer>
      <Link to="/">
        <Styled.Logo src={logo} />
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
  );
};

export default HeaderComponent;
