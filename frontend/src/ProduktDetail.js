import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import produkte from './data/produkte';
import './App.css';
import { useWarenkorb } from './context/WarenkorbContext';

function ProduktDetail() {
  const { id } = useParams();
  const produkt = produkte.find(p => p.id === parseInt(id));
  const { addToWarenkorb } = useWarenkorb();
  const [menge, setMenge] = useState(1);

  if (!produkt) {
    return (
      <div className="produkte-page">
        <h1>Produkt nicht gefunden</h1>
        <p>Das angeforderte Produkt existiert nicht.</p>
      </div>
    );
  }

  const handleMengenEingabe = (e) => {
    const value = parseInt(e.target.value || '1');
    if (value < 1) setMenge(1);
    else if (value > 20) setMenge(20);
    else setMenge(value);
  };

  return (
    <div className="produkt-detail-page">
      <div className="produkt-detail">
        <img src={produkt.bild} alt={produkt.name} />
        <div className="detail-info">
          <h2>{produkt.name}</h2>
          <p className="preis">{produkt.preis}</p>
          <p className="beschreibung">{produkt.beschreibung}</p>

          <div className="menge-und-warenkorb">
            <label htmlFor="menge">Menge:</label>
            <input
              id="menge"
              type="number"
              min="1"
              max="20"
              value={menge}
              onChange={handleMengenEingabe}
            />
            <button
              className="in-den-warenkorb-btn"
              onClick={() => addToWarenkorb(produkt, menge)}
            >
              In den Warenkorb
            </button>
          </div>

          {produkt.details && (
            <div className="zusatzinfos">
              <h4>Details</h4>
              <ul>
                {produkt.details.map((info, index) => (
                  <li key={index}>{info}</li>
                ))}
              </ul>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default ProduktDetail;
