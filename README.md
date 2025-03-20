# SE-MateWebshop
Softwareentwicklungsprojekt:
E-Commerce: Online-Shop für Yerba Mate und Zubehör
Produkte: Thermo, Yerba (Kräuter), Strohhalm

**Beschreibung**: Es soll ein E-Commerce-Onlineshop entwickelt werden für die Produktpalette Yerva Mate und das Zubehör. Der Onlineshop soll möglichst benutzerfreundlich, performant und sicher sein. Die Zielgruppe hierfür sollen Endkunden und Wiederverkäufer sein. Dem Kunden ist es wichtig, ein ansprechendes Design, eine einfache Kaufabwicklung und möglichst flexibel für zukünftige Erweiterungen sein.
**Funktionale Anforderungen**: Produktverwaltung, Benutzerverwaltung, Warenkorb / Bestellprozess, Zahlungsintegration, Versand + Logistik, Suche/Filter, Bewertungssystem, Mehrsprachigkeit, E-Mail, Barrierefreiheit Benachrichtigungen, Sicherheit (Passwortanforderungen)
**Nicht-funktionale Anforderungen**: Performance, Skalierbarkeit, Verfügbarkeit, Sicherheitsanforderungen, Benutzerfreundlichkeit, Wartbarkeit

**Code Struktur** (Vorschlag)
/src
  /application  -> Services, Use Cases
  /domain       -> Entities, Value Objects, Aggregates
  /infrastructure -> Repositories, externe Schnittstellen
  /web          -> Controller, API
