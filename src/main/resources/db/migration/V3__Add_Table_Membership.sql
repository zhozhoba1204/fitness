CREATE TABLE IF NOT EXISTS membership
(
    number   serial      NOT NULL PRIMARY KEY,
    end_date DATE        NOT NULL,
    section  VARCHAR(50) NOT NULL,
    user_id  INTEGER     NOT NULL
);

alter table membership add constraint membership_user_fk
    foreign key (user_id) references usr;