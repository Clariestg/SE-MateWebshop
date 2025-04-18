import React from 'react';
import { useNavigate } from 'react-router-dom';
import './App.css';

const Danke = () => {
  const navigate = useNavigate();

  return (
    <div className="danke-container">
      <div className="danke-box">
        <h1>Vielen Dank für deine Bestellung!</h1>
        <p>Wir haben deine Bestellung erhalten und werden sie schnellstmöglich bearbeiten.</p>

        <div className="danke-buttons">
          <button onClick={() => navigate('/')} className="btn">
            Zurück zur Startseite
          </button>
          <button onClick={() => navigate('/alle-produkte')} className="btn">
            Zurück zur Produktübersicht
          </button>
        </div>
      </div>
    </div>
  );
};

export default Danke;
