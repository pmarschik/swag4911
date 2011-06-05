
SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;


INSERT INTO map (id, maxusers) VALUES (1, 10);

INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (1, false, 243, 345, 213, 543, true, 3211, 5343, 2910, 7001, 89, 179, 73, 13, 1, 1);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (2, false, 532, 698, 492, 1023, false, 8901, 14923, 6790, 21322, 123, 499, 132, 99, 1, 2);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (3, false, 1232, 1523, 823, 2312, true, 16233, 3211, 10232, 32112, 562, 801, 232, 123, 1, 3);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (4, false, 10, 32, 23, 42, false, 132, 321, 14, 2, 0, 0, 0, 0, 1, 4);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (5, false, 13, 23, 14, 34, false, 232, 621, 123, 32, 10, 14, 3, 2, 1, 5);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (6, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 6);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (7, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 7);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (8, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 8);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (9, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (10, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 10);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (11, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 11);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (12, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 12);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (13, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 13);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (14, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 14);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (15, false, 0, 0, 0, 0, false, 0, 0, 0, 0, 0, 0, 0, 0, 1, 15);

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


INSERT INTO base (id, home, production_crops, production_gold, production_stone, production_wood, locatedon_mapid, locatedon_x, locatedon_y, owner_id) VALUES (1, true, 100, 100, 100, 100, 1, 1, 1, 1);
INSERT INTO base (id, home, production_crops, production_gold, production_stone, production_wood, locatedon_mapid, locatedon_x, locatedon_y, owner_id) VALUES (2, true, 100, 100, 100, 100, 1, 2, 2, 2);
INSERT INTO base (id, home, production_crops, production_gold, production_stone, production_wood, locatedon_mapid, locatedon_x, locatedon_y, owner_id) VALUES (3, true, 100, 100, 100, 100, 1, 3, 3, 3);
INSERT INTO base (id, home, production_crops, production_gold, production_stone, production_wood, locatedon_mapid, locatedon_x, locatedon_y, owner_id) VALUES (4, true, 100, 100, 100, 100, 1, 4, 4, 4);
INSERT INTO base (id, home, production_crops, production_gold, production_stone, production_wood, locatedon_mapid, locatedon_x, locatedon_y, owner_id) VALUES (5, true, 100, 100, 100, 100, 1, 5, 5, 5);
INSERT INTO base (id, home, production_crops, production_gold, production_stone, production_wood, locatedon_mapid, locatedon_x, locatedon_y, owner_id) VALUES (6, true, 100, 100, 100, 100, 1, 5, 4, 5);


INSERT INTO buildingtype (id, name) VALUES (2, 'Factory');
INSERT INTO buildingtype (id, name) VALUES (3, 'Casern');
INSERT INTO buildingtype (id, name) VALUES (1, 'Goldmine');
INSERT INTO buildingtype (id, name) VALUES (4, 'Stonemine');
INSERT INTO buildingtype (id, name) VALUES (5, 'Wood');
INSERT INTO buildingtype (id, name) VALUES (6, 'Crops');


INSERT INTO buildinglevel (buildingtypeid, level, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, production_crops, production_gold, production_stone, production_wood) VALUES (2, 1, 10, 10, 0, 0, 10, 0, 10, 10, 0, 0, 0, 0, 0);
INSERT INTO buildinglevel (buildingtypeid, level, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, production_crops, production_gold, production_stone, production_wood) VALUES (3, 1, 10, 10, 0, 0, 10, 0, 0, 0, 0, 0, 10, 0, 0);
INSERT INTO buildinglevel (buildingtypeid, level, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, production_crops, production_gold, production_stone, production_wood) VALUES (4, 1, 10, 10, 0, 0, 10, 0, 0, 0, 0, 0, 0, 10, 0);
INSERT INTO buildinglevel (buildingtypeid, level, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, production_crops, production_gold, production_stone, production_wood) VALUES (1, 1, 10, 10, 0, 0, 10, 0, 0, 0, 0, 0, 10, 0, 0);
INSERT INTO buildinglevel (buildingtypeid, level, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, production_crops, production_gold, production_stone, production_wood) VALUES (6, 1, 10, 10, 0, 0, 10, 0, 0, 0, 0, 10, 0, 0, 0);
INSERT INTO buildinglevel (buildingtypeid, level, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, production_crops, production_gold, production_stone, production_wood) VALUES (5, 1, 10, 10, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 10);

INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Action', 1);

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
INSERT INTO square (baseid, "position") VALUES (5, 1);
INSERT INTO square (baseid, "position") VALUES (5, 2);
INSERT INTO square (baseid, "position") VALUES (5, 3);
INSERT INTO square (baseid, "position") VALUES (5, 4);
INSERT INTO square (baseid, "position") VALUES (5, 5);
INSERT INTO square (baseid, "position") VALUES (5, 6);
INSERT INTO square (baseid, "position") VALUES (5, 7);
INSERT INTO square (baseid, "position") VALUES (5, 8);
INSERT INTO square (baseid, "position") VALUES (6, 1);
INSERT INTO square (baseid, "position") VALUES (6, 2);
INSERT INTO square (baseid, "position") VALUES (6, 3);
INSERT INTO square (baseid, "position") VALUES (6, 4);
INSERT INTO square (baseid, "position") VALUES (6, 5);
INSERT INTO square (baseid, "position") VALUES (6, 6);
INSERT INTO square (baseid, "position") VALUES (6, 7);
INSERT INTO square (baseid, "position") VALUES (6, 8);

INSERT INTO trooptype (id, name) VALUES (1, 'Bowmen');
INSERT INTO trooptype (id, name) VALUES (2, 'Knights');
INSERT INTO trooptype (id, name) VALUES (3, 'Cavalry');

