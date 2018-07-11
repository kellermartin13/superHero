USE superhero;

DELETE FROM heroOrgs;
DELETE FROM heroPowers;
DELETE FROM heroes;

INSERT INTO heroes VALUES (1, 'Superman', 'dumb');
INSERT INTO heroes VALUES (2, 'Batman', 'rich');	
INSERT INTO heroes VALUES (3, 'Wolverine', 'sharp');	
INSERT INTO heroes VALUES (4, 'The Joker', 'crazy');	
INSERT INTO heroes VALUES (5, 'Mr. Incredible', 'strong');

INSERT INTO heroorgs (heroid, orgid) VALUES (1, 1);
INSERT INTO heroorgs (heroid, orgid) VALUES (1, 2);
INSERT INTO heroorgs (heroid, orgid) VALUES (1, 3);
INSERT INTO heroorgs (heroid, orgid) VALUES (2, 1);
INSERT INTO heroorgs (heroid, orgid) VALUES (3, 3);
INSERT INTO heroorgs (heroid, orgid) VALUES (3, 4);
INSERT INTO heroorgs (heroid, orgid) VALUES (4, 1);
INSERT INTO heroorgs (heroid, orgid) VALUES (4, 2);
INSERT INTO heroorgs (heroid, orgid) VALUES (4, 3);
INSERT INTO heroorgs (heroid, orgid) VALUES (4, 4);
INSERT INTO heroorgs (heroid, orgid) VALUES (4, 5);
INSERT INTO heroorgs (heroid, orgid) VALUES (5, 5);
INSERT INTO heroorgs (heroid, orgid) VALUES (5, 6);

INSERT INTO heroPowers(heroId, powerId) VALUES (1, 1);
INSERT INTO heroPowers(heroId, powerId) VALUES (1, 2);
INSERT INTO heroPowers(heroId, powerId) VALUES (2, 2);
INSERT INTO heroPowers(heroId, powerId) VALUES (2, 3);
INSERT INTO heroPowers(heroId, powerId) VALUES (3, 3);
INSERT INTO heroPowers(heroId, powerId) VALUES (4, 4);
INSERT INTO heroPowers(heroId, powerId) VALUES (5, 5);

INSERT INTO heroSightings(heroId, sightingId) VALUES (1, 1);
INSERT INTO heroSightings(heroId, sightingId) VALUES (1, 2);
INSERT INTO heroSightings(heroId, sightingId) VALUES (1, 3);
INSERT INTO heroSightings(heroId, sightingId) VALUES (2, 2);
INSERT INTO heroSightings(heroId, sightingId) VALUES (2, 3);
INSERT INTO heroSightings(heroId, sightingId) VALUES (2, 4);
INSERT INTO heroSightings(heroId, sightingId) VALUES (3, 3);
INSERT INTO heroSightings(heroId, sightingId) VALUES (3, 4);
INSERT INTO heroSightings(heroId, sightingId) VALUES (4, 4);
INSERT INTO heroSightings(heroId, sightingId) VALUES (4, 5);
INSERT INTO heroSightings(heroId, sightingId) VALUES (5, 6);
INSERT INTO heroSightings(heroId, sightingId) VALUES (5, 7);
INSERT INTO heroSightings(heroId, sightingId) VALUES (5, 8);


