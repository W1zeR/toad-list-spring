--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

-- Started on 2023-04-20 20:06:58

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3332 (class 1262 OID 25879)
-- Name: toadlist; Type: DATABASE; Schema: -; Owner: me
--

-- Role: me
-- DROP ROLE IF EXISTS me;

CREATE ROLE me WITH
  LOGIN
  SUPERUSER
  NOINHERIT
  CREATEDB
  CREATEROLE
  NOREPLICATION
  ENCRYPTED PASSWORD 'SCRAM-SHA-256$4096:IXp/s2RSmvIYqorjh0+yiA==$2M1ZQwHmmtmM573vJQjV/hCGF2pjMX0IDD2ajZNn/iY=:I0I17ZYYifOcKjvDcqHi6xssxlFgCJ6fv12Y1ofiKW8=';

COMMENT ON ROLE me IS 'Пароль "123"';

CREATE DATABASE toadlist WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';


ALTER DATABASE toadlist OWNER TO me;

\connect toadlist

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 25881)
-- Name: profile; Type: TABLE; Schema: public; Owner: me
--

CREATE TABLE public.profile (
    id bigint NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(50) NOT NULL
);


ALTER TABLE public.profile OWNER TO me;

--
-- TOC entry 214 (class 1259 OID 25880)
-- Name: profile_id_seq; Type: SEQUENCE; Schema: public; Owner: me
--

CREATE SEQUENCE public.profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profile_id_seq OWNER TO me;

--
-- TOC entry 3333 (class 0 OID 0)
-- Dependencies: 214
-- Name: profile_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: me
--

ALTER SEQUENCE public.profile_id_seq OWNED BY public.profile.id;


--
-- TOC entry 217 (class 1259 OID 25888)
-- Name: toad; Type: TABLE; Schema: public; Owner: me
--

CREATE TABLE public.toad (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    type character varying(50),
    weight bigint,
    length numeric(5,2),
    birthday date,
    description character varying(150),
    id_profile bigint NOT NULL
);


ALTER TABLE public.toad OWNER TO me;

--
-- TOC entry 3334 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE toad; Type: COMMENT; Schema: public; Owner: me
--

COMMENT ON TABLE public.toad IS 'Вес в граммах, длина в сантиметрах';


--
-- TOC entry 216 (class 1259 OID 25887)
-- Name: toad_id_seq; Type: SEQUENCE; Schema: public; Owner: me
--

CREATE SEQUENCE public.toad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.toad_id_seq OWNER TO me;

--
-- TOC entry 3335 (class 0 OID 0)
-- Dependencies: 216
-- Name: toad_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: me
--

ALTER SEQUENCE public.toad_id_seq OWNED BY public.toad.id;


--
-- TOC entry 3178 (class 2604 OID 25884)
-- Name: profile id; Type: DEFAULT; Schema: public; Owner: me
--

ALTER TABLE ONLY public.profile ALTER COLUMN id SET DEFAULT nextval('public.profile_id_seq'::regclass);


--
-- TOC entry 3179 (class 2604 OID 25891)
-- Name: toad id; Type: DEFAULT; Schema: public; Owner: me
--

ALTER TABLE ONLY public.toad ALTER COLUMN id SET DEFAULT nextval('public.toad_id_seq'::regclass);


--
-- TOC entry 3181 (class 2606 OID 25886)
-- Name: profile profile_pkey; Type: CONSTRAINT; Schema: public; Owner: me
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);


--
-- TOC entry 3183 (class 2606 OID 25893)
-- Name: toad toad_pkey; Type: CONSTRAINT; Schema: public; Owner: me
--

ALTER TABLE ONLY public.toad
    ADD CONSTRAINT toad_pkey PRIMARY KEY (id);


--
-- TOC entry 3184 (class 2606 OID 25894)
-- Name: toad fk_toad_to_profile; Type: FK CONSTRAINT; Schema: public; Owner: me
--

ALTER TABLE ONLY public.toad
    ADD CONSTRAINT fk_toad_to_profile FOREIGN KEY (id_profile) REFERENCES public.profile(id) ON DELETE CASCADE ON UPDATE CASCADE;


-- Completed on 2023-04-20 20:07:00

--
-- PostgreSQL database dump complete
--
