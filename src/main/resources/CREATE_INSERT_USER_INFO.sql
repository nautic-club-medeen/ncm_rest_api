-- Table: public.user_info

-- DROP TABLE public.user_info;

-- CREATE

CREATE TABLE public.user_info
(
    id integer NOT NULL DEFAULT nextval('user_info_id_seq'::regclass),
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    birthday_date date NOT NULL,
    phone character varying(10) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role_id integer NOT NULL DEFAULT nextval('user_info_role_id_seq'::regclass),
    password chkpass NOT NULL,
    CONSTRAINT user_info_pkey PRIMARY KEY (id),
    CONSTRAINT role_id_fk FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.user_info
    OWNER to postgres;

-- Index: fki_role_id_fk

-- DROP INDEX public.fki_role_id_fk;

CREATE INDEX fki_role_id_fk
    ON public.user_info USING btree
    (role_id)
    TABLESPACE pg_default;

-- INSERT

INSERT INTO public.user_info(
	first_name, last_name, birthday_date, phone, email, role_id, password)
	VALUES ('toto', 'tata', '14/02/1995', '0600000000', 'toto@tata.fr', 1, 'toto');