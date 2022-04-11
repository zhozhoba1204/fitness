ALTER TABLE usr
    ADD passport INTEGER;

UPDATE usr
SET passport = 11111 WHERE id=1;
UPDATE usr
SET passport = 22222 WHERE id=2;
UPDATE usr
SET passport = 33333 WHERE id=3;
UPDATE usr
SET passport = 44444 WHERE id=4;
UPDATE usr
SET passport = 55555 WHERE id=5;
UPDATE usr
SET passport = 66666 WHERE id=6;