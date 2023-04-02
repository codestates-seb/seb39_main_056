import React, { useEffect } from 'react';
import Mypage from './components/pages/Mypage';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import GlobalStyles from './style/GlobalStyles';
import BodyContainer from './components/atom/BodyContainer';
import MainPage from './components/pages/MainPage';
import Type from './components/pages/Type.js';
import Login from './components/pages/Login';
import Info from './components/pages/Info';
import Header from './components/organism/Header';
import ProductDetail from './components/pages/ProductDetail';
import History from './components/pages/History';
import Cart from './components/pages/Cart';
import axios from 'axios';
import { useDispatch } from 'react-redux';
import { setLoginChange } from './actions';
import { setTokenHeader } from './service/setTokenHeader';
import Footer from './components/organism/Footer';

axios.defaults.withCredentials = true;

function App() {
  const dispatch = useDispatch();
  const tokenHeader = setTokenHeader();

  if(!tokenHeader) {
    dispatch(setLoginChange(false));
  }

  useEffect(() => {
    axios({
      method: 'get',
      url: `${process.env.REACT_APP_API_URL}/members/info`,
      headers: {
        ...tokenHeader,
      },
    }).then(res => {
      if (res.status === 200) {
        dispatch(setLoginChange(true));
      } else {
        dispatch(setLoginChange(false));
      }
    });
  }, []);

  return (
    <>
      <BrowserRouter>
        <GlobalStyles />
        <Header />
        <BodyContainer>
          <Routes>
            <Route path="/" element={<MainPage />} />
            <Route path="/mypage" element={<Mypage />} />
            <Route path="/mypage/history" element={<History />} />
            <Route path="/product/:id" element={<ProductDetail />} />
            <Route path="/type/:id" element={<Type />} />
            <Route path="/login" element={<Login />} />
            {/* <Route path="/signup" element={<Signup />} /> */}
            <Route path="/signup" element={<Info />} />
            <Route path="/cart" element={<Cart />} />
          </Routes>
        </BodyContainer>
      </BrowserRouter>
      <Footer />
    </>
  );
}

export default App;
