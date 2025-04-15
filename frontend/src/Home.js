import React from 'react';
import './App.css';

function Home() {
  return (
    <div className="home-page">
      <header className="hero-section">
        <h1>Yerba Mate – Energie aus der Natur</h1>
        <p>Entdecke die Welt von Mate-Tee – traditionell, belebend und gesund.</p>

      </header>

      <section className="info-section">
        <h2>Warum Yerba Mate?</h2>
        <div className="features">
          <div className="feature">
            <h3>🌿 Natürlich</h3>
            <p>100% natürlich & ohne künstliche Zusätze.</p>
          </div>
          <div className="feature">
            <h3>⚡ Energie</h3>
            <p>Sanfter Koffeinkick ohne Nervosität.</p>
          </div>
          <div className="feature">
            <h3>💧 Detox</h3>
            <p>Unterstützt den Stoffwechsel & die Verdauung.</p>
          </div>
        </div>
      </section>

      <footer className="home-footer">
        <p>© 2025 Yerba Mate Shop – Frische aus Südamerika</p>
      </footer>
    </div>
  );
}

export default Home;