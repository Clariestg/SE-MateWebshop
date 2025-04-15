import React from 'react';
import { NavLink, Link } from 'react-router-dom';
import './Header.css';

function Header() {
  return (
    <header className="main-nav">
      <div className="nav-left">
        <NavLink to="/" className="nav-item">Startseite</NavLink>
        <NavLink to="/kategorien" className="nav-item">Kategorien</NavLink>
        <NavLink to="/produkte" className="nav-item">Alle Produkte</NavLink>
      </div>
      <div className="nav-right">
        <Link to="/login" className="auth-link">Anmelden</Link>
        <Link to="/register" className="auth-link register">Registrieren</Link>
      </div>
    </header>
  );
}

export default Header;