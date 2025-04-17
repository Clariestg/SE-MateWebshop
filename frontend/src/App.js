import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './Home';
import Kategorien from './Kategorien';
import Produkte from './Produkte';
import LoginForm from './LoginForm';
import Register from './Register';
import ForgotPassword from './ForgotPassword';
import Profile from './Profile';
import Cart from './Cart';
import KategorieYerbaMate from './KategorieYerbaMate';
import KategorieZubehoer from './KategorieZubehoer';
import Header from './Header';
import Suche from './Suche';
import ProduktDetail from './ProduktDetail';
import './App.css';

function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/kategorien" element={<Kategorien />} />
        <Route path="/produkte" element={<Produkte />} />
        <Route path="/login" element={<LoginForm />} />
        <Route path="/register" element={<Register />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path="/profil" element={<Profile />} />
        <Route path="/warenkorb" element={<Cart />} />
        <Route path="/kategorie/yerba-mate" element={<KategorieYerbaMate />} />
        <Route path="/kategorie/zubehoer" element={<KategorieZubehoer />} />
        <Route path="/suche" element={<Suche />} />
        <Route path="/produkt/:id" element={<ProduktDetail />} />
</Routes>
    </Router>
  );
}

export default App;