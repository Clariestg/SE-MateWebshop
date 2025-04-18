import yerbaMateOriginal from '../assets/images/yerba-mate-original.png';
import yerbaMateMinze from '../assets/images/yerba-mate-minze.png';
import yerbaMateZitrone from '../assets/images/yerba-mate-zitrone.png';
import yerbaMateBio from '../assets/images/yerba-mate-bio.png';
import KeramikBecher from '../assets/images/keramik-matebecher.png';
import TraditionelleKalebasse from '../assets/images/traditionelle-kalebasse.png';
import MetallStrohhalm from '../assets/images/metall-strohhalm.png';
import BombillaGebogen from '../assets/images/bombilla-gebogen.png';

const produkte = [
  {
    id: 1,
    name: 'Yerba Mate Original',
    kategorie: 'yerba-mate',
    preis: '9,90 €',
    beschreibung: 'Klassische Yerba aus Argentinien.',
    bild: yerbaMateOriginal,
    details: [
      'Herkunft: Argentinien',
      'Zubereitung: 1-2 EL mit 200 ml Wasser bei 70-80°C',
      'Zutaten: 100 % Yerba Mate (Blätter und Stiele)',
      'Geschmack: Kräftig, leicht rauchig',
    ]
  },
  {
    id: 2,
    name: 'Yerba Mate Minze',
    kategorie: 'yerba-mate',
    preis: '11,90 €',
    beschreibung: 'Mit erfrischender Minznote.',
    bild: yerbaMateMinze,
    details: [
      'Herkunft: Brasilien',
      'Zubereitung: 1-2 EL mit 200 ml Wasser bei 70°C',
      'Zutaten: Yerba Mate, natürliche Minzaromen',
      'Besonderheit: Erfrischender Geschmack, ideal für den Sommer',
    ]
  },
  {
    id: 3,
    name: 'Yerba Mate Zitrone',
    kategorie: 'yerba-mate',
    preis: '12,50 €',
    beschreibung: 'Fruchtiger Geschmack mit Zitronenextrakt.',
    bild: yerbaMateZitrone,
    details: [
      'Herkunft: Uruguay',
      'Zubereitung: 1-2 EL mit 250 ml Wasser bei 75°C',
      'Zutaten: Yerba Mate, Zitronenschale, Zitronenaroma',
      'Geschmack: Fruchtig, leicht säuerlich',
    ]
  },
  {
    id: 4,
    name: 'Yerba Mate Bio',
    kategorie: 'yerba-mate',
    preis: '13,90 €',
    beschreibung: 'Zertifizierte Bio-Yerba, nachhaltig angebaut.',
    bild: yerbaMateBio,
    details: [
      'Herkunft: Paraguay',
      'Zubereitung: 2 EL mit 200 ml Wasser bei 70-75°C',
      'Zutaten: 100 % Bio-Yerba Mate',
      'Zertifizierung: DE-ÖKO-001',
    ]
  },
  {
    id: 5,
    name: 'Keramik-Matebecher',
    kategorie: 'zubehoer',
    preis: '14,50 €',
    beschreibung: 'Modern & pflegeleicht in verschiedenen Farben.',
    bild: KeramikBecher,
    details: [
      'Material: Hochwertige Keramik',
      'Fassungsvermögen: 250 ml',
      'Spülmaschinengeeignet: Ja',
      'Farbe: Diverse Varianten verfügbar',
    ]
  },
  {
    id: 6,
    name: 'Traditionelle Kalebasse',
    kategorie: 'zubehoer',
    preis: '17,90 €',
    beschreibung: 'Klassische Form aus natürlichem Material.',
    bild: TraditionelleKalebasse,
    details: [
      'Material: Natur-Kürbis (Kalebasse)',
      'Fassungsvermögen: ca. 200 ml',
      'Pflegehinweis: Vor erster Nutzung auskochen & trocknen',
      'Hinweis: Natürliche Form und Größe können leicht variieren',
    ]
  },
  {
    id: 7,
    name: 'Metall-Strohalm (Bombilla)',
    kategorie: 'zubehoer',
    preis: '8,90 €',
    beschreibung: 'Edelstahl, spülmaschinenfest, mit Filterkopf.',
    bild: MetallStrohhalm,
    details: [
      'Material: Edelstahl (rostfrei)',
      'Länge: 17 cm',
      'Filtertyp: Flacher Schraubfilter',
      'Reinigung: Spülmaschinengeeignet',
    ]
  },
  {
    id: 8,
    name: 'Bombilla gebogen',
    kategorie: 'zubehoer',
    preis: '9,50 €',
    beschreibung: 'Gebogene Variante mit integriertem Sieb.',
    bild: BombillaGebogen,
    details: [
      'Material: Edelstahl, gebogenes Mundstück',
      'Länge: 16 cm',
      'Filtertyp: Fein gelochtes Rundsieb',
      'Reinigung: Handwäsche empfohlen',
    ]
  }
];

export default produkte;
