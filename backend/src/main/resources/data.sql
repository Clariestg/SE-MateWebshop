-- 🔧 1. Constraints deaktivieren
SET FOREIGN_KEY_CHECKS = 0;

-- 📥 2. Address einfügen
INSERT INTO address (city, number, street, zip_code) VALUES
                                                         ('Berlin', '12A', 'Alexanderplatz', 10178),
                                                         ('München', '22B', 'Marienplatz', 80331),
                                                         ('Hamburg', '3C', 'Reeperbahn', 20359);

-- 📥 3. Customer einfügen (ohne cart_id)
INSERT INTO customer (customer_name, email, firstname, lastname, password, phone_number, role, address_id)
VALUES
    ('Max Mustermann', 'max@example.com', 'Max', 'Mustermann', 'plaintext123', '4912345678901', 'customer', 1),
    ('Erika Musterfrau', 'erika@example.com', 'Erika', 'Musterfrau', 'plaintext123', '4912345678902', 'customer', 2),
    ('Tom Testuser', 'tom@example.com', 'Tom', 'Testuser', 'plaintext123', '4912345678903', 'customer', 3);

-- 📥 4. Cart einfügen
INSERT INTO cart (customer_id) VALUES (1), (2), (3);

-- 📥 5. Customer updaten
UPDATE customer SET cart_id = 1 WHERE customer_id = 1;
UPDATE customer SET cart_id = 2 WHERE customer_id = 2;
UPDATE customer SET cart_id = 3 WHERE customer_id = 3;

-- ✅ 6. Constraints wieder aktivieren
SET FOREIGN_KEY_CHECKS = 1;


-- 5. Admin
INSERT INTO admin (email, name, password, role) VALUES
    ('admin@example.com', 'Admin User', 'adminpass123', 'admin');

-- 6. Produkte
INSERT INTO product (
    product_name,
    product_description,
    product_price,
    product_quantity,
    product_status,
    product_category,
    product_image_url
) VALUES
-- Yerba-Produkte
('Yerba Mate Original', 'Klassische Yerba aus Argentinien.', 9.90, 50, 'AVAILABLE', 'YERBA', '/images/yerba-mate-original.png'),
('Yerba Mate Minze', 'Mit erfrischender Minznote.', 11.90, 50, 'AVAILABLE', 'YERBA', '/images/yerba-mate-minze.png'),
('Yerba Mate Zitrone', 'Fruchtiger Geschmack mit Zitronenextrakt.', 12.50, 50, 'AVAILABLE', 'YERBA', '/images/yerba-mate-zitrone.png'),
('Yerba Mate Bio', 'Zertifizierte Bio-Yerba, nachhaltig angebaut.', 13.90, 50, 'AVAILABLE', 'YERBA', '/images/yerba-mate-bio.png'),

-- Zubehör
('Keramik-Matebecher', 'Modern & pflegeleicht in verschiedenen Farben.', 14.50, 30, 'AVAILABLE', 'CUP', '/images/keramik-matebecher.png'),
('Traditionelle Kalebasse', 'Klassische Form aus natürlichem Material.', 17.90, 30, 'AVAILABLE', 'CUP', '/images/traditionelle-kalebasse.png'),
('Metall-Strohhalm (Bombilla)', 'Edelstahl, spülmaschinenfest, mit Filterkopf.', 8.90, 100, 'AVAILABLE', 'STRAW', '/images/metall-strohhalm.png'),
('Bombilla gebogen', 'Gebogene Variante mit integriertem Sieb.', 9.50, 100, 'AVAILABLE', 'STRAW', '/images/bombilla-gebogen.png');
