-- Insertar ciudades/estados mexicanos
INSERT INTO districts (name) VALUES 
('Ciudad de México'),
('Guadalajara'),
('Monterrey'),
('Puebla'),
('Tijuana'),
('Cancún'),
('Mérida'),
('Oaxaca'),
('Playa del Carmen'),
('Puerto Vallarta'),
('Querétaro'),
('Toluca'),
('León'),
('Chihuahua'),
('Acapulco'),
('Veracruz'),
('Morelia'),
('Aguascalientes'),
('Saltillo'),
('Cuernavaca');

-- Insertar restaurantes mexicanos típicos
INSERT INTO restaurants (name, address, phone_number, district_id, price_per_person, capacity) VALUES
('Taquería El Fogón', 'Av. Insurgentes 123', '55-1234567', 1, 180.0, 80),
('Cantina La Guadalupana', 'Calle Madero 456', '55-2345678', 1, 250.0, 120),
('Mariscos El Patrón', 'Av. Reforma 789', '33-3456789', 2, 320.0, 150),
('Tacos Don Chente', 'Calle Juárez 101', '33-4567890', 2, 150.0, 60),
('Asadero Regiomontano', 'Av. Constitución 202', '81-5678901', 3, 380.0, 200),
('Carnitas Michoacanas', 'Calle Morelos 303', '81-6789012', 3, 220.0, 90),
('Mole Poblano Tradicional', 'Av. 5 de Mayo 404', '22-7890123', 4, 280.0, 110),
('Chiles en Nogada', 'Calle 16 de Septiembre 505', '22-8901234', 4, 350.0, 85),
('Mariscos Baja California', 'Av. Revolución 606', '66-9012345', 5, 400.0, 140),
('Tacos de Pescado', 'Calle Primera 707', '66-0123456', 5, 180.0, 70),
('Ceviche Tropical', 'Av. Tulum 808', '99-0987654', 6, 320.0, 160),
('Cochinita Pibil Yucateca', 'Calle Hidalgo 909', '99-9876543', 6, 250.0, 100),
('Poc Chuc Meridano', 'Av. Paseo Montejo 1010', '99-8765432', 7, 290.0, 120),
('Sopa de Lima', 'Calle 60 Centro 1111', '99-7654321', 7, 200.0, 80),
('Mole Oaxaqueño', 'Av. Independencia 1212', '95-6543210', 8, 320.0, 90),
('Tlayudas Oaxaqueñas', 'Calle Alcalá 1313', '95-5432109', 8, 180.0, 70),
('Aguachile Sinaloense', 'Av. Constituyentes 1414', '98-4321098', 9, 280.0, 110),
('Tacos de Camarón', 'Calle 10 Norte 1515', '98-3210987', 9, 220.0, 85),
('Birria Jaliscience', 'Malecón 1616', '32-2109876', 10, 250.0, 130),
('Tortas Ahogadas', 'Av. Francisco Villa 1717', '32-1098765', 10, 160.0, 60),
('Barbacoa Queretana', 'Av. Corregidora 1818', '44-0987654', 11, 300.0, 100),
('Enchiladas Queretanas', 'Calle Allende 1919', '44-9876543', 11, 200.0, 75),
('Chorizo Toluca', 'Av. Lerdo 2020', '72-8765432', 12, 220.0, 95),
('Tacos de Longaniza', 'Calle Benito Juárez 2121', '72-7654321', 12, 180.0, 65),
('Cabrito Norteño', 'Av. Hidalgo 2222', '47-6543210', 13, 450.0, 180),
('Machaca con Huevo', 'Calle Victoria 2323', '47-5432109', 13, 200.0, 70),
('Burritos Chihuahuenses', 'Av. Universidad 2424', '61-4321098', 14, 180.0, 80),
('Carne Seca Norteña', 'Calle Libertad 2525', '61-3210987', 14, 250.0, 90),
('Pescado a la Veracruzana', 'Malecón 2626', '74-2109876', 15, 320.0, 140),
('Camarones al Coco', 'Av. Costera 2727', '74-1098765', 15, 380.0, 160);

