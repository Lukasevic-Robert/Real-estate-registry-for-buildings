INSERT INTO owners (created_at, updated_at, email, first_name, last_name) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 'a.bilotas@randatmail.com', 'Andrius', 'Bilotas');
INSERT INTO owners (created_at, updated_at, email, first_name, last_name) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 'l.lenkutyte@randatmail.com', 'Laura', 'Lenkutyte');
INSERT INTO owners (created_at, updated_at, email, first_name, last_name) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 'l.giniotis@randatmail.com', 'Lukas', 'Giniotis');
INSERT INTO owners (created_at, updated_at, email, first_name, last_name) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 'i.vaitekunas@randatmail.com', 'Ignas', 'Vaitekunas');

INSERT INTO addresses (created_at, updated_at, city, street, number) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 'Uglebury', 'Ap #342-811 Imperdiet, St.', '22');
INSERT INTO addresses (created_at, updated_at, city, street, number) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 'Isrosall', '429-4102 Gravida Avenue', '15');
INSERT INTO addresses (created_at, updated_at, city, street, number) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 'Fecledo', '413-1490 Commodo Rd.', '67A');
INSERT INTO addresses (created_at, updated_at, city, street, number) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 'Feanridge', 'P.O. Box 636, 7368 Euismod Av.', '1');
INSERT INTO addresses (created_at, updated_at, city, street, number) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 'Agrille', 'Ap #147-9368 Quisque Av.', '12');


INSERT INTO buildings (created_at, updated_at, market_value, type, size, address_id, owner_id) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 10000000, 'BUSINESS', 1000, 1, 1);
INSERT INTO buildings (created_at, updated_at, market_value, type, size, address_id, owner_id) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 59000000, 'INDUSTRIAL', 2500, 2, 2);
INSERT INTO buildings (created_at, updated_at, market_value, type, size, address_id, owner_id) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 150000, 'BUSINESS', 500, 3, 3);
INSERT INTO buildings (created_at, updated_at, market_value, type, size, address_id, owner_id) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 80000, 'HOUSE', 1234, 4, 4);
INSERT INTO buildings (created_at, updated_at, market_value, type, size, address_id, owner_id) values (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 250000, 'BUSINESS', 999, 5, 4);