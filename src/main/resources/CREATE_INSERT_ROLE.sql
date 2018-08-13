-- Table: public.role

-- DROP TABLE public.role;

-- CREATE

CREATE TABLE public.role
(
    name character varying COLLATE pg_catalog."default" NOT NULL,
    id integer NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
    CONSTRAINT roles_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.role
    OWNER to postgres;

-- INSERT

INSERT INTO public.role(name) VALUES ('ADMIN');
INSERT INTO public.role(name) VALUES ('USER');