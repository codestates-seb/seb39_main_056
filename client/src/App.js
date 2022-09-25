import './App.css';
import Header from './components/organism/Header';
import GlobalStyles from './style/GlobalStyles';
import { Route, Routes, BrowserRouter as Router } from 'react-router-dom';

function App() {
	return (
		<>
			<Router>
				<GlobalStyles />
				<Header />
			</Router>
		</>
	);
}

export default App;
