--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-04-23 14:09:09

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
-- TOC entry 214 (class 1259 OID 16399)
-- Name: call_cost; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.call_cost (
    id smallint NOT NULL,
    default_minute_cost numeric(3,1) NOT NULL,
    benefit_minute_cost numeric(3,1) NOT NULL
);


ALTER TABLE public.call_cost OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16439)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    id integer NOT NULL,
    phone_number character varying(11),
    balance numeric(7,1),
    benefit_minutes_left integer,
    tariff_id character varying(2)
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16419)
-- Name: tariff; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tariff (
    tariff_id character varying(2) NOT NULL,
    tariff_name character varying(20) NOT NULL,
    subscription_fee integer NOT NULL,
    benefit_minutes smallint NOT NULL,
    in_call_cost_id smallint NOT NULL,
    out_call_cost_id smallint NOT NULL
);


ALTER TABLE public.tariff OWNER TO postgres;

--
-- TOC entry 3333 (class 0 OID 16399)
-- Dependencies: 214
-- Data for Name: call_cost; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.call_cost (id, default_minute_cost, benefit_minute_cost) VALUES (1, 1.5, 0.0);
INSERT INTO public.call_cost (id, default_minute_cost, benefit_minute_cost) VALUES (2, 1.0, 0.0);
INSERT INTO public.call_cost (id, default_minute_cost, benefit_minute_cost) VALUES (3, 0.0, 0.0);
INSERT INTO public.call_cost (id, default_minute_cost, benefit_minute_cost) VALUES (4, 1.5, 0.5);


--
-- TOC entry 3335 (class 0 OID 16439)
-- Dependencies: 216
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (349, '78245176506', 9777.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (303, '79150914576', 9986.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (382, '78572214312', 9974.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (380, '73513302620', 9915.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (458, '77249451585', 9598.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (485, '70338259554', 9848.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (453, '70359002908', 9562.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (257, '73860640184', 9613.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (246, '70197987396', 8780.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (457, '73848361892', 9993.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (488, '70717446703', 9894.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (225, '75270866217', 8520.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (361, '77640692412', 9989.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (290, '79808645206', 9681.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (287, '79088463195', 9616.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (350, '72579104121', 9997.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (293, '71846084449', 9855.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (258, '72667101680', 9841.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (292, '70736109080', 9895.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (480, '76065609589', 9792.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (224, '74388964319', 9799.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (306, '77725095327', 9976.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (368, '72126245177', 9990.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (357, '78296348353', 9755.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (464, '73688583810', 8921.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (497, '74807375942', 9777.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (197, '78360436399', 9865.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (213, '74402306898', 9823.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (387, '71014068612', 9364.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (297, '73614326318', 9789.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (395, '70073153636', 1888.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (228, '77648423743', 4494.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (75, '79275123968', 7863.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (373, '78695569014', 2913.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (56, '79759828487', 8996.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (412, '79127658263', 6323.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (35, '79833019763', 9397.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (265, '75016152379', 2270.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (33, '79984592676', 8943.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (427, '76557424459', 4967.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (1, '79107356790', 8196.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (372, '75121914965', 965.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (18, '79957162308', 6239.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (466, '77106268143', 9835.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (490, '75834895559', 9894.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (484, '72939602352', 9914.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (478, '70226183790', 9907.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (24, '79777029097', 5557.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (49, '79173317681', 10260.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (12, '79639358389', 7456.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (61, '79775144603', 6863.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (184, '79812655022', 8369.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (451, '71035452694', 2655.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (86, '79689492568', 8332.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (264, '70550398976', 5371.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (46, '79673874281', 6765.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (144, '79796639052', 8153.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (165, '79552192853', 6462.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (492, '77991807627', 2365.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (241, '72819797530', 6394.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (161, '79817404678', 7197.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (84, '79129914832', 8473.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (82, '79175861910', 8602.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (491, '75603456382', 2067.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (450, '78838046794', 1649.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (421, '76679826390', 5499.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (399, '73266225837', 4880.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (430, '74538362062', 4484.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (240, '76635922525', 652.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (159, '79572612753', 6097.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (391, '78992283408', 6624.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (2, '79356269457', 8483.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (209, '77870367936', 9988.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (272, '79914219161', 9833.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (57, '79745524861', 7533.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (275, '78124931895', 9560.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (239, '74544464161', 5686.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (233, '70821944285', 9907.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (218, '75641097521', 9662.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (216, '73908852278', 9756.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (127, '79783451888', 8913.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (406, '73238016686', 9757.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (215, '78078613290', 1086.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (170, '79988539908', 8291.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (278, '73192224862', 2261.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (262, '79033548952', 5318.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (129, '79931820303', 7812.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (113, '79755890378', 7578.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (205, '72471261369', 7398.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (248, '71761130027', 3757.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (204, '74812674888', 4040.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (243, '75114707493', 5478.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (227, '77614211985', 1980.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (283, '70914340500', 3573.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (151, '79783941768', 9770.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (487, '74487529029', 4692.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (123, '79893427312', 6805.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (193, '76226576311', 6003.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (191, '74830100220', 2350.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (214, '79613847235', 6660.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (9, '79825388512', 9883.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (30, '79727650606', 9554.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (93, '79590365704', 8512.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (37, '79126435787', 8434.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (132, '79567989375', 9143.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (207, '74105742558', 2333.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (83, '79025222861', 7924.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (260, '73850832081', 2301.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (271, '75856917290', 37.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (437, '70388859398', 1673.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (503, '71231233213', 300.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (163, '79229104597', 7196.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (392, '79812777099', 9060.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (504, '71231233214', 9215.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (432, '79317982241', 972.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (404, '70733609087', 5083.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (268, '75843010913', 4231.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (270, '76377222521', 998.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (206, '73317080882', 2911.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (416, '79897456848', 7650.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (145, '79290232628', 8355.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (468, '72966852149', 3952.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (7, '79109862134', 6692.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (108, '79173552488', 9421.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (256, '70549369268', 4342.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (219, '71137382151', 7008.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (274, '76942768614', 8201.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (414, '76522919742', 9696.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (379, '71836010161', 9539.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (305, '79809948567', 3731.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (385, '72100339057', 9682.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (501, '71231233211', 9517.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (384, '74667687799', 9799.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (288, '78882378253', 6675.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (336, '71382608993', 237.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (389, '73186585272', 617.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (347, '79655171012', 5340.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (408, '72432228186', 4533.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (394, '76074956651', 4952.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (68, '79129735854', 7631.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (106, '79050299491', 8920.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (150, '79251601716', 6347.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (52, '79747296115', 9414.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (102, '79106701856', 6552.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (442, '72578962725', 5481.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (353, '79236140426', 3722.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (17, '79134725967', 7877.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (81, '79113719597', 8493.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (177, '79340597487', 8311.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (139, '79844751100', 8942.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (314, '78534124848', 7425.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (312, '76450259953', 1617.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (456, '74973772714', 2208.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (173, '79925577418', 8154.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (42, '79940930587', 7515.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (332, '72923680317', 4659.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (183, '79234837505', 8062.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (340, '74486485071', 6371.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (48, '79822252975', 9190.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (360, '77985855603', 7207.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (71, '79797978762', 7603.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (440, '79258707475', 1891.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (112, '79077237019', 8111.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (63, '79580301466', 7999.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (435, '75188389190', 4128.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (377, '73776496032', 8580.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (117, '79157395681', 5278.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (411, '72689298017', 4801.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (418, '76636067588', 1136.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (72, '79722943972', 6229.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (431, '77427275945', 565.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (345, '78367475818', 7818.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (291, '78114240014', 6851.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (4, '79214842407', 7564.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (120, '79784127661', 7445.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (153, '79174922007', 8565.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (55, '79961423233', 7598.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (141, '79532620700', 9066.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (196, '71034758629', 9148.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (229, '77533310350', 9958.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (463, '73757720175', 9812.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (410, '78750423457', 9894.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (436, '76284363982', 9669.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (396, '77052553220', 6840.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (434, '75433993922', 9873.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (198, '72861641583', 7244.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (236, '77370391773', 3747.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (118, '79613143076', 8956.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (355, '73911453533', 2378.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (176, '79774736655', 6780.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (358, '74493390871', 4716.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (337, '76077144761', 7048.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (318, '74226283028', 62.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (369, '70116414154', 1133.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (31, '79742038849', 7371.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (58, '79265539006', 7896.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (276, '79763956313', 3907.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (370, '74709692587', 2119.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (449, '72648328731', 1318.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (34, '79553672610', 7622.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (180, '79333867081', 8675.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (351, '78165999602', 2490.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (201, '72775101286', 3305.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (91, '79522211075', 5941.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (320, '70160363279', 5470.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (97, '79217069721', 9280.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (330, '72397387594', 5791.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (261, '73751017187', 5852.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (39, '79710629502', 8586.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (99, '79075446910', 8549.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (438, '73363786972', 3190.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (16, '79531194798', 8814.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (420, '72430905176', 848.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (359, '70217172104', 1138.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (447, '79459614711', 6010.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (182, '79042612961', 8997.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (13, '79060262049', 7495.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (415, '77868516486', 5753.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (401, '71227027891', 5600.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (502, '71231233212', 2502.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (405, '70809083164', 7774.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (124, '79794622349', 7027.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (47, '79245009002', 9148.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (157, '79921874763', 6802.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (375, '73688734590', 9050.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (454, '74052813338', 9731.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (423, '74879096217', 9842.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (445, '79695666571', 9770.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (381, '70183586646', 9671.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (498, '71067122819', 9758.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (8, '79519169008', 9005.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (402, '72444773300', 8130.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (19, '79996697121', 9052.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (126, '79784357950', 8934.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (103, '79675752189', 9048.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (88, '79503241685', 5997.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (105, '79668893694', 8438.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (168, '79547620695', 9207.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (467, '76912717667', 6982.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (54, '79996422375', 8789.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (156, '79887231929', 7858.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (152, '79818147517', 9075.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (374, '71080910575', 6699.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (76, '79090967362', 8081.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (62, '79696219232', 9474.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (446, '72883875930', 6006.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (166, '79724410307', 6210.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (80, '79671841425', 7357.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (433, '74828058199', 5464.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (69, '79891330512', 7115.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (92, '79830225608', 8050.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (469, '71336004040', 4872.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (85, '79690435714', 7119.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (98, '79164809260', 9251.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (482, '73678237835', 173.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (476, '79622029489', 6127.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (390, '77406243063', 4304.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (164, '79155992733', 9025.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (95, '79795192809', 9389.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (94, '79625072009', 6807.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (11, '79045508048', 5832.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (388, '79550359535', 8015.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (489, '75469807037', 4832.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (486, '74608787017', 8916.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (428, '73751604411', 3557.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (383, '78830063381', 975.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (475, '74259820960', 4264.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (77, '79880756249', 7649.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (59, '79024388512', 8839.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (107, '79885790789', 6176.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (477, '77724364819', 4893.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (178, '79766971724', 8295.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (461, '77285587101', 6815.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (158, '79197209870', 8747.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (471, '70115009352', 7681.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (425, '75055053709', 1191.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (199, '73014713615', 1053.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (232, '79695330026', 8429.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (155, '79326961948', 7853.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (443, '76414461887', 2167.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (407, '73315204370', 7342.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (128, '79069139454', 6350.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (254, '74409186396', 1544.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (245, '71462760369', 4609.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (202, '78952834128', 4636.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (281, '71777564605', 4906.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (15, '79116108246', 9100.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (74, '79763059695', 8351.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (279, '79110162718', 5941.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (422, '78780496281', 7915.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (136, '79711584995', 8164.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (230, '78860360636', 5820.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (66, '79189907266', 8956.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (220, '72362407739', 2964.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (238, '75785660558', 6400.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (149, '79510560379', 7242.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (231, '72287201594', 5349.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (135, '79186767633', 7776.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (277, '72630505454', 5814.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (73, '79359628287', 9560.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (496, '77180579773', 5984.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (192, '72286798662', 5439.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (280, '77603799771', 391.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (5, '79794364796', 9510.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (255, '78330974026', 4868.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (101, '79972861575', 8408.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (212, '78564253607', 2842.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (325, '77664977706', 4150.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (64, '79069903202', 7197.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (187, '79729906772', 8156.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (294, '77372053564', 2704.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (313, '73946850687', 5342.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (308, '77745325176', 557.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (362, '78567963186', 5186.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (363, '74656958053', 1179.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (179, '79255453629', 8409.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (386, '76289093976', 4193.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (324, '74857290072', 6754.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (27, '79886962541', 9581.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (319, '76884660317', 3661.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (51, '79914104015', 9559.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (331, '72357894672', 7876.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (315, '78077966275', 2942.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (462, '71556323737', 4740.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (14, '79118429710', 9562.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (495, '77712364049', 2304.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (341, '78008787561', 3309.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (32, '79589071277', 7616.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (307, '70164996117', 8250.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (429, '78637734049', 1259.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (133, '79324982543', 7055.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (175, '79916004568', 7947.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (321, '75369935833', 5280.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (323, '78493057247', 4508.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (50, '79206465587', 6680.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (348, '70834258818', 2272.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (104, '79621217217', 8231.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (148, '79583101217', 9416.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (295, '70575040563', 1850.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (70, '79390151289', 8869.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (448, '79886981376', 676.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (322, '70600016612', 3780.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (329, '79645673282', 6937.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (172, '79747692391', 8382.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (344, '72300583874', 1815.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (326, '70561465605', 4847.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (354, '73425040040', 8333.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (296, '70505792178', 6709.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (130, '79736444662', 8192.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (41, '79583781116', 6752.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (174, '79951421481', 7217.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (21, '79944636076', 8413.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (226, '74534775755', 2030.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (44, '79268254896', 7466.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (65, '79970194518', 8631.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (188, '79620752014', 9601.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (235, '71880931685', 4885.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (378, '74088622481', 3206.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (146, '79611913525', 5836.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (181, '79723490238', 8774.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (137, '79723827406', 6187.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (253, '71569625061', 1447.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (251, '73920380920', 1061.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (247, '74849260443', 5803.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (237, '79141288086', 8360.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (267, '73649373583', 5965.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (223, '78402881882', 7821.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (147, '79297248122', 7919.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (115, '79791746439', 7837.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (87, '79190934281', 7484.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (60, '79833178697', 10031.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (6, '79687145033', 8641.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (334, '79594705119', 5259.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (90, '79059860160', 9235.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (289, '72018128999', 463.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (333, '77859410248', 67.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (26, '79139504581', 6448.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (171, '79728357806', 7487.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (250, '75377323719', 1605.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (311, '75307576160', 5361.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (142, '79634438909', 7042.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (298, '72851282495', 4760.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (121, '79890907982', 8885.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (143, '79023172145', 9597.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (43, '79308203125', 8927.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (327, '74803497651', 6021.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (140, '79088014472', 7156.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (114, '79663048775', 7499.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (285, '73294311108', 7566.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (304, '71984529586', 535.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (400, '72056462498', 3729.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (263, '74978816333', 6231.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (20, '79752713750', 7868.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (273, '74872567164', 4356.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (111, '79911627395', 8758.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (116, '79999766783', 6797.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (3, '79695719166', 7594.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (217, '70096540593', 9149.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (286, '75719201272', 4049.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (481, '76922659068', 4780.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (125, '79318340115', 4827.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (300, '75468352829', 3945.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (211, '79280724212', 1174.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (36, '79786187731', 8194.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (473, '75251401826', 5816.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (439, '72764404100', 3707.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (208, '74497624180', 6529.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (242, '78121877239', 3396.0, 300, '06');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (444, '72405962042', 915.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (67, '79169616455', 9634.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (493, '75518588822', 1109.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (500, '70245554780', 1363.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (266, '72084500414', 4674.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (338, '75337403868', 6603.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (310, '70853274433', 1921.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (190, '71453295350', 8618.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (302, '78846878130', 2164.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (317, '78208976453', 6687.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (397, '75218006571', 4507.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (234, '74879194723', 2355.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (499, '78034814365', 9179.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (259, '78392831092', 2151.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (221, '72555534022', 3763.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (342, '73005945062', 5360.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (28, '79638499296', 8709.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (122, '79794177267', 8624.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (376, '75523880891', 2042.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (222, '79203601386', 5364.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (189, '76759949113', 6860.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (459, '72605283256', 3466.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (100, '79342427302', 6858.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (339, '74215806970', 2270.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (365, '73448648376', 6329.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (210, '71728589087', 4491.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (186, '79815714809', 6513.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (403, '71011481905', 2441.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (465, '74317434908', 7430.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (194, '72551921515', 8936.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (203, '78368048916', 3127.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (162, '79982632807', 9356.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (343, '72994597418', 3299.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (352, '71656739062', 6468.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (483, '76385752274', 2294.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (252, '79131451412', 3916.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (29, '79852536125', 8451.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (460, '70016534223', 2112.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (25, '79971732781', 8816.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (441, '79781932282', 5667.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (195, '70535097425', 4870.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (23, '79107007195', 8407.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (455, '76801719410', 1909.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (364, '79094289272', 7820.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (169, '79375832945', 9115.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (409, '76650162255', 4728.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (269, '70098401026', 8022.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (78, '79983636462', 9107.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (79, '79735064996', 9016.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (474, '79483844453', 4277.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (328, '73871924948', 898.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (200, '75823005571', 6010.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (470, '70113309063', 5224.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (109, '79723384780', 8535.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (426, '79872598807', 3567.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (40, '79358323908', 9030.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (185, '79612873741', 9194.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (309, '76587829442', 6217.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (45, '79290134605', 8763.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (154, '79763918452', 8739.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (398, '76681864809', 3241.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (494, '70931248656', 4245.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (22, '79143605909', 7890.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (472, '72787587114', 8876.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (479, '70385579757', 6673.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (10, '79517294826', 7083.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (244, '71761243392', 3983.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (371, '78165155837', 4464.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (424, '79412541918', 4651.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (110, '79541019043', 8022.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (366, '75129989125', 156.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (282, '74887214084', 6594.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (131, '79563612587', 8569.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (301, '72666304298', 4794.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (119, '79569380422', 7892.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (299, '73515735123', 3360.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (134, '79057729384', 8701.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (53, '79812441851', 8428.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (356, '73551902665', 4405.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (413, '77156152668', 4128.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (249, '78933461414', 306.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (335, '72040071370', 5072.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (346, '79632011708', 2489.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (417, '72165864089', 1509.5, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (452, '79259256792', 6850.0, 0, '03');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (89, '79737307189', 8695.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (38, '79033419551', 9348.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (419, '75628014196', 7305.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (393, '74603434692', 4566.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (367, '71281900769', 5738.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (138, '79059623911', 8567.0, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (167, '79562510228', 8184.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (96, '79774325592', 8292.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (160, '79857276437', 9401.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (284, '70584835934', 6173.5, 100, '11');
INSERT INTO public.clients (id, phone_number, balance, benefit_minutes_left, tariff_id) VALUES (316, '72185125887', 5764.5, 100, '11');


--
-- TOC entry 3334 (class 0 OID 16419)
-- Dependencies: 215
-- Data for Name: tariff; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tariff (tariff_id, tariff_name, subscription_fee, benefit_minutes, in_call_cost_id, out_call_cost_id) VALUES ('06', 'Безлимит 300', 100, 300, 2, 2);
INSERT INTO public.tariff (tariff_id, tariff_name, subscription_fee, benefit_minutes, in_call_cost_id, out_call_cost_id) VALUES ('03', 'Поминутный', 0, 0, 1, 1);
INSERT INTO public.tariff (tariff_id, tariff_name, subscription_fee, benefit_minutes, in_call_cost_id, out_call_cost_id) VALUES ('11', 'Обычный', 0, 100, 3, 4);


--
-- TOC entry 3181 (class 2606 OID 16403)
-- Name: call_cost call_cost_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.call_cost
    ADD CONSTRAINT call_cost_pkey PRIMARY KEY (id);


--
-- TOC entry 3185 (class 2606 OID 16451)
-- Name: clients clients_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_id_key UNIQUE (id);


--
-- TOC entry 3187 (class 2606 OID 16443)
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- TOC entry 3183 (class 2606 OID 16423)
-- Name: tariff tariff_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tariff
    ADD CONSTRAINT tariff_pkey PRIMARY KEY (tariff_id);


--
-- TOC entry 3190 (class 2606 OID 16444)
-- Name: clients clients_tariff_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_tariff_id_fkey FOREIGN KEY (tariff_id) REFERENCES public.tariff(tariff_id);


--
-- TOC entry 3188 (class 2606 OID 16424)
-- Name: tariff tariff_in_call_cost_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tariff
    ADD CONSTRAINT tariff_in_call_cost_fkey FOREIGN KEY (in_call_cost_id) REFERENCES public.call_cost(id);


--
-- TOC entry 3189 (class 2606 OID 16429)
-- Name: tariff tariff_out_call_cost_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tariff
    ADD CONSTRAINT tariff_out_call_cost_fkey FOREIGN KEY (out_call_cost_id) REFERENCES public.call_cost(id);


-- Completed on 2023-04-23 14:09:10

--
-- PostgreSQL database dump complete
--

