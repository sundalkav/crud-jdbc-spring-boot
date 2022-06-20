INSERT INTO flight (departure_city,departure_date,arrival_city,arrival_date,status)
  VALUES ('САМАРА', '2022-04-01 15:00:00', 'МОСКВА', '2022-04-01 16:00:00','АКТИВНЫЙ');
INSERT INTO flight (departure_city,departure_date,arrival_city,arrival_date,status)
VALUES ('САМАРА', '2022-04-01 15:00:00', 'САНКТ-ПЕТЕРБУРГ', '2022-04-01 17:00:00','АКТИВНЫЙ');
COMMIT;
INSERT INTO price (status_class,cost,flight_id) (SELECT'БИЗНЕС КЛАСС', 10000, id FROM flight WHERE arrival_city='МОСКВА' LIMIT 1);
INSERT INTO price (status_class,cost,flight_id) (SELECT 'ЭКОНОМ КЛАСС', 5000, id FROM flight WHERE arrival_city='МОСКВА' LIMIT 1);

INSERT INTO price (status_class,cost,flight_id) (SELECT 'БИЗНЕС КЛАСС', 15000, id FROM flight WHERE arrival_city='САНКТ-ПЕТЕРБУРГ' LIMIT 1);
INSERT INTO price (status_class,cost,flight_id) (SELECT 'ЭКОНОМ КЛАСС', 10000, id FROM flight WHERE arrival_city='САНКТ-ПЕТЕРБУРГ' LIMIT 1);
COMMIT;

INSERT INTO ticket (surname,name,patronymic,seat,flight_id,price_id)
    (SELECT 'ИВАНОВ', 'ИВАН', 'ИВАНОВИЧ', floor(random() * 50 + 1), flight_id, id FROM price ORDER BY random() LIMIT 1);

INSERT INTO ticket (surname,name,patronymic,seat,flight_id,price_id)
    (SELECT 'ПЕТРОВ', 'ПЕТР', 'ПЕТРОВИЧ', floor(random() * 50 + 1), flight_id, id FROM price ORDER BY random() LIMIT 1);

INSERT INTO ticket (surname,name,patronymic,seat,flight_id,price_id)
    (SELECT 'СИДОРОВ', 'СИДР', 'СИДОРОВИЧ', floor(random() * 50 + 1), flight_id, id FROM price ORDER BY random() LIMIT 1);

INSERT INTO ticket (surname,name,patronymic,seat,flight_id,price_id)
    (SELECT 'ПОПОВ', 'ИВАН', 'ИВАНОВИЧ', floor(random() * 50 + 1), flight_id, id FROM price ORDER BY random() LIMIT 1);

INSERT INTO ticket (surname,name,patronymic,seat,flight_id,price_id)
    (SELECT 'КАРЛОВ', 'ПЕТР', 'ПЕТРОВИЧ', floor(random() * 50 + 1), flight_id, id FROM price ORDER BY random() LIMIT 1);

INSERT INTO ticket (surname,name,patronymic,seat,flight_id,price_id)
    (SELECT 'КОЗЛОВ', 'СИДР', 'СИДОРОВИЧ', floor(random() * 50 + 1), flight_id, id FROM price ORDER BY random() LIMIT 1);

INSERT INTO ticket (surname,name,patronymic,seat,flight_id,price_id)
    (SELECT 'КУЗНЕЦОВ', 'ИВАН', 'ИВАНОВИЧ', floor(random() * 50 + 1), flight_id, id FROM price ORDER BY random() LIMIT 1);

INSERT INTO ticket (surname,name,patronymic,seat,flight_id,price_id)
    (SELECT 'СОКОЛОВ', 'ПЕТР', 'ПЕТРОВИЧ', floor(random() * 50 + 1), flight_id, id FROM price ORDER BY random() LIMIT 1);

INSERT INTO ticket (surname,name,patronymic,seat,flight_id,price_id)
    (SELECT 'ЛЕБЕДЕВ', 'ИВАН', 'ИВАНОВИЧ', floor(random() * 50 + 1), flight_id, id FROM price ORDER BY random() LIMIT 1);
COMMIT;
