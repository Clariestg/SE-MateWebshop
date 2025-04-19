import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useWarenkorb } from './context/WarenkorbContext';
import './App.css';

const Schritte = ['Lieferadresse', 'Rechnungsadresse', 'Versandoption', 'Zahlungsart', 'Übersicht'];

function Kasse() {
  const navigate = useNavigate();
  const { warenkorb, leeren } = useWarenkorb();

  const [schritt, setSchritt] = useState(0);
  const [balkenFehler, setBalkenFehler] = useState(false);
  const [fehler, setFehler] = useState({});

  const [lieferadresse, setLieferadresse] = useState({
    vorname: '', nachname: '', strasse: '', hausnummer: '', plz: '', email: '', telefon: ''
  });

  const [rechnungsadresse, setRechnungsadresse] = useState({
    vorname: '', nachname: '', strasse: '', hausnummer: '', plz: '', email: '', telefon: ''
  });

  const [selbeAdresse, setSelbeAdresse] = useState(false);
  const [versand, setVersand] = useState('');
  const [zahlung, setZahlung] = useState('');

  const handleWeiter = () => {
    if (!schrittValid()) {
      blinkFehler();
      return;
    }

    if (schritt === 1 && selbeAdresse) {
      setRechnungsadresse({ ...lieferadresse });
    }

    if (schritt < Schritte.length - 1) {
      setSchritt(schritt + 1);
    } else {
        leeren();
        navigate('/danke');
    }
  };

  const blinkFehler = () => {
    setBalkenFehler(true);
    setTimeout(() => setBalkenFehler(false), 500);
  };

  const handleZurueck = () => {
    if (schritt > 0) setSchritt(schritt - 1);
  };

  const validateFelder = (daten) => {
    const neueFehler = {};
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const telRegex = /^[\d+\s-]*$/;

    for (const [feld, val] of Object.entries(daten)) {
      if (feld !== 'telefon' && val.trim() === '') {
        neueFehler[feld] = 'Pflichtfeld';
      }
      if (feld === 'email' && val && !emailRegex.test(val)) {
        neueFehler[feld] = 'Ungültige E-Mail-Adresse';
      }
      if (feld === 'telefon' && val && !telRegex.test(val)) {
        neueFehler[feld] = 'Nur Ziffern, +, -, Leerzeichen erlaubt';
      }
    }

    return neueFehler;
  };

  const schrittValid = () => {
    let err = {};
    if (schritt === 0) err = validateFelder(lieferadresse);
    if (schritt === 1 && !selbeAdresse) err = validateFelder(rechnungsadresse);
    if (schritt === 2 && !versand) err = { versand: 'Bitte wählen' };
    if (schritt === 3 && !zahlung) err = { zahlung: 'Bitte wählen' };

    setFehler(err);
    return Object.keys(err).length === 0;
  };

  const handleChange = (setFunktion, feld, wert) => {
    setFunktion(prev => ({ ...prev, [feld]: wert }));
  };

  const renderAdresseForm = (werte, setWerte, fehlerMap) => (
    <>
      <div className="feld-gruppe">
        <label>Vorname *</label>
        <input value={werte.vorname} onChange={e => handleChange(setWerte, 'vorname', e.target.value)} />
        {fehlerMap.vorname && <p className="fehler-text">{fehlerMap.vorname}</p>}
      </div>
      <div className="feld-gruppe">
        <label>Nachname *</label>
        <input value={werte.nachname} onChange={e => handleChange(setWerte, 'nachname', e.target.value)} />
        {fehlerMap.nachname && <p className="fehler-text">{fehlerMap.nachname}</p>}
      </div>
      <div className="feld-gruppe-row">
        <div className="feld-gruppe">
          <label>Straße *</label>
          <input value={werte.strasse} onChange={e => handleChange(setWerte, 'strasse', e.target.value)} />
          {fehlerMap.strasse && <p className="fehler-text">{fehlerMap.strasse}</p>}
        </div>
        <div className="feld-gruppe">
          <label>Hausnummer *</label>
          <input value={werte.hausnummer} onChange={e => handleChange(setWerte, 'hausnummer', e.target.value)} />
          {fehlerMap.hausnummer && <p className="fehler-text">{fehlerMap.hausnummer}</p>}
        </div>
      </div>
      <div className="feld-gruppe">
        <label>Postleitzahl *</label>
        <input value={werte.plz} onChange={e => handleChange(setWerte, 'plz', e.target.value)} />
        {fehlerMap.plz && <p className="fehler-text">{fehlerMap.plz}</p>}
      </div>
      <div className="feld-gruppe">
        <label>E-Mail *</label>
        <input type="email" value={werte.email} onChange={e => handleChange(setWerte, 'email', e.target.value)} />
        {fehlerMap.email && <p className="fehler-text">{fehlerMap.email}</p>}
      </div>
      <div className="feld-gruppe">
        <label>Telefon</label>
        <input value={werte.telefon} onChange={e => handleChange(setWerte, 'telefon', e.target.value)} />
        {fehlerMap.telefon && <p className="fehler-text">{fehlerMap.telefon}</p>}
      </div>
    </>
  );

  const formatAdresseBlock = (daten) => (
    <div className="adressblock">
      <p>{daten.vorname} {daten.nachname}</p>
      <p>{daten.strasse} {daten.hausnummer}</p>
      <p>{daten.plz}</p>
      <p>{daten.email}</p>
      <p>{daten.telefon}</p>
    </div>
  );

  const gesamt = warenkorb.reduce((sum, p) =>
    sum + parseFloat(p.preis.replace(',', '.')) * p.menge, 0).toFixed(2).replace('.', ',');

  const renderSchritt = () => {
    switch (schritt) {
      case 0:
        return (
          <div className="form-bereich">
            <h2>Lieferadresse</h2>
            {renderAdresseForm(lieferadresse, setLieferadresse, fehler)}
          </div>
        );
      case 1:
        return (
          <div className="form-bereich">
            <h2>Rechnungsadresse</h2>
            <label className="checkbox">
              <input type="checkbox" checked={selbeAdresse} onChange={() => setSelbeAdresse(!selbeAdresse)} />
              Rechnungsadresse entspricht der Lieferadresse
            </label>
            {!selbeAdresse && renderAdresseForm(rechnungsadresse, setRechnungsadresse, fehler)}
          </div>
        );
      case 2:
        return (
          <div className="form-bereich">
            <h2>Versandoption</h2>
            <div className="radio-option">
              <label>Standardversand (3–5 Tage)</label>
              <input type="radio" name="versand" value="Standardversand"
                checked={versand === 'Standardversand'}
                onChange={(e) => setVersand(e.target.value)} />
            </div>
            <div className="radio-option">
              <label>Expressversand (1–2 Tage)</label>
              <input type="radio" name="versand" value="Express"
                checked={versand === 'Express'}
                onChange={(e) => setVersand(e.target.value)} />
            </div>
            {fehler.versand && <p className="fehler-text">{fehler.versand}</p>}
          </div>
        );
      case 3:
        return (
          <div className="form-bereich">
            <h2>Zahlungsart</h2>
            <div className="radio-option">
              <label>Kreditkarte</label>
              <input type="radio" name="zahlung" value="Kreditkarte"
                checked={zahlung === 'Kreditkarte'}
                onChange={(e) => setZahlung(e.target.value)} />
            </div>
            <div className="radio-option">
              <label>PayPal</label>
              <input type="radio" name="zahlung" value="PayPal"
                checked={zahlung === 'PayPal'}
                onChange={(e) => setZahlung(e.target.value)} />
            </div>
            <div className="radio-option">
              <label>Rechnung</label>
              <input type="radio" name="zahlung" value="Rechnung"
                checked={zahlung === 'Rechnung'}
                onChange={(e) => setZahlung(e.target.value)} />
            </div>
            {fehler.zahlung && <p className="fehler-text">{fehler.zahlung}</p>}
          </div>
        );
      case 4:
        return (
          <div className="form-bereich">
            <h2>Bestellübersicht</h2>
            <h4>Lieferadresse</h4>
            {formatAdresseBlock(lieferadresse)}
            <h4>Rechnungsadresse</h4>
            {formatAdresseBlock(rechnungsadresse)}
            <p><strong>Versand:</strong> {versand}</p>
            <p><strong>Zahlung:</strong> {zahlung}</p>
          </div>
        );
      default:
        return null;
    }
  };

  return (
    <div className="checkout-layout">
      <div className="kasse-page">
        <div className={`fortschritt ${balkenFehler ? 'fehler' : ''}`}>
          <div className="balken" style={{ width: `${((schritt + 1) / Schritte.length) * 100}%` }} />
          <div className="schritte-labels">
            {Schritte.map((s, i) => (
              <span key={i} className={i <= schritt ? 'aktiv' : ''}>{s}</span>
            ))}
          </div>
        </div>

        {renderSchritt()}

        <div className="kasse-buttons">
          {schritt > 0 && <button onClick={handleZurueck}>Zurück</button>}
          <button onClick={handleWeiter}>
            {schritt === Schritte.length - 1 ? 'Bestellung aufgeben' : 'Weiter'}
          </button>
        </div>
      </div>

      <div className="checkout-seiteninfo">
  <h3>Deine Produkte</h3>
  {warenkorb.map((item) => (
    <p key={item.id}>
      {item.menge}× {item.name} – {item.preis}
    </p>
  ))}
  <p><strong>Gesamtsumme: {gesamt} €</strong></p>

  <hr />

  {schritt >= 0 && (
    <>
      <h4>Lieferadresse</h4>
      <p>{lieferadresse.vorname} {lieferadresse.nachname}</p>
      <p>{lieferadresse.strasse} {lieferadresse.hausnummer}</p>
      <p>{lieferadresse.plz}</p>
      <p>{lieferadresse.email}</p>
      <p>{lieferadresse.telefon}</p>
      <hr />
    </>
  )}

  {schritt >= 1 && !selbeAdresse && (
    <>
      <h4>Rechnungsadresse</h4>
      <p>{rechnungsadresse.vorname} {rechnungsadresse.nachname}</p>
      <p>{rechnungsadresse.strasse} {rechnungsadresse.hausnummer}</p>
      <p>{rechnungsadresse.plz}</p>
      <p>{rechnungsadresse.email}</p>
      <p>{rechnungsadresse.telefon}</p>
      <hr />
    </>
  )}

  {schritt >= 2 && (
    <>
      <h4>Versandoption</h4>
      <p>{versand}</p>
      <hr />
    </>
  )}

  {schritt >= 3 && (
    <>
      <h4>Zahlungsart</h4>
      <p>{zahlung}</p>
      <hr />
    </>
  )}
</div>


    </div>
  );
}

export default Kasse;
