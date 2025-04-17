import React from 'react';
import { useParams } from 'react-router-dom';
import produkte from './data/produkte';
import './App.css';

function ProduktDetail() {
  const { id } = useParams();
  const produkt = produkte.find(p => p.id === parseInt(id));

  if (!produkt) {
    return (
      <div className="produkte-page">
        <h1>Produkt nicht gefunden</h1>
        <p>Das angeforderte Produkt existiert nicht.</p>
      </div>
    );
  }

  return (
    <div className="produkt-detail-page">
      <div className="produkt-detail">
        <img src={produkt.bild} alt={produkt.name} />
        <div className="detail-info">
          <h2>{produkt.name}</h2>
          <p className="preis">{produkt.preis}</p>
          <p className="beschreibung">{produkt.beschreibung}</p>
        </div>
      </div>
    </div>
  );
}

export default ProduktDetail;
