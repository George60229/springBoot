CREATE SEQUENCE serial START 101;
CREATE TABLE IF NOT EXISTS public.certificates
(
    certificate_id integer NOT NULL DEFAULT nextval('serial'::regclass),
    create_date timestamp(6) without time zone,
    description character varying(255) COLLATE pg_catalog."default",
    duration integer,
    last_update_date timestamp(6) without time zone,
    name character varying(255) COLLATE pg_catalog."default",
    price numeric,
    CONSTRAINT certificates_pkey PRIMARY KEY (certificate_id)
    );
CREATE SEQUENCE tag_seq START 1;
CREATE TABLE IF NOT EXISTS public.tages
(
    tag_id integer NOT NULL DEFAULT nextval('tag_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT tages_pkey PRIMARY KEY (tag_id)
    );
CREATE TABLE IF NOT EXISTS public.certificates_to_tages
(
    certificate_id integer,
    tag_id integer,
    CONSTRAINT certificates_to_tages_certificate_id_fkey FOREIGN KEY (certificate_id)
    REFERENCES public.certificates (certificate_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT certificates_to_tages_tag_id_fkey FOREIGN KEY (tag_id)
    REFERENCES public.tages (tag_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )
