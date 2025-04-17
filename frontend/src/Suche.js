import React from 'react';
import { useLocation } from 'react-router-dom';
import produkte from './data/produkte';
import './App.css';

function useQuery() {
  return new URLSearchParams(useLocation().search);
}

function Suche() {
  const query = useQuery();
  const keyword = query.get('q')?.toLowerCase() || '';

  const gefundeneProdukte = produkte.filter(p =>
    p.name.toLowerCase().includes(keyword) || p.beschreibung.toLowerCase().includes(keyword)
  );

  return (
    <div className="produkte-page">
      <h1>Suchergebnisse für: "{keyword}"</h1>

      {gefundeneProdukte.length === 0 ? (
        <p>Keine Produkte gefunden.</p>
      ) : (
        <div className="produkt-grid">
          {gefundeneProdukte.map(produkt => (
            <div className="produkt-card" key={produkt.id}>
              <img src={produkt.bild} alt={produkt.name} />
              <h3>{produkt.name}</h3>
              <p className="preis">{produkt.preis}</p>
              <p className="beschreibung">{produkt.beschreibung}</p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default Suche;
