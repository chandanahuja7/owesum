CREATE DATABASE owesum
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
GRANT TEMPORARY, CONNECT ON DATABASE owesum TO PUBLIC;



-- Table: public.payment

-- DROP TABLE public.payment;

CREATE TABLE public.payment
(
    event_id bigint NOT NULL,
    purpose_tp_cd integer NOT NULL,
    description character varying(80) COLLATE pg_catalog."default",
    paidby_party_id bigint,
    payment_dt timestamp without time zone,
    payment_amount integer,
    payment_id character varying(35) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT payment_pkey PRIMARY KEY (payment_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.payment
    OWNER to postgres;
	
	
	
	
	
	
	
	
	-- Table: public.paymentbeneficiary

-- DROP TABLE public.paymentbeneficiary;

CREATE TABLE public.paymentbeneficiary
(
    beneficiary_party_id bigint NOT NULL,
    benefitted_amount integer NOT NULL,
    percent_reference numeric(4,2) NOT NULL,
    payment_id character varying(35) COLLATE pg_catalog."default" NOT NULL,
    paymentbeneficiary_id character varying(35) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT paymentbeneficiary_pkey PRIMARY KEY (paymentbeneficiary_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.paymentbeneficiary
    OWNER to postgres;