--liquibase formatted sql

--changeset Vytautas:2

INSERT INTO RECORDS_LIQUI
SELECT * FROM RECORDS;