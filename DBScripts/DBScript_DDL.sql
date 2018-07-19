
CREATE DATABASE owesum
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

GRANT TEMPORARY, CONNECT ON DATABASE owesum TO PUBLIC;

GRANT ALL ON DATABASE owesum TO postgres;




CREATE TABLE public.payment
(
    purpose_tp_cd integer NOT NULL,
    description character varying(80) COLLATE pg_catalog."default",
    payment_dt timestamp without time zone,
    payment_amount integer,
    payment_id character varying(35) COLLATE pg_catalog."default" NOT NULL,
    event_id character varying(35) COLLATE pg_catalog."default" NOT NULL,
    paidby_party_id character varying(35) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT payment_pkey PRIMARY KEY (payment_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.payment
    OWNER to postgres;

-- Index: IDX_PAYMENT_EVENT_ID

-- DROP INDEX public."IDX_PAYMENT_EVENT_ID";

CREATE INDEX "IDX_PAYMENT_EVENT_ID"
    ON public.payment USING btree
    (event_id COLLATE pg_catalog."default")
    TABLESPACE pg_default;

-- Index: IDX_PAYMENT_PARTY_ID

-- DROP INDEX public."IDX_PAYMENT_PARTY_ID";

CREATE INDEX "IDX_PAYMENT_PARTY_ID"
    ON public.payment USING btree
    (paidby_party_id COLLATE pg_catalog."default")
    TABLESPACE pg_default;




	CREATE TABLE public.paymentbeneficiary
(
    benefitted_amount numeric(8,2) NOT NULL,
    percent_reference numeric(8,2) NOT NULL,
    payment_id character varying(35) COLLATE pg_catalog."default" NOT NULL,
    paymentbeneficiary_id character varying(35) COLLATE pg_catalog."default" NOT NULL,
    beneficiary_party_id character varying(35) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT paymentbeneficiary_pkey PRIMARY KEY (paymentbeneficiary_id),
    CONSTRAINT paymentbeneficiary_payment_id_fkey FOREIGN KEY (payment_id)
        REFERENCES public.payment (payment_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.paymentbeneficiary
    OWNER to postgres;

-- Index: IDX_PAYMENTBENEFICIARY_PAYMENT_ID

-- DROP INDEX public."IDX_PAYMENTBENEFICIARY_PAYMENT_ID";

CREATE INDEX "IDX_PAYMENTBENEFICIARY_PAYMENT_ID"
    ON public.paymentbeneficiary USING btree
    (payment_id COLLATE pg_catalog."default")
    TABLESPACE pg_default;

