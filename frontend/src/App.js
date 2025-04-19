import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Header from './Header';
import Home from './Home';
import Produkte from './Produkte';
import KategorieYerbaMate from './KategorieYerbaMate';
import KategorieZubehoer from './KategorieZubehoer';
import LoginForm from './LoginForm';
import Register from './Register';
import ForgotPassword from './ForgotPassword';
import ProduktDetail from './ProduktDetail';
import Profile from './Profile';
import Suche from './Suche';
import Cart from './Cart';
import Kasse from './Kasse';
import Danke from './Danke';

function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/alle-produkte" element={<Produkte />} />
        <Route path="/kategorie/yerba-mate" element={<KategorieYerbaMate />} />
        <Route path="/kategorie/zubehoer" element={<KategorieZubehoer />} />
        <Route path="/produkt/:id" element={<ProduktDetail />} />
        <Route path="/login" element={<LoginForm />} />
        <Route path="/register" element={<Register />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path="/profil" element={<Profile />} />
        <Route path="/suche" element={<Suche />} />
        <Route path="/warenkorb" element={<Cart />} />
        <Route path="/kasse" element={<Kasse />} />
        <Route path="/danke" element={<Danke />} />
      </Routes>
    </>
  );
}

export default App;
