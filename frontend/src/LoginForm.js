import React from 'react';
import { Link } from 'react-router-dom';
import './App.css';

function LoginForm() {
  return (
    <div className="login-container">
      <h2>Anmelden</h2>
      <form>
        <input type="email" placeholder="E-Mail" required />
        <input type="password" placeholder="Passwort" required />
        <button type="submit">Login</button>
      </form>
      <div className="forgot-password">
        <Link to="/forgot-password">Passwort vergessen?</Link>
      </div>
      <div className="register-link">
        Noch kein Konto? <Link to="/register">Jetzt registrieren</Link>
      </div>
    </div>
  );
}

export default LoginForm;