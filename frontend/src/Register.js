import React from 'react';
import { Link } from 'react-router-dom';
import './App.css';

function Register() {
  return (
    <div className="login-container">
      <Link to="/login" className="back-button">← Zurück zur Anmeldung</Link>
      <h2>Registrieren</h2>
      <form>
        <input type="text" placeholder="Vorname" required />
        <input type="text" placeholder="Nachname" required />
        <input type="email" placeholder="E-Mail" required />
        <input type="tel" placeholder="Telefonnummer" required />
        <input type="password" placeholder="Passwort" required />
        <button type="submit">Registrieren</button>
      </form>
    </div>
  );
}

export default Register;