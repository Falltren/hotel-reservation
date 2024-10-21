CREATE TABLE hotels (
    id bigserial primary key,
    name varchar(255),
    title varchar(255),
    city varchar(255),
    address varchar(255),
    distance int,
    ranking float,
    count int
);

CREATE TABLE rooms (
    id bigserial primary key,
    name varchar(255),
    description varchar(255),
    number int,
    price float,
    max_people_count int,
    hotel_id bigint references hotels(id)
);

CREATE TABLE unavailable_dates (
    id bigserial primary key,
    room_id bigint references rooms(id),
    reserved timestamp(6) without time zone
);

CREATE TABLE IF NOT EXISTS users (
    id bigserial primary key,
    name varchar(255),
    password varchar(255),
    email varchar(255),
    role varchar(255)
);
