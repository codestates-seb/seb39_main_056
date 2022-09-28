import React from 'react';
import Mypage from './components/pages/Mypage';
import ProductDetail from './components/pages/ProductDetail';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import GlobalStyles from './style/GlobalStyles';
import BodyContainer from './components/atom/BodyContainer';
import MainPage from './components/pages/MainPage';
import Type from './components/pages/Type.js';
import Login from './components/pages/Login';
import Register from './components/pages/Register';
import Info from './components/pages/Info';
import Header from './components/organism/Header';

function App() {
  return (
    <div>
      <GlobalStyles />
      <BrowserRouter>
        <BodyContainer>
          <Routes>
          <Header />
            <Route path="/" element={<MainPage />} />
            <Route path="/mypage/:id" element={<Mypage />} />
            <Route path="/product/:id" element={<ProductDetail />} />
            <Route path="type/" element={<Type />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/info" element={<Info />} />
          </Routes>
        </BodyContainer>
      </BrowserRouter>
    </div>
  );
}

export default App;
