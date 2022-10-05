// import React, { useEffect } from 'react';
// import GoogleBtn from '../molecule/GoogleBtn';
// import { postLoginToken } from '../../hooks/postLoginToken';
// import { useDispatch, useSelector } from 'react-redux';
// import { setLoginChange } from '../../actions';
// import { useNavigate } from 'react-router-dom';
// import CenterBox from '../templates/CenterBox';
// import H1 from '../atom/H1';
// import CenterInnerBox from '../templates/CenterInnerBox';
// import SocialBtnContainer from '../templates/SocialBtnContainer';

// const Login = () => {
//   const navigate = useNavigate();
//   const dispatch = useDispatch();
//   const loginState = useSelector(state => state.loginReducer);

//   const onGoogleSign = async res => {
//     const { credential } = res;
//     const result = await postLoginToken(credential);
//     dispatch(setLoginChange(result));
//   };

//   useEffect(() => {
//     if (!loginState) return;
//     navigate('/info');
//   }, [loginState]);

//   return (
//     <CenterBox width="30vw">
//       <CenterInnerBox>
//         <H1>회원가입</H1>
//         <SocialBtnContainer>
//           <GoogleBtn
//             onGoogleSignIn={onGoogleSign}
//             text="signup_with"
//           ></GoogleBtn>
//         </SocialBtnContainer>
//       </CenterInnerBox>
//     </CenterBox>
//   );
// };

// export default Login;
