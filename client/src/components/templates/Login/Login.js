import React, { useEffect } from 'react';
import GoogleBtn from '../../molecule/GoogleBtn';
import { postLoginToken } from '../../../hooks/postLoginToken';
import { useDispatch, useSelector } from 'react-redux';
import { setLoginChange } from '../../../actions';
import { useNavigate } from 'react-router-dom';
import CenterBox from '../../templates/CenterBox';
import StyledInput from '../../atom/StyledInput';
import MaincolorBtn from '../../atom/MainColorBtn';
import H1 from '../../atom/H1';
import H3 from '../../atom/H3';
import * as Styled from './Style';

const Index = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const loginState = useSelector(state => state.loginReducer);

  //   const onLogin =

  const onGoogleSign = async res => {
    const { credential } = res;
    const result = await postLoginToken(credential);
    dispatch(setLoginChange(result));
  };

  useEffect(() => {
    if (!loginState) return;
    navigate('/');
  }, [loginState]);

  return (
    <CenterBox width="30vw">
      <Styled.InnerBox>
        <H1>로그인</H1>
        <Styled.LoginContainer>
          <form>
            <Styled.LoginInput
              type="email"
              placeholder="이메일"
            ></Styled.LoginInput>
            <Styled.LoginInput
              type="password"
              placeholder="비밀번호"
            ></Styled.LoginInput>
            <Styled.LoginBtn>로그인</Styled.LoginBtn>
          </form>
        </Styled.LoginContainer>
        <Styled.SocialContainer>
          <Styled.LoginH3>소셜 로그인</Styled.LoginH3>
          <GoogleBtn
            onGoogleSignIn={onGoogleSign}
            text="signin_with"
          ></GoogleBtn>
        </Styled.SocialContainer>
      </Styled.InnerBox>
    </CenterBox>
  );
};

export default Index;
