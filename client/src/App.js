import BodyContainer from './components/atom/BodyContainer';
import MainPage from './components/pages/MainPage';
import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Mypage from './components/pages/Mypage';
import ProductDetail from './components/pages/ProductDetail';

function App() {
	return (
		<BrowserRouter>
			<div className="App">
				<BodyContainer>
					<Routes>
						<Route path="/" element={<MainPage />} />
						<Route path="/mypage/:id" element={<Mypage />} />
						<Route path="/product/:id" element={<ProductDetail />} />
					</Routes>
				</BodyContainer>
			</div>
		</BrowserRouter>
	);
}

export default App;
