import React from 'react';
import './App.css';
import { useWarenkorb } from './context/WarenkorbContext';
import { useNavigate } from 'react-router-dom';

function Cart() {
  const {
    warenkorb,
    addToWarenkorb,
    removeFromWarenkorb,
    leeren
  } = useWarenkorb();

  const navigate = useNavigate();

  const handleMengeÄndern = (produkt, neueMenge) => {
    if (!neueMenge || neueMenge < 1 || neueMenge > 20) return;
    const differenz = neueMenge - produkt.menge;
    addToWarenkorb(produkt, differenz);
  };

  const gesamtpreis = warenkorb.reduce((sum, item) => {
    const preis = parseFloat(item.preis.replace(',', '.'));
    return sum + preis * item.menge;
  }, 0);

  if (warenkorb.length === 0) {
    return (
      <div className="produkte-page">
        <h2>Dein Warenkorb ist leer</h2>
        <p><a href="/alle-produkte">Hier geht’s zu unseren Produkten</a></p>
      </div>
    );
  }

  return (
    <div className="produkte-page">
      <h2>Warenkorb</h2>

      <div className="warenkorb-liste">
        {warenkorb.map((item) => (
          <div key={item.id} className="warenkorb-item">
            <img src={item.bild} alt={item.name} />
            <div className="info">
              <h4>{item.name}</h4>
              <p>{item.preis}</p>
              <div className="menge-aendern">
                <label>Menge:</label>
                <input
                  type="number"
                  min="1"
                  max="20"
                  value={item.menge}
                  onChange={(e) =>
                    handleMengeÄndern(item, parseInt(e.target.value || 1))
                  }
                />
              </div>
              <button
                onClick={() => removeFromWarenkorb(item.id)}
                className="entfernen-btn"
              >
                Entfernen
              </button>
            </div>
          </div>
        ))}
      </div>

      <div className="warenkorb-summe">
        <h3>Gesamtsumme: {gesamtpreis.toFixed(2).replace('.', ',')} €</h3>
      </div>

      <div className="warenkorb-aktionen">
        <button className="leeren-btn" onClick={leeren}>
          Warenkorb leeren
        </button>
      </div>

      <div className="kasse-start">
        <button className="kasse-btn" onClick={() => navigate('/kasse')}>
          Zur Kasse
        </button>
      </div>
    </div>
  );
}

export default Cart;
