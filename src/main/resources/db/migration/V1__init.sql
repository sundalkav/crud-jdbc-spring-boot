DROP SEQUENCE IF EXISTS flight_id_seq, price_id_seq, ticket_id_seq CASCADE;
DROP TABLE IF EXISTS flight, price, ticket CASCADE;

CREATE SEQUENCE IF NOT EXISTS flight_id_seq;
CREATE SEQUENCE IF NOT EXISTS price_id_seq;
CREATE SEQUENCE IF NOT EXISTS ticket_id_seq;

create table IF NOT EXISTS flight
            (
            id integer default nextval('flight_id_seq') not null
            primary key,
            departure_city varchar,
            departure_date timestamp,
            arrival_city varchar,
            arrival_date timestamp,
            status varchar
            );

create table IF NOT EXISTS price
            (
            id integer default nextval('price_id_seq') not null
            primary key,
            status_class varchar,
            cost integer,
            flight_id integer,
            FOREIGN KEY (flight_id) REFERENCES flight (id) ON DELETE CASCADE
            );

create table IF NOT EXISTS ticket
            (
            id integer default nextval('ticket_id_seq') not null
            primary key,
            surname varchar,
            name varchar,
            patronymic varchar,
            seat varchar,
            flight_id integer,
            price_id integer,
            FOREIGN KEY (flight_id) REFERENCES flight (id) ON DELETE CASCADE,
            FOREIGN KEY (price_id) REFERENCES price (id) ON DELETE CASCADE
            );
