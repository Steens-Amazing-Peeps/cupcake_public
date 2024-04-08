-- This script was generated by the ERD tool in pgAdmin 4.
-- Please log an issue at https://github.com/pgadmin-org/pgadmin4/issues/new/choose if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE IF NOT EXISTS public.top
(
    top_id serial NOT NULL,
    taste character varying(50) NOT NULL,
    price_oere integer NOT NULL,
    obsolete boolean NOT NULL DEFAULT FALSE,
    PRIMARY KEY (top_id)
);

CREATE TABLE IF NOT EXISTS public.bottom
(
    bottom_id serial NOT NULL,
    taste character varying(50) NOT NULL,
    price_oere integer NOT NULL,
    obsolete boolean NOT NULL DEFAULT FALSE,
    PRIMARY KEY (bottom_id)
);

CREATE TABLE IF NOT EXISTS public.order_line
(
    order_line_id serial NOT NULL,
    order_id integer NOT NULL,
    top_id integer NOT NULL,
    bottom_id integer NOT NULL,
    amount integer NOT NULL,
    PRIMARY KEY (order_line_id)
);

CREATE TABLE IF NOT EXISTS public."order"
(
    order_id serial NOT NULL,
    user_id integer NOT NULL,
    date_ordered date NOT NULL,
    date_ready date,
    status_id integer NOT NULL DEFAULT 1,
    PRIMARY KEY (order_id)
);

CREATE TABLE IF NOT EXISTS public."user"
(
    user_id serial NOT NULL,
    email character varying(256) NOT NULL,
    password character varying(100) NOT NULL,
    role_id integer NOT NULL DEFAULT 1,
    PRIMARY KEY (user_id),
    CONSTRAINT user_email_unq UNIQUE (email)
        INCLUDE(email)
);

CREATE TABLE IF NOT EXISTS public.role
(
    role_id serial NOT NULL,
    role character varying(50) NOT NULL,
    PRIMARY KEY (role_id),
    CONSTRAINT role_role_unq UNIQUE (role)
        INCLUDE(role)
);

CREATE TABLE IF NOT EXISTS public.status
(
    status_id serial NOT NULL,
    status character varying(50) NOT NULL,
    PRIMARY KEY (status_id),
    CONSTRAINT status_status_unq UNIQUE (status)
        INCLUDE(status)
);

CREATE TABLE IF NOT EXISTS public.wallet
(
    wallet_id serial NOT NULL,
    user_id integer NOT NULL,
    balance_oere integer NOT NULL DEFAULT 50000,
    PRIMARY KEY (wallet_id)
);

ALTER TABLE IF EXISTS public.order_line
    ADD FOREIGN KEY (bottom_id)
        REFERENCES public.bottom (bottom_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.order_line
    ADD FOREIGN KEY (top_id)
        REFERENCES public.top (top_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.order_line
    ADD FOREIGN KEY (order_id)
        REFERENCES public."order" (order_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public."order"
    ADD FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public."order"
    ADD FOREIGN KEY (status_id)
        REFERENCES public.status (status_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public."user"
    ADD FOREIGN KEY (role_id)
        REFERENCES public.role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.wallet
    ADD FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

CREATE VIEW order_detailed AS
SELECT
    public.order.order_id,
    public.order.user_id,
    public.user.email,
    public.user.role_id,
    public.role.role,
    public.order.date_ordered,
    public.order.date_ready,
    public.order.status_id,
    public.status.status,
    public.order_line.order_line_id,
    public.order_line.top_id,
    public.top.taste AS top_taste,
    public.top.price_oere AS top_price_oere,
    public.top.obsolete AS top_obsolete,
    public.order_line.bottom_id,
    public.bottom.taste AS bottom_taste,
    public.bottom.price_oere AS bottom_price_oere,
    public.bottom.obsolete AS bottom_obsolete,
    public.order_line.amount

FROM
    public.order
        LEFT JOIN public.user ON public.user.user_id = public.order.user_id
        LEFT JOIN public.role ON public.role.role_id = public.user.role_id
        LEFT JOIN public.status ON public.status.status_id = public.order.status_id
        LEFT JOIN public.order_line ON public.order_line.order_id = public.order.order_id
        LEFT JOIN public.top ON public.top.top_id = public.order_line.top_id
        LEFT JOIN public.bottom ON public.bottom.bottom_id = public.order_line.bottom_id;


INSERT INTO public.status ( status )
VALUES
    ('pending'),
    ('denied'),
    ('approved'),
    ('awaiting pickup'),
    ('cancelled'),
    ('done');

INSERT INTO public.role ( role )
VALUES
    ('user'),
    ('admin');

END;