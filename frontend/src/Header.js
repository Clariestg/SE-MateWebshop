import React from 'react';
import { NavLink, Link } from 'react-router-dom';
import { FaUserCircle, FaShoppingCart } from 'react-icons/fa';
import './Header.css';

function Header() {
  return (
    <header className="main-nav">
      <div className="nav-left">
        <NavLink to="/" className="nav-item">Startseite</NavLink>
        <div className="nav-item dropdown">
            Kategorien ▾
            <div className="dropdown-content">
            <NavLink to="/kategorie/yerba-mate">Yerba Mate</NavLink>
            <NavLink to="/kategorie/zubehoer">Zubehör</NavLink>
            </div>
        </div>
        <NavLink to="/produkte" className="nav-item">Alle Produkte</NavLink>
      </div>
      <div className="nav-right">
      <form
          className="suchfeld"
           onSubmit={(e) => {
            e.preventDefault();
          const q = e.target.elements.suchbegriff.value.trim();
        if (q) window.location.href = `/suche?q=${encodeURIComponent(q)}`;
        }}
        >
          <input
         type="text"
         name="suchbegriff"
         placeholder="Suche..."
         className="such-input"
        />
          </form>
        <Link to="/login" className="auth-link">Anmelden</Link>
        <Link to="/register" className="auth-link register">Registrieren</Link>
        <Link to="/profil" className="icon-link"><FaUserCircle /></Link>
        <Link to="/warenkorb" className="icon-link"><FaShoppingCart /></Link>
      </div>
    </header>
  );
}

export default Header;