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
