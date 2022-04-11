CREATE TABLE IF NOT EXISTS usr
(
    id         serial PRIMARY KEY ,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    age      INTEGER NOT NULL,
    passport INTEGER NOT NULL
);
