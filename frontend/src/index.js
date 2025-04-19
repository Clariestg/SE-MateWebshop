import React from 'react';
import ReactDOM from 'react-dom/client';
import './App.css';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import { WarenkorbProvider } from './context/WarenkorbContext';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <WarenkorbProvider>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </WarenkorbProvider>
  </React.StrictMode>
);
