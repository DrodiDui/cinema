CREATE TABLE IF NOT EXISTS ORGANIZATION
(
    ORG_ID          BIGINT                NOT NULL,
    ORG_NAME        CHARACTER VARYING(50) NOT NULL,
    FULL_ORG_NAME   CHARACTER VARYING(50) NOT NULL,
    ITN             BIGINT                NOT NULL,
    ADDRESS         CHARACTER VARYING(50) NOT NULL,
    ILLEGAL_ADDRESS CHARACTER VARYING(50) NOT NULL,
    CREATE_DATE     TIMESTAMP             NOT NULL,
    CREATE_AT       CHARACTER VARYING(50) NOT NULL,
    CREATE_AT_ROLE  CHARACTER VARYING(50) NOT NULL,
    UPDATE_DATE     TIMESTAMP             NULL,
    UPDATE_AT       CHARACTER VARYING(50) NULL,
    UPDATE_AT_ROLE  CHARACTER VARYING(50) NULL,
    CLOSE_DATE      TIMESTAMP             NULL,
    STATUS_ID       BIGINT                NOT NULL,
    PHONE_NUMBER    CHARACTER VARYING(20) NOT NULL
)
;

ALTER TABLE ORGANIZATION
    ADD CONSTRAINT ORG_ID_PK PRIMARY KEY (ORG_ID)
;


CREATE TABLE CINEMA
(
    CINEMA_ID      BIGINT                 NOT NULL,
    CINEMA_NAME    CHARACTER VARYING(50)  NOT NULL,
    COUNTRY        CHARACTER VARYING(50)  NOT NULL,
    CITY           CHARACTER VARYING(50)  NOT NULL,
    ADDRESS        CHARACTER VARYING(50)  NOT NULL,
    DESCRIPTION    CHARACTER VARYING(200) NOT NULL,
    CREATE_DATE    TIMESTAMP              NOT NULL,
    CREATE_BY      CHARACTER VARYING(50)  NOT NULL,
    CREATE_BY_ROLE CHARACTER VARYING(50)  NOT NULL,
    UPDATE_DATE    TIMESTAMP              NULL,
    UPDATE_BY      CHARACTER VARYING(50)  NULL,
    UPDATE_BY_ROLE CHARACTER VARYING(50)  NULL,
    CLOSE_DATE     TIMESTAMP              NULL,
    STATUS_ID      BIGINT                 NOT NULL,
    ORG_ID         BIGINT                 NOT NULL
)
;

ALTER TABLE CINEMA
    ADD CONSTRAINT CINEMA_PK PRIMARY KEY (CINEMA_ID)
;

ALTER TABLE CINEMA
    ADD CONSTRAINT CINEMA_ORG FOREIGN KEY (ORG_ID) REFERENCES ORGANIZATION (ORG_ID)
;