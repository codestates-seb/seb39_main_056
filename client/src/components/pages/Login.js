import React, { useEffect } from 'react';
import { postLoginToken } from '../../hooks/postLoginToken';
import { useDispatch, useSelector } from 'react-redux';
import { setLoginChange } from '../../actions';
import { useNavigate } from 'react-router-dom';
import Index from '../templates/Login/Login';

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

  return <Index />;
};

export default Login;
