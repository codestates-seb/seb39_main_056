import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import GlobalStyles from './style/GlobalStyles';
import Type from './components/pages/Type.js';
import Login from './components/pages/Login';
import Register from './components/pages/Register';
import Info from './components/pages/Info';

function App() {
  return (
    <div>
      <GlobalStyles />
      <BrowserRouter>
        <Routes>
          <Route path="type/" element={<Type />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/info" element={<Info />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
