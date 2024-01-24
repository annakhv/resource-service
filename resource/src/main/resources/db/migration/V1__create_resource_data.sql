CREATE TABLE resource_data (
  resource_id BIGINT NOT NULL,
   data BYTEA NOT NULL,
   CONSTRAINT pk_resourcedata PRIMARY KEY (resource_id)
);

CREATE SEQUENCE  IF NOT EXISTS resource_data_seq START WITH 1 INCREMENT BY 50;
