--liquibase formatted sql

--changeset Vytautas:3

ALTER TABLE RECORDS_LIQUI
ADD TEST CHARACTER VARYING(255) NULL;