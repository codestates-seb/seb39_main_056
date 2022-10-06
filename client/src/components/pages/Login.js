import React, { useEffect } from 'react';
import GoogleBtn from '../organism/GoogleBtn';
import { postLoginToken } from '../../hooks/postLoginToken';
import { useDispatch, useSelector } from 'react-redux';
import { setLoginChange } from '../../actions';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const loginState = useSelector(state => state.loginReducer);
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
    <div>
      <h1>로그인</h1>
      <GoogleBtn onGoogleSignIn={onGoogleSign} text="signin_with"></GoogleBtn>
    </div>
  );
};

export default Login;
