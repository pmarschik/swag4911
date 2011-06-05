--
-- PostgreSQL database dump
--

-- Started on 2011-05-21 19:39:53

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 1928 (class 0 OID 0)
-- Dependencies: 1551
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: swa
--

SELECT pg_catalog.setval('hibernate_sequence', 107, true);


--
-- TOC entry 1912 (class 0 OID 84429)
-- Dependencies: 1538
-- Data for Name: map; Type: TABLE DATA; Schema: public; Owner: swa
--

INSERT INTO map (id, maxusers, url) VALUES (1, 10, 'http://localhost/map/1');
INSERT INTO map (id, maxusers, url) VALUES (2, 20, 'http://localhost/map/2');


--
-- TOC entry 1914 (class 0 OID 84442)
-- Dependencies: 1540 1916 1912
-- Data for Name: player; Type: TABLE DATA; Schema: public; Owner: swa
--

INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (1, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (2, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (3, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (4, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (5, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1);

--
-- TOC entry 1918 (class 0 OID 84467)
-- Dependencies: 1544 1906 1912
-- Data for Name: tile; Type: TABLE DATA; Schema: public; Owner: swa
--

INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 1, 1, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 2, 1, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 1, 2, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 2, 2, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 3, 2, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 4, 3, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 3, 4, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 4, 4, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 5, 4, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 4, 5, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 5, 5, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 1, 4, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 1, 5, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 5, 1, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 5, 2, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 5, 3, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 2, 4, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 2, 5, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 4, 2, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 1, 0, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 0, 1, 'special', NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 1, 3, 'special', NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 4, 1, 'special', NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 3, 3, NULL, NULL);
INSERT INTO tile (mapid, x, y, special, base_id) VALUES (1, 2, 3, 'special', NULL);

--
-- TOC entry 1906 (class 0 OID 84395)
-- Dependencies: 1532 1914
-- Data for Name: base; Type: TABLE DATA; Schema: public; Owner: swa
--

INSERT INTO base (id, home, production_crops, production_gold, production_stone, production_wood, locatedon_mapid, locatedon_x, locatedon_y, owner_id) VALUES (1, true, 100, 100, 100, 100, 1, 1, 1, 1);
INSERT INTO base (id, home, production_crops, production_gold, production_stone, production_wood, locatedon_mapid, locatedon_x, locatedon_y, owner_id) VALUES (2, true, 100, 100, 100, 100, 1, 2, 2, 2);
INSERT INTO base (id, home, production_crops, production_gold, production_stone, production_wood, locatedon_mapid, locatedon_x, locatedon_y, owner_id) VALUES (3, true, 100, 100, 100, 100, 1, 3, 3, 3);
INSERT INTO base (id, home, production_crops, production_gold, production_stone, production_wood, locatedon_mapid, locatedon_x, locatedon_y, owner_id) VALUES (4, true, 100, 100, 100, 100, 1, 4, 4, 4);


--
-- TOC entry 1910 (class 0 OID 84417)
-- Dependencies: 1536
-- Data for Name: buildingtype; Type: TABLE DATA; Schema: public; Owner: swa
--

INSERT INTO buildingtype (id, name) VALUES (2, 'Factory');
INSERT INTO buildingtype (id, name) VALUES (3, 'Casern');
INSERT INTO buildingtype (id, name) VALUES (1, 'Goldmine');
INSERT INTO buildingtype (id, name) VALUES (4, 'Stonemine');
INSERT INTO buildingtype (id, name) VALUES (5, 'Wood');
INSERT INTO buildingtype (id, name) VALUES (6, 'Crops');


--
-- TOC entry 1909 (class 0 OID 84412)
-- Dependencies: 1535 1910
-- Data for Name: buildinglevel; Type: TABLE DATA; Schema: public; Owner: swa
--

INSERT INTO buildinglevel (buildingtypeid, level, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, production_crops, production_gold, production_stone, production_wood) VALUES (2, 1, 10, 10, 0, 0, 10, 0, 10, 10, 0, 0, 0, 0, 0);
INSERT INTO buildinglevel (buildingtypeid, level, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, production_crops, production_gold, production_stone, production_wood) VALUES (3, 1, 10, 10, 0, 0, 10, 0, 0, 0, 0, 0, 10, 0, 0);
INSERT INTO buildinglevel (buildingtypeid, level, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, production_crops, production_gold, production_stone, production_wood) VALUES (4, 1, 10, 10, 0, 0, 10, 0, 0, 0, 0, 0, 0, 10, 0);
INSERT INTO buildinglevel (buildingtypeid, level, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, production_crops, production_gold, production_stone, production_wood) VALUES (1, 1, 10, 10, 0, 0, 10, 0, 0, 0, 0, 0, 10, 0, 0);
INSERT INTO buildinglevel (buildingtypeid, level, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, production_crops, production_gold, production_stone, production_wood) VALUES (6, 1, 10, 10, 0, 0, 10, 0, 0, 0, 0, 10, 0, 0, 0);
INSERT INTO buildinglevel (buildingtypeid, level, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, production_crops, production_gold, production_stone, production_wood) VALUES (5, 1, 10, 10, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 10);


--
-- TOC entry 1908 (class 0 OID 84407)
-- Dependencies: 1534 1909 1910
-- Data for Name: building; Type: TABLE DATA; Schema: public; Owner: swa
--



--
-- TOC entry 1907 (class 0 OID 84402)
-- Dependencies: 1533 1908 1918 1914
-- Data for Name: buildaction; Type: TABLE DATA; Schema: public; Owner: swa
--



--
-- TOC entry 1911 (class 0 OID 84422)
-- Dependencies: 1537 1910 1909
-- Data for Name: buildingtype_buildinglevel; Type: TABLE DATA; Schema: public; Owner: swa
--



--
-- TOC entry 1925 (class 0 OID 84656)
-- Dependencies: 1552
-- Data for Name: hibernate_sequences; Type: TABLE DATA; Schema: public; Owner: swa
--

INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Action', 1);


--
-- TOC entry 1913 (class 0 OID 84434)
-- Dependencies: 1539 1914 1914
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: swa
--



--
-- TOC entry 1915 (class 0 OID 84447)
-- Dependencies: 1541 1914
-- Data for Name: resource; Type: TABLE DATA; Schema: public; Owner: swa
--



--
-- TOC entry 1917 (class 0 OID 84462)
-- Dependencies: 1543 1906 1908
-- Data for Name: square; Type: TABLE DATA; Schema: public; Owner: swa
--

INSERT INTO square (baseid, "position") VALUES (1, 1);
INSERT INTO square (baseid, "position") VALUES (1, 2);
INSERT INTO square (baseid, "position") VALUES (1, 3);
INSERT INTO square (baseid, "position") VALUES (1, 4);
INSERT INTO square (baseid, "position") VALUES (1, 5);
INSERT INTO square (baseid, "position") VALUES (1, 6);
INSERT INTO square (baseid, "position") VALUES (1, 7);
INSERT INTO square (baseid, "position") VALUES (1, 8);
INSERT INTO square (baseid, "position") VALUES (2, 1);
INSERT INTO square (baseid, "position") VALUES (2, 2);
INSERT INTO square (baseid, "position") VALUES (2, 3);
INSERT INTO square (baseid, "position") VALUES (2, 4);
INSERT INTO square (baseid, "position") VALUES (2, 5);
INSERT INTO square (baseid, "position") VALUES (2, 6);
INSERT INTO square (baseid, "position") VALUES (2, 7);
INSERT INTO square (baseid, "position") VALUES (2, 8);
INSERT INTO square (baseid, "position") VALUES (3, 1);
INSERT INTO square (baseid, "position") VALUES (3, 2);
INSERT INTO square (baseid, "position") VALUES (3, 3);
INSERT INTO square (baseid, "position") VALUES (3, 4);
INSERT INTO square (baseid, "position") VALUES (3, 5);
INSERT INTO square (baseid, "position") VALUES (3, 6);
INSERT INTO square (baseid, "position") VALUES (3, 7);
INSERT INTO square (baseid, "position") VALUES (3, 8);
INSERT INTO square (baseid, "position") VALUES (4, 1);
INSERT INTO square (baseid, "position") VALUES (4, 2);
INSERT INTO square (baseid, "position") VALUES (4, 3);
INSERT INTO square (baseid, "position") VALUES (4, 4);
INSERT INTO square (baseid, "position") VALUES (4, 5);
INSERT INTO square (baseid, "position") VALUES (4, 6);
INSERT INTO square (baseid, "position") VALUES (4, 7);
INSERT INTO square (baseid, "position") VALUES (4, 8);


--
-- TOC entry 1923 (class 0 OID 84492)
-- Dependencies: 1549
-- Data for Name: trooptype; Type: TABLE DATA; Schema: public; Owner: swa
--

INSERT INTO trooptype (id, name) VALUES (1, 'Bowmen');
INSERT INTO trooptype (id, name) VALUES (2, 'Knights');
INSERT INTO trooptype (id, name) VALUES (3, 'Cavalry');


--
-- TOC entry 1922 (class 0 OID 84487)
-- Dependencies: 1548 1923
-- Data for Name: trooplevel; Type: TABLE DATA; Schema: public; Owner: swa
--



--
-- TOC entry 1919 (class 0 OID 84472)
-- Dependencies: 1545 1918 1923 1922 1914
-- Data for Name: troop; Type: TABLE DATA; Schema: public; Owner: swa
--



--
-- TOC entry 1920 (class 0 OID 84477)
-- Dependencies: 1546 1918 1914
-- Data for Name: troopaction; Type: TABLE DATA; Schema: public; Owner: swa
--



--
-- TOC entry 1921 (class 0 OID 84482)
-- Dependencies: 1547 1920 1919
-- Data for Name: troopaction_troop; Type: TABLE DATA; Schema: public; Owner: swa
--



--
-- TOC entry 1924 (class 0 OID 84497)
-- Dependencies: 1550 1923 1922
-- Data for Name: trooptype_trooplevel; Type: TABLE DATA; Schema: public; Owner: swa
--

-- Completed on 2011-05-21 19:39:54

--
-- PostgreSQL database dump complete
--

