import React, { useEffect } from 'react';
import Mypage from './components/pages/Mypage';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import GlobalStyles from './style/GlobalStyles';
import BodyContainer from './components/atom/BodyContainer';
import MainPage from './components/pages/MainPage';
import Type from './components/pages/Type.js';
import Login from './components/pages/Login';
import Register from './components/pages/Register';
import Info from './components/pages/Info';
import Header from './components/organism/Header';
import ProductDetail from './components/pages/ProductDetail';
import History from './components/pages/History';
import Test from './components/templates/Type/Type';
import ProductDetail from './components/pages/ProductDetail';
import History from './components/pages/History';
import axios from 'axios';
// import Test from './components/templates/Type/Type';
import Cart from './components/pages/Cart';
import { useDispatch, useSelector } from 'react-redux';
import { setLoginChange } from './actions';
import { setTokenHeader } from './service/setTokenHeader';

axios.defaults.withCredentials = true;
// axios.defaults.headers.common['Authorization'] = `Bearer ${ACCESS_TOKEN}`;

function App() {
  const dispatch = useDispatch();
  // const loginState = useSelector(state => state.loginReducer);

  const tokenHeader = setTokenHeader();
  console.log(tokenHeader);

  useEffect(() => {
    axios({
      method: 'get',
      url: 'http://192.168.5.122:8080/members/info',
      headers: {
        ...tokenHeader,
      },
    }).then(res => {
      if (res.status === 200) {
        console.log('현재 상태는 로그인됏엉');
        dispatch(setLoginChange(true));
      } else {
        console.log('현재 상태는 유저가 아니양');
        dispatch(setLoginChange(false));
      }
    });
  }, []);

  return (
    <BodyContainer>
      <GlobalStyles />
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/mypage/:id" element={<Mypage />} />
          <Route path="/mypage/:id/history" element={<History />} />
          <Route path="/product/:id" element={<ProductDetail />} />
          <Route path="/type/:id" element={<Type />} />
          <Route path="/login" element={<Login />} />
          {/* <Route path="/signup" element={<Signup />} /> */}
          <Route path="/info" element={<Info />} />
          {/* <Route path="/test" element={<Test />} /> */}
          <Route path="/cart" element={<Cart />} />
        </Routes>
      </BrowserRouter>
    </BodyContainer>
  );
}

export default App;
