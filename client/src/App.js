import React from 'react';
import Mypage from './components/pages/Mypage';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import GlobalStyles from './style/GlobalStyles';
import BodyContainer from './components/atom/BodyContainer';
import MainPage from './components/pages/MainPage';
import Type from './components/pages/Type.js';
import Login from './components/pages/Login';
import Signup from './components/pages/Signup';
import Info from './components/pages/Info';
import Header from './components/organism/Header';
import Test from './components/templates/Type/Type';
import ProductDetail from './components/pages/ProductDetail';
import History from './components/pages/History';
import Test from './components/templates/Type/Type';
import Cart from './components/pages/Cart';

function App() {
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
          <Route path="/type" element={<Type />} />
          <Route path="/login" element={<Login />} />
          {/* <Route path="/signup" element={<Signup />} /> */}
          <Route path="/info" element={<Info />} />
          <Route path="/test" element={<Test />} />
          <Route path="/cart" element={<Cart />} />
        </Routes>
      </BrowserRouter>
    </BodyContainer>
  );
}

export default App;
