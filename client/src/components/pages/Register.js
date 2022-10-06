import React, { useEffect } from 'react';
import GoogleBtn from '../organism/GoogleBtn';
import { postLoginToken } from '../../hooks/postLoginToken';
import { useDispatch, useSelector } from 'react-redux';
import { setLoginChange } from '../../actions';
import { useNavigate } from 'react-router-dom';

const Register = () => {
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
    navigate('/info');
  }, [loginState]);

  return (
    <div>
      <h1>회원가입</h1>
      <GoogleBtn onGoogleSignIn={onGoogleSign} text="signup_with"></GoogleBtn>
    </div>
  );
};

export default Register;
