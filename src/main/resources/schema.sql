
CREATE EXTENSION unaccent;

CREATE TABLE pauta
(
  id numeric(19) NOT NULL,
  des_pauta character varying(50) NOT NULL,
  dt_votacao_fim timestamp without time zone,
  CONSTRAINT pauta_pk PRIMARY KEY (id)
);
ALTER TABLE pauta OWNER TO postgres;

CREATE SEQUENCE pauta_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807 --MAX do BigInteger
  START 1
  CACHE 1;
ALTER TABLE pauta_sequence OWNER TO postgres;

CREATE TABLE voto
(
  id numeric(19) NOT NULL,
  id_pauta numeric(19) NOT NULL,
  num_cpf numeric(11) NOT NULL,
  ind_voto_sim numeric(1) NOT NULL,
  CONSTRAINT voto_pk PRIMARY KEY (id),
  CONSTRAINT voto_pauta_fk FOREIGN KEY (id_pauta)
      REFERENCES pauta (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE voto OWNER TO postgres;

CREATE SEQUENCE voto_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807 --MAX do BigInteger
  START 1
  CACHE 1;
ALTER TABLE voto_sequence OWNER TO postgres;

GRANT ALL ON DATABASE DB_BLABLA
