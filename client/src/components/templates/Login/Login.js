import React, { useEffect } from 'react';
import GoogleBtn from '../../molecule/GoogleBtn';
import { postLoginToken } from '../../../service/postLoginToken';
import { useDispatch, useSelector } from 'react-redux';
import { setLoginChange } from '../../../actions';
import { useNavigate } from 'react-router-dom';
import CenterBox from '../../templates/CenterBox';
import H1 from '../../atom/H1';
import * as Styled from './Style';
import axios from 'axios';
import $ from 'jquery';

const Index = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const loginState = useSelector(state => state.loginReducer);

  const handleSubmit = e => {
    e.preventDefault();
    const email = e.target[0].value;
    const password = e.target[1].value;

    if (email === '' || password === '') {
      alert('빈칸을 채워주세요.');
    }
    const authInfo = {
      email: email,
      password: password,
    };

    axios({
      method: 'post',
      url: `${process.env.REACT_APP_API_URL}/auth/login`,
      header: {
        Accept: 'application/json',
        'Content-type': 'application/json',
      },
      data: JSON.stringify(authInfo),
    })
      .then(res => {
        if (res.status === 200) {
          localStorage.setItem('JWT TOKEN', res.headers.authorization.slice(6));
          localStorage.setItem('JWT EXPIRATION', res.headers.expiration);
          console.log(res.headers.expiration)
          dispatch(setLoginChange(true));
          navigate('/');
        }
      })
      .catch(e => {
        alert("이메일이나 패스워드가 일치하지 않습니다.");
      });
  };

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
          <form id="loginForm"
            onSubmit={e => {
              handleSubmit(e);
            }}
          >
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

        {/* <Styled.SocialContainer>
          <Styled.LoginH3>소셜 로그인</Styled.LoginH3>
          <GoogleBtn
            onGoogleSignIn={onGoogleSign}
            text="signin_with"
          ></GoogleBtn>
        </Styled.SocialContainer> */}

      </Styled.InnerBox>
    </CenterBox>
  );
};

export default Index;
