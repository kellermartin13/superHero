DROP DATABASE IF EXISTS SuperHero;
CREATE DATABASE SuperHero;
USE SuperHero;

CREATE TABLE Sightings (
	id INT NOT NULL auto_increment,
    locationId INT NOT NULL,
    sightingDate DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE Locations ( 
	id INT NOT NULL auto_increment,
    locationName VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
    address VARCHAR(250) NOT NULL,
    city VARCHAR(75) NOT NULL, 
    country VARCHAR(75) NOT NULL,
    latitude VARCHAR(20) Null,
    longitude VARCHAR(20) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Organizations (
	id INT NOT NULL auto_increment,
    locationId INT NOT NULL,
    orgName VARCHAR(50) NOT NULL,
    description VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Heroes (
	id INT NOT NULL auto_increment,
    heroName VARCHAR(150) NOT NULL,
    description VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE HeroOrgs (
	heroId INT NOT NULL,
    orgId INT NOT NULL
);

CREATE TABLE Powers (
	id INT NOT NULL auto_increment,
	powerName VARCHAR(150) NOT NULL,
    description VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE HeroPowers (
	heroId INT NOT NULL,
    powerId INT NOT NULL
);

CREATE TABLE HeroSightings (
	heroId INT NOT NULL,
    sightingId INT NOT NULL
);

ALTER TABLE Sightings ADD CONSTRAINT sighting_location FOREIGN KEY (locationId) REFERENCES Locations (id);
ALTER TABLE Organizations ADD CONSTRAINT org_location FOREIGN KEY (locationId) REFERENCES Locations (id);
ALTER TABLE HeroOrgs ADD CONSTRAINT heroOrg_hero FOREIGN KEY (heroId) REFERENCES Heroes (id);
ALTER TABLE HeroOrgs ADD CONSTRAINT heroOrg_org FOREIGN KEY (orgId) REFERENCES Organizations (id);
ALTER TABLE HeroPowers ADD CONSTRAINT heroPower_hero FOREIGN KEY (heroId) REFERENCES Heroes (id);
ALTER TABLE HeroPowers ADD CONSTRAINT heroPower_power FOREIGN KEY (powerId) REFERENCES Powers (id);
ALTER TABLE HeroSightings ADD CONSTRAINT heroSighting_hero FOREIGN KEY (heroId) REFERENCES Heroes (id);
ALTER TABLE HeroSightings ADD CONSTRAINT heroSighting_sight FOREIGN KEY (sightingId) REFERENCES Sightings (id);




