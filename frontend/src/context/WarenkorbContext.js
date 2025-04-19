import React, { createContext, useContext, useState, useEffect } from 'react';

const WarenkorbContext = createContext();

export const WarenkorbProvider = ({ children }) => {
  const [warenkorb, setWarenkorb] = useState(() => {
    const gespeicherterWarenkorb = localStorage.getItem('warenkorb');
    return gespeicherterWarenkorb ? JSON.parse(gespeicherterWarenkorb) : [];
  });

  useEffect(() => {
    localStorage.setItem('warenkorb', JSON.stringify(warenkorb));
  }, [warenkorb]);

  const addToWarenkorb = (produkt, menge = 1) => {
    if (menge === 0) return;

    setWarenkorb((prev) => {
      const vorhandenes = prev.find((item) => item.id === produkt.id);
      if (vorhandenes) {
        const neueMenge = vorhandenes.menge + menge;
        if (neueMenge <= 0) {
          return prev.filter((item) => item.id !== produkt.id);
        }
        return prev.map((item) =>
          item.id === produkt.id
            ? { ...item, menge: Math.min(neueMenge, 20) }
            : item
        );
      } else if (menge > 0) {
        return [...prev, { ...produkt, menge: Math.min(menge, 20) }];
      }
      return prev;
    });
  };

  const removeFromWarenkorb = (id) => {
    setWarenkorb((prev) => prev.filter((item) => item.id !== id));
  };

  const leeren = () => {
    setWarenkorb([]);
    localStorage.removeItem('warenkorb');
  };

  const warenkorbAnzahl = warenkorb.reduce((sum, item) => sum + item.menge, 0);

  return (
    <WarenkorbContext.Provider
      value={{
        warenkorb,
        warenkorbAnzahl,
        addToWarenkorb,
        removeFromWarenkorb,
        leeren
      }}
    >
      {children}
    </WarenkorbContext.Provider>
  );
};

export const useWarenkorb = () => useContext(WarenkorbContext);
