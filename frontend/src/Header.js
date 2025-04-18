import React from 'react';
import { Link } from 'react-router-dom';
import { FaShoppingCart, FaUser } from 'react-icons/fa';
import { useWarenkorb } from './context/WarenkorbContext';
import './Header.css';

function Header() {
  const { warenkorbAnzahl } = useWarenkorb();

  return (
    <header className="main-nav">
      <div className="nav-left">
        <Link to="/" className="nav-item">Startseite</Link>

        <div className="dropdown nav-item">
          <span>Kategorien</span>
          <div className="dropdown-content">
            <Link to="/kategorie/yerba-mate">Yerba Mate</Link>
            <Link to="/kategorie/zubehoer">Zubehör</Link>
          </div>
        </div>

        <Link to="/alle-produkte" className="nav-item">Alle Produkte</Link>
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
          <input type="text" name="suchbegriff" placeholder="Suche..." className="such-input" />
        </form>

        <Link to="/login" className="icon-link">
          <FaUser />
        </Link>

        <Link to="/warenkorb" className="icon-link">
          <FaShoppingCart />
          {warenkorbAnzahl > 0 && <span className="cart-count">{warenkorbAnzahl}</span>}
        </Link>
      </div>
    </header>
  );
}

export default Header;
