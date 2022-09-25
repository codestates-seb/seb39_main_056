import './App.css';
import Header from './components/organism/Header';
import GlobalStyles from './style/GlobalStyles';
import { Route, Routes, BrowserRouter as Router } from 'react-router-dom';
import ProductPage from './components/pages/ProductPage';

function App() {
	return (
		<>
			<Router>
				<GlobalStyles />
				<Header />
				<Route path="/product/:id" element={<ProductPage />} />
			</Router>
		</>
	);
}

export default App;
