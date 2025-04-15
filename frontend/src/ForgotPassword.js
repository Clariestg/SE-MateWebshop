import React from 'react';
import { Link } from 'react-router-dom';
import './App.css';

function ForgotPassword() {
  return (
    <div className="login-container">
      <Link to="/login" className="back-button">← Zurück zur Anmeldung</Link>
      <h2>Passwort zurücksetzen</h2>
      <form>
        <input type="email" placeholder="E-Mail eingeben" required />
        <button type="submit">Zurücksetzen</button>
      </form>
    </div>
  );
}

export default ForgotPassword;