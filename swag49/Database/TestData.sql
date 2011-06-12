
SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

-- TODO: Users

INSERT INTO map (id, maxusers, url) VALUES (1, 10, 'http://localhost/map/1');

INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (1, false, 1232, 1523, 823, 2312, true, 16233, 3211, 10232, 32112, 562, 801, 232, 123, 1, 3);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (2, false, 532, 698, 492, 1023, false, 8901, 14923, 6790, 21322, 123, 499, 132, 99, 1, 2);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (3, false, 243, 345, 213, 543, true, 3211, 5343, 2910, 7001, 89, 179, 73, 13, 1, 1);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (4, false, 13, 23, 14, 34, false, 232, 621, 123, 32, 10, 14, 3, 2, 1, 5);
INSERT INTO player (id, deleted, income_crops, income_gold, income_stone, income_wood, online, resources_crops, resources_gold, resources_stone, resources_wood, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, plays_id, userId) VALUES (5, false, 10, 32, 23, 42, false, 132, 321, 14, 2, 0, 0, 0, 0, 1, 4);

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
INSERT INTO base (id, home, production_crops, production_gold, production_stone, production_wood, locatedon_mapid, locatedon_x, locatedon_y, owner_id) VALUES (6, true, 100, 100, 100, 100, 1, 5, 4, 1);


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

INSERT INTO trooptype (id, name, canfoundbase) VALUES (1, 'Bowmen', FALSE);
INSERT INTO trooptype (id, name, canfoundbase) VALUES (2, 'Knights', FALSE);
INSERT INTO trooptype (id, name, canfoundbase) VALUES (3, 'Cavalry', FALSE);
INSERT INTO trooptype (id, name, canfoundbase) VALUES (4, 'Settler', TRUE);

INSERT INTO trooplevel("level", trooptypeid, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, cargo_capacity, defense, speed, strength)
               VALUES (1, 1, 100, 100, 0, 50, 120, 10, 2, 0, 20, 0, 1, 5, 3);
INSERT INTO trooplevel("level", trooptypeid, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, cargo_capacity, defense, speed, strength)
               VALUES (2, 1, 300, 200, 0, 75, 180, 30, 5, 0, 20, 0, 1, 5, 5);
INSERT INTO trooplevel("level", trooptypeid, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, cargo_capacity, defense, speed, strength)
               VALUES (3, 1, 700, 500, 0, 200, 300, 70, 15, 0, 20, 0, 4, 8, 8);
INSERT INTO trooplevel("level", trooptypeid, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, cargo_capacity, defense, speed, strength)
               VALUES (1, 2, 200, 200, 0, 25, 60, 20, 2, 0, 0, 0, 3, 3, 5);
INSERT INTO trooplevel("level", trooptypeid, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, cargo_capacity, defense, speed, strength)
               VALUES (2, 2, 500, 300, 0, 50, 90, 35, 10, 0, 0, 0, 7, 3, 10);
INSERT INTO trooplevel("level", trooptypeid, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, cargo_capacity, defense, speed, strength)
               VALUES (3, 2, 100, 500, 0, 50, 120, 50, 20, 0, 5, 0, 10, 5, 15);
INSERT INTO trooplevel("level", trooptypeid, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, cargo_capacity, defense, speed, strength)
               VALUES (1, 3, 1000, 500, 0, 50, 180, 100, 20, 0, 0, 0, 8, 20, 12);
INSERT INTO trooplevel("level", trooptypeid, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, cargo_capacity, defense, speed, strength)
               VALUES (2, 3, 1500, 800, 0, 120, 300, 250, 45, 0, 0, 0, 12, 15, 15);
INSERT INTO trooplevel("level", trooptypeid, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, cargo_capacity, defense, speed, strength)
               VALUES (3, 3, 3000, 1000, 0, 120, 480, 500, 100, 0, 0, 0, 12, 15, 20);
INSERT INTO trooplevel("level", trooptypeid, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, cargo_capacity, defense, speed, strength)
               VALUES (1, 4, 20, 0, 0, 0, 60, 25, 0, 0, 0, 2, 0, 5, 1);
INSERT INTO trooplevel("level", trooptypeid, cost_crops, cost_gold, cost_stone, cost_wood, upgradeduration, upkeep_crops, upkeep_gold, upkeep_stone, upkeep_wood, cargo_capacity, defense, speed, strength)
               VALUES (2, 4, 50, 0, 0, 20, 90, 40, 0, 0, 0, 5, 2, 10, 2);

INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (1, 1, 1, 1, 1, 1, 1, 1);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (2, 1, 1, 1, 1, 1, 1, 1);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (3, 2, 1, 1, 1, 1, 1, 1);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (4, 2, 1, 1, 1, 1, 1, 1);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (5, 2, 1, 1, 1, 1, 1, 1);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (6, 1, 2, 1, 1, 1, 1, 2);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (7, 1, 2, 1, 1, 1, 1, 2);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (8, 2, 2, 1, 1, 1, 1, 2);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (9, 2, 2, 1, 1, 1, 1, 2);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (10, 3, 2, 1, 1, 1, 1, 2);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (11, 1, 3, 1, 1, 1, 1, 3);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (12, 2, 3, 1, 1, 1, 1, 3);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (13, 1, 4, 1, 1, 1, 1, 4);

INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (14, 2, 1, 2, 1, 2, 2, 1);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (15, 3, 1, 2, 1, 2, 2, 1);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (16, 3, 1, 2, 1, 2, 2, 1);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (17, 2, 2, 2, 1, 2, 2, 2);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (18, 2, 2, 2, 1, 2, 2, 2);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (19, 1, 4, 2, 1, 2, 2, 4);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (20, 1, 4, 2, 1, 2, 2, 4);

INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (21, 1, 1, 3, 1, 3, 3, 1);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (22, 2, 2, 3, 1, 3, 3, 2);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (23, 2, 2, 3, 1, 3, 3, 2);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (24, 1, 4, 3, 1, 3, 3, 4);

INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (25, 1, 2, 4, 1, 4, 4, 2);
INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (26, 1, 4, 4, 1, 4, 4, 4);

INSERT INTO troop(id, isoflevel_level, isoflevel_trooptypeid, owner_id, position_mapid, position_x, position_y, type_id)
          VALUES (27, 1, 4, 5, 1, 5, 5, 4);
