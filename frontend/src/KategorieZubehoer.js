import React, { useState } from 'react';
import ReactSlider from 'react-slider';
import { Link } from 'react-router-dom';
import produkte from './data/produkte';
import './App.css';

function KategorieZubehoer() {
  const [sortierung, setSortierung] = useState('standard');
  const [preisBereich, setPreisBereich] = useState([0, 25]);

  const gefiltertUndSortiert = () => {
    let gefiltert = produkte
      .filter(p => p.kategorie === 'zubehoer')
      .filter(p => {
        const preis = parseFloat(p.preis.replace(',', '.'));
        return preis >= preisBereich[0] && preis <= preisBereich[1];
      });

    if (sortierung === 'preis-asc') {
      gefiltert.sort((a, b) => parseFloat(a.preis.replace(',', '.')) - parseFloat(b.preis.replace(',', '.')));
    } else if (sortierung === 'preis-desc') {
      gefiltert.sort((a, b) => parseFloat(b.preis.replace(',', '.')) - parseFloat(a.preis.replace(',', '.')));
    }

    return gefiltert;
  };

  return (
    <div className="produkte-page">
      <h1>Zubehör</h1>

      <div className="filter-leiste">
        <div className="sortierung">
          <label htmlFor="sortierung">Sortieren:</label>
          <select id="sortierung" value={sortierung} onChange={e => setSortierung(e.target.value)}>
            <option value="standard">Standard</option>
            <option value="preis-asc">Preis: aufsteigend</option>
            <option value="preis-desc">Preis: absteigend</option>
          </select>
        </div>

        <div className="slider-bereich">
          <label>Preis: {preisBereich[0]} € – {preisBereich[1]} €</label>
          <ReactSlider
            className="preis-slider"
            thumbClassName="slider-thumb"
            trackClassName="slider-track"
            min={0}
            max={25}
            value={preisBereich}
            onChange={setPreisBereich}
            pearling
            minDistance={1}
          />
        </div>
      </div>

      <div className="produkt-grid">
        {gefiltertUndSortiert().map(produkt => (
          <Link to={`/produkt/${produkt.id}`} className="produkt-card no-link-style" key={produkt.id}>
            <img src={produkt.bild} alt={produkt.name} />
            <h3>{produkt.name}</h3>
            <p className="preis">{produkt.preis}</p>
            <p className="beschreibung">{produkt.beschreibung}</p>
          </Link>
        ))}
      </div>
    </div>
  );
}

export default KategorieZubehoer;
