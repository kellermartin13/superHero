/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.dao;

import com.sg.heroes.model.Hero;
import com.sg.heroes.model.Location;
import com.sg.heroes.model.Organization;
import com.sg.heroes.model.Power;
import com.sg.heroes.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Keller Martin
 */
public class HeroesDaoImpl implements HeroesDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//     _    _ ______ _____   ____  ______  _____ 
// | |  | |  ____|  __ \ / __ \|  ____|/ ____|
// | |__| | |__  | |__) | |  | | |__  | (___  
// |  __  |  __| |  _  /| |  | |  __|  \___ \ 
// | |  | | |____| | \ \| |__| | |____ ____) |
// |_|  |_|______|_|  \_\\____/|______|_____/ 
//    
//    
    //INSERT INTO BRIDGE TABLE HERO/POWERS
    private static final String SQL_INSERT_HERO_POWERS
            = "INSERT INTO heroPowers (heroId, powerId) VALUES (?, ?)";

    private void insertHeroPowers(Hero hero) {
        final int heroId = hero.getHeroId();
        final List<Power> powers = hero.getPowers();

        for (Power p : powers) {
            this.jdbcTemplate.update(SQL_INSERT_HERO_POWERS,
                    heroId, p.getPowerId());
        }
    }

    //ASSOCIATING LIST OF POWERS FOR EACH HERO
    private static final String SQL_FIND_POWERS_FOR_HERO
            = "SELECT * FROM Powers p "
            + "INNER JOIN heroPowers hp ON p.id = hp.powerId "
            + "WHERE hp.heroId = ?";

    private List<Power> findPowersForHero(Hero hero) {
        return this.jdbcTemplate.query(SQL_FIND_POWERS_FOR_HERO,
                new PowerMapper(), hero.getHeroId());
    }

    private List<Hero> associatePowersWithHero(List<Hero> heroes) {
        for (Hero h : heroes) {
            h.setPowers(this.findPowersForHero(h));
        }
        return heroes;
    }

    //ADD NEW HERO
    private static final String SQL_ADD_HERO
            = "INSERT INTO Heroes (heroName, description) VALUES (?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHero(Hero hero) {
        this.jdbcTemplate.update(SQL_ADD_HERO,
                hero.getName(), hero.getDescription());
        hero.setHeroId(this.jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()", Integer.class));
        this.insertHeroPowers(hero);
    }

    //REMOVE HERO FROM BRIDGE AND HEROES TABLE
    private static final String SQL_REMOVE_HERO_POWERS
            = "DELETE FROM heroPowers  WHERE heroId = ?";
    
    private static final String SQL_REMOVE_HERO_ORGS
            = "DELETE FROM heroOrgs WHERE heroId = ?";

    private static final String SQL_REMOVE_HERO
            = "DELETE FROM Heroes WHERE id = ?";

    @Override
    public void deleteHero(int heroId) {
        this.jdbcTemplate.update(SQL_REMOVE_HERO_POWERS, heroId);
        this.jdbcTemplate.update(SQL_REMOVE_HERO_ORGS, heroId);
        this.jdbcTemplate.update(SQL_REMOVE_HERO, heroId);
    }

    //UPDATE EXISTING HERO IN BRIDGE AND HEROES TABLE
    private static final String SQL_UPDATE_HERO
            = "UPDATE Heroes SET heroName = ?, description = ? WHERE id = ?";

    @Override
    public Hero updateHero(Hero hero) {
        this.jdbcTemplate.update(SQL_UPDATE_HERO, hero.getName(),
                hero.getDescription(), hero.getHeroId());
        this.jdbcTemplate.update(SQL_REMOVE_HERO_POWERS, hero.getHeroId());
        this.insertHeroPowers(hero);
        return hero;
    }

    //GET HERO BY ID
    private static final String SQL_GET_HERO
            = "SELECT * FROM Heroes h WHERE id = ?";

    @Override
    public Hero getHeroById(int heroId) {
        try {
            Hero hero = this.jdbcTemplate.queryForObject(SQL_GET_HERO,
                    new HeroMapper(), heroId);
            hero.setPowers(this.findPowersForHero(hero));
            return hero;
        } catch (EmptyResultDataAccessException ex) {
            System.err.println("Could not get hero by id: " + heroId);
            return null;
        }
    }

    //GET ALL HEROES
    private static final String SQL_GET_HEROES
            = "SELECT * FROM Heroes";

    @Override
    public List<Hero> getAllHeroes() {
        List<Hero> heroes = this.jdbcTemplate.query(
                SQL_GET_HEROES, new HeroMapper());
        return this.associatePowersWithHero(heroes);
    }

    //GET LIST OF HEROES BY POWER ID
    private static final String SQL_SELECT_HEROES_BY_POWER_ID
            = "SELECT h.* FROM heroes h "
            + "INNER JOIN heroPowers hp ON h.id = hp.heroId "
            + "WHERE hp.powerId = ?";

    @Override
    public List<Hero> getHeroesByPowerId(int powerId) {
        List<Hero> heroes = this.jdbcTemplate.query(
                SQL_SELECT_HEROES_BY_POWER_ID, new HeroMapper(), powerId);
        return this.associatePowersWithHero(heroes);
    }

//       _____ _____ _____ _    _ _______ _____ _   _  _____  _____ 
//  / ____|_   _/ ____| |  | |__   __|_   _| \ | |/ ____|/ ____|
// | (___   | || |  __| |__| |  | |    | | |  \| | |  __| (___  
//  \___ \  | || | |_ |  __  |  | |    | | | . ` | | |_ |\___ \ 
//  ____) |_| || |__| | |  | |  | |   _| |_| |\  | |__| |____) |
// |_____/|_____\_____|_|  |_|  |_|  |_____|_| \_|\_____|_____/ 
//                                                              
//                                                      
    //INSERT INTO BRIDGE TABLE HERO/SIGHTINGS
    private static final String SQL_INSERT_HERO_SIGHTINGS
            = "INSERT INTO heroSightings (heroId, sightingId) VALUES (?, ?)";

    private void insertHeroSightings(Sighting sighting) {
        final Integer id = sighting.getSightingId();
        final List<Hero> heroes = sighting.getHeroes();

        for (Hero hero : heroes) {
            this.jdbcTemplate.update(SQL_INSERT_HERO_SIGHTINGS,
                    hero.getHeroId(), id);
        }
    }

    //HELPER METHODS TO ASSOCIATE SIGHTINGS WITH HERO ID AND LOCATION ID
    private static final String SQL_SELECT_HEROES_BY_SIGHTING_ID
            = "SELECT h.* FROM heroes h "
            + "INNER JOIN heroSightings hs ON h.id = hs.heroId "
            + "WHERE sightingId = ?";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "SELECT l.* FROM locations l "
            + "INNER JOIN sightings s ON l.id = s.locationId "
            + "WHERE s.id = ?";

    private List<Hero> findHeroesBySighting(Sighting sighting) {
        return this.jdbcTemplate.query(SQL_SELECT_HEROES_BY_SIGHTING_ID,
                new HeroMapper(), sighting.getSightingId());
    }

    private Location findLocationBySighting(Sighting sighting) {
        return this.jdbcTemplate.queryForObject(
                SQL_SELECT_LOCATION_BY_SIGHTING_ID,
                new LocationMapper(), sighting.getSightingId());
    }

    private List<Sighting>
            associateLocationAndHeroesWithSighting(List<Sighting> sightingList) {
        for (Sighting s : sightingList) {
            s.setHeroes(this.findHeroesBySighting(s));
            s.setLocation(this.findLocationBySighting(s));
        }
        return sightingList;
    }

    //INSERT NEW SIGHTING       
    private static final String SQL_INSERT_SIGHTING
            = "INSERT INTO Sightings (locationId, sightingDate) VALUES (?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        this.jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getLocation().getLocationId(), sighting.getDate());
        sighting.setSightingId(this.jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()", Integer.class));
        this.insertHeroSightings(sighting);
    }

    //DELETE SIGHTING FROM BRIDGE AND FROM SIGHTINGS TABLE 
    private static final String SQL_DELETE_SIGHTING_BRIDGE
            = "DELETE FROM heroSightings WHERE sightingId = ?";

    private void deleteSightingFromBridges(int sightingId) {
        this.jdbcTemplate.update(SQL_DELETE_SIGHTING_BRIDGE,
                sightingId);
    }

    private static final String SQL_DELETE_SIGHTING
            = "DELETE FROM Sightings WHERE id = ?";

    @Override
    public void deleteSighting(int sightingId) {
        this.deleteSightingFromBridges(sightingId);
        this.jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    //UPDATE EXISTING SIGHTING IN BRIDGE AND SIGHTINGS TABLE
    private static final String SQL_UPDATE_SIGHTING
            = "UPDATE Sightings SET sightingDate = ? WHERE id = ?";

    @Override
    public Sighting updateSighting(Sighting sighting) {
        this.jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getDate().toString(), sighting.getSightingId());
        this.deleteSightingFromBridges(sighting.getSightingId());
        this.insertHeroSightings(sighting);
        return sighting;
    }

    //GET SIGHTING BY ID
    private static final String SQL_GET_SIGHTING
            = "SELECT * FROM Sightings WHERE id = ?";

    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            Sighting sighting = this.jdbcTemplate.queryForObject(SQL_GET_SIGHTING,
                    new SightingMapper(), sightingId);
            sighting.setHeroes(this.findHeroesBySighting(sighting));
            sighting.setLocation(this.findLocationBySighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    //GET ALL SIGHTINGS
    private static final String SQL_ALL_SIGHTINGS
            = "SELECT * FROM Sightings";

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightings = this.jdbcTemplate.query(
                SQL_ALL_SIGHTINGS, new SightingMapper());
        return this.associateLocationAndHeroesWithSighting(sightings);
    }
    
    //GET TOP 15 SIGHTINGS
    private static final String SQL_RECENT_SIGHTINGS
            = "SELECT * FROM sightings s "
            + "ORDER BY s.sightingDate DESC "
            + "LIMIT 10";
    
    @Override
    public List<Sighting> getRecentSightings(){
        List<Sighting> sightings = this.jdbcTemplate.query(
                SQL_RECENT_SIGHTINGS, new SightingMapper());
        return this.associateLocationAndHeroesWithSighting(sightings);
    }

    //GET SIGHTINGS BY HERO ID
    private static final String SQL_SELECT_SIGHTINGS_BY_HERO_ID
            = "SELECT s.* FROM sightings s "
            + "INNER JOIN heroSightings hs ON s.id = hs.sightingId "
            + "WHERE heroId = ?";

    @Override
    public List<Sighting> getSightingsByHeroId(int heroId) {
        List<Sighting> sightings = this.jdbcTemplate.query(
                SQL_SELECT_SIGHTINGS_BY_HERO_ID, new SightingMapper(), heroId);
        return this.associateLocationAndHeroesWithSighting(sightings);
    }

    //GET SIGHTING BY LOCATION ID
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID
            = "SELECT * FROM Sightings s WHERE s.locationId = ?";

    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        List<Sighting> sightings = this.jdbcTemplate.query(
                SQL_SELECT_SIGHTINGS_BY_LOCATION_ID, new SightingMapper(), locationId);
        return this.associateLocationAndHeroesWithSighting(sightings);
    }

//       ____  _____   _____  _____ 
//  / __ \|  __ \ / ____|/ ____|
// | |  | | |__) | |  __| (___  
// | |  | |  _  /| | |_ |\___ \ 
// | |__| | | \ \| |__| |____) |
//  \____/|_|  \_\\_____|_____/ 
//                              
//    
    //INSERT INTO BRIDGE TABLE HERO/ORGS
    private static final String SQL_INSERT_HERO_ORGS
            = "INSERT INTO HeroOrgs (heroId, orgId) VALUES (?, ?)";

    //HELPER METHODS TO ASSOCIATE ORGANIZATIONS WITH LOCATIONS AND HEROES
    private static final String SQL_SELECT_HERO_BY_ORG_ID
            = "SELECT h.* FROM heroes h "
            + "INNER JOIN heroOrgs ho ON h.id = ho.heroId "
            + "WHERE h.id = ho.heroId "
            + "AND ho.orgId = ?";

    private static final String SQL_SELECT_LOCATION_BY_ORG_ID
            = "SELECT l.* "
            + "FROM locations l "
            + "INNER JOIN organizations o ON l.id = o.locationId "
            + "WHERE l.id = o.locationId AND "
            + "o.id = ?";

    private void insertHeroOrgs(Organization org) {
        final int orgId = org.getOrgId();
        final List<Hero> heroes = org.getHeroes();

        for (Hero hero : heroes) {
            this.jdbcTemplate.update(SQL_INSERT_HERO_ORGS,
                    hero.getHeroId(), orgId);
        }
    }

    private List<Hero> findHeroesForOrg(Organization org) {
        return this.jdbcTemplate.query(SQL_SELECT_HERO_BY_ORG_ID,
                new HeroMapper(), org.getOrgId());
    }

    private Location findLocationForOrg(Organization org) {
        return this.jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ORG_ID,
                new LocationMapper(), org.getOrgId());
    }

    private List<Organization>
            associateLocationAndHeroesWithOrg(List<Organization> orgList) {
        for (Organization org : orgList) {
            org.setHeroes(this.findHeroesForOrg(org));
            org.setLocation(this.findLocationForOrg(org));
        }
        return orgList;
    }

    //ADD NEW ORG
    private static final String SQL_ADD_ORG
            = "INSERT INTO Organizations (`orgName`, locationId, `description`) VALUES (?, ?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrg(Organization org) {
        String name = org.getName();
                Integer id = org.getLocation().getLocationId();
                String desc = org.getDescription();
        this.jdbcTemplate.update(SQL_ADD_ORG,
                name, id, desc);
        org.setOrgId(this.jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()", Integer.class));
        this.insertHeroOrgs(org);
    }

    //DELETE FROM BRIDGE TABLE AND ORG TABLE
    private static final String SQL_DELETE_ORG_HEROORG
            = "DELETE FROM HeroOrgs WHERE orgId = ?";

    public void removeOrganizationFromBridges(int organizationId) {
        this.jdbcTemplate.update(SQL_DELETE_ORG_HEROORG, organizationId);
    }

    private static final String SQL_DELETE_ORG
            = "DELETE FROM Organizations WHERE id = ?";

    @Override
    public void deleteOrg(int orgId) {
        this.removeOrganizationFromBridges(orgId);
        this.jdbcTemplate.update(SQL_DELETE_ORG, orgId);
    }

    //UPDATE EXISTING ORG IN BRIDGE AND ORG TABLE
    private static final String SQL_UPDATE_ORG
            = "UPDATE Organizations SET orgName = ?, description = ? WHERE id = ?";

    @Override
    public Organization updateOrg(Organization org) {
        this.jdbcTemplate.update(SQL_UPDATE_ORG,
                org.getName(), org.getDescription(), org.getOrgId());
        this.removeOrganizationFromBridges(org.getOrgId());
        this.insertHeroOrgs(org);
        return org;
    }

    //GET ORG BY ID
    private static final String SQL_GET_ORG
            = "SELECT * FROM Organizations WHERE id = ?";

    @Override
    public Organization getOrgById(int orgId) {
        try {
            Organization org = this.jdbcTemplate.queryForObject(SQL_GET_ORG,
                    new OrgMapper(), orgId);
            org.setHeroes(this.findHeroesForOrg(org));
            org.setLocation(this.findLocationForOrg(org));
            return org;
        } catch (EmptyResultDataAccessException ex) {
            System.err.println("Tried and failed to get an organization by " + orgId);
            return null;
        }
    }

    //GET ALL ORGS
    private static final String SQL_ALL_ORGS
            = "SELECT * FROM Organizations";

    @Override
    public List<Organization> getAllOrgs() {
        List<Organization> orgList
                = this.jdbcTemplate.query(SQL_ALL_ORGS, new OrgMapper());
        return this.associateLocationAndHeroesWithOrg(orgList);
    }

    //GET ORGS BY HERO ID
    private static final String SQL_SELECT_ORGS_BY_HERO_ID
            = "SELECT o.* FROM Organizations o "
            + "INNER JOIN heroOrgs ho ON o.id = ho.orgId "
            + "WHERE ho.heroId = ?";

    @Override
    public List<Organization> getOrgsByHeroId(int heroId) {
        List<Organization> orgList
                = this.jdbcTemplate.query(SQL_SELECT_ORGS_BY_HERO_ID,
                        new OrgMapper(), heroId);
        return this.associateLocationAndHeroesWithOrg(orgList);
    }

    //GET ORGS BY LOCATION ID
    private static final String SQL_SELECT_ORGS_BY_LOCATION_ID
            = "SELECT * FROM Organizations o WHERE o.locationId = ?";

    @Override
    public List<Organization> getOrgsByLocationId(int locationId) {
        List<Organization> orgList
                = this.jdbcTemplate.query(SQL_SELECT_ORGS_BY_LOCATION_ID,
                        new OrgMapper(), locationId);
        return this.associateLocationAndHeroesWithOrg(orgList);
    }
//      _      ____   _____       _______ _____ ____  _   _  _____ 
// | |    / __ \ / ____|   /\|__   __|_   _/ __ \| \ | |/ ____|
// | |   | |  | | |       /  \  | |    | || |  | |  \| | (___  
// | |   | |  | | |      / /\ \ | |    | || |  | | . ` |\___ \ 
// | |___| |__| | |____ / ____ \| |   _| || |__| | |\  |____) |
// |______\____/ \_____/_/    \_\_|  |_____\____/|_| \_|_____/ 
//                                                             
//                               

    //ADD NEW LOCATION
    private static final String SQL_ADD_LOCATION
            = "INSERT INTO Locations (`locationName`, `description`, `address`, "
            + "`city`, `country`, `latitude`, `longitude`) VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        this.jdbcTemplate.update(SQL_ADD_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getCity(),
                location.getCountry(),
                location.getLatitude(),
                location.getLongitude());
        location.setLocationId(this.jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()", Integer.class));
    }

    //DELETE LOCATION FROM BRIDGES AND LOCATIONS TABLE
    private static final String SQL_DELETE_LOCATION_SIGHTINGS
            = "DELETE FROM Sightings WHERE locationId = ?";
    private static final String SQL_DELETE_LOCATION_ORGS
            = "DELETE FROM Organizations WHERE locationId = ?";

    public void removeLocationFromBridges(int locationId) {
        this.jdbcTemplate.update(SQL_DELETE_LOCATION_SIGHTINGS, locationId);
        this.jdbcTemplate.update(SQL_DELETE_LOCATION_ORGS, locationId);
    }

    private static final String SQL_DELETE_LOCATION
            = "DELETE FROM Locations WHERE id = ?";

    @Override
    public void deleteLocation(int locationId) {
        this.removeLocationFromBridges(locationId);
        this.jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    //UPDATE LOCATION IN BRIDGES AND LOCATIONS TABLE
    private static final String SQL_UPDATE_LOCATION
            = "UPDATE Locations SET locationName = ?, description = ?, "
            + "address = ?, city = ?, country = ?, latitude = ?, "
            + "longitude = ? WHERE id = ?";

    @Override
    public Location updateLocation(Location location) {
        this.jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getName(), location.getDescription(),
                location.getAddress(), location.getCity(), 
                location.getCountry(), location.getLatitude(),
                location.getLongitude(), location.getLocationId());
        return location;
    }

    //GET LOCATION
    private static final String SQL_GET_LOCATION
            = "SELECT * FROM Locations WHERE id = ?";

    @Override
    public Location getLocationById(int locationId) {
        try {
            return this.jdbcTemplate.queryForObject(SQL_GET_LOCATION, new LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException ex) {
            System.err.println("Tried and failed to get a location by " + locationId);
            return null;
        }
    }

    //GET ALL LOCATIONS
    private static final String SQL_ALL_LOCATIONS
            = "SELECT * FROM Locations";

    @Override
    public List<Location> getAllLocations() {
        return this.jdbcTemplate.query(SQL_ALL_LOCATIONS, new LocationMapper());
    }

//      _____   ______          ________ _____   _____ 
// |  __ \ / __ \ \        / /  ____|  __ \ / ____|
// | |__) | |  | \ \  /\  / /| |__  | |__) | (___  
// |  ___/| |  | |\ \/  \/ / |  __| |  _  / \___ \ 
// | |    | |__| | \  /\  /  | |____| | \ \ ____) |
// |_|     \____/   \/  \/   |______|_|  \_\_____/ 
//                                             
//    
    //ADD NEW POWER
    private static final String SQL_ADD_POWER
            = "INSERT INTO Powers (`powerName`, `description`) "
            + " VALUES (?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPower(Power power) {
        jdbcTemplate.update(SQL_ADD_POWER,
                power.getName(),
                power.getDescription());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        power.setPowerId(id);
    }

    //DELETE POWER FROM BRIDGE AND POWERS TABLE
    private static final String SQL_DELETE_POWER
            = "DELETE FROM Powers WHERE id = ?";

    private static final String SQL_DELETE_POWER_FROM_BRIDGE
            = "DELETE FROM HeroPowers WHERE powerId = ?";

    public void removePowerFromBridge(int powerId) {
        this.jdbcTemplate.update(SQL_DELETE_POWER_FROM_BRIDGE, powerId);
    }

    @Override
    public void deletePower(int powerId) {
        this.removePowerFromBridge(powerId);
        jdbcTemplate.update(SQL_DELETE_POWER, powerId);
    }

    //UPDATE POWER
    private static final String SQL_UPDATE_POWER
            = "UPDATE Powers SET powerName = ?, description = ? WHERE id = ?";

    @Override
    public Power updatePower(Power power) {
        this.jdbcTemplate.update(SQL_UPDATE_POWER,
                power.getName(),
                power.getDescription(),
                power.getPowerId());
        return power;
    }

    //GET POWER
    private static final String SQL_GET_POWER
            = "SELECT * FROM Powers WHERE id = ?";

    @Override
    public Power getPowerById(int powerId) {
        try {
            return this.jdbcTemplate.queryForObject(SQL_GET_POWER, new PowerMapper(), powerId);
        } catch (EmptyResultDataAccessException ex) {
            System.err.println("Tried and failed to get a power by " + powerId);
            return null;
        }
    }

    //GET ALL POWERS
    private static final String SQL_GET_ALL_POWERS
            = "SELECT * FROM Powers";

    @Override
    public List<Power> getAllPowers() {
        return this.jdbcTemplate.query(SQL_GET_ALL_POWERS, new PowerMapper());
    }

//      __  __          _____  _____  ______ _____   _____ 
// |  \/  |   /\   |  __ \|  __ \|  ____|  __ \ / ____|
// | \  / |  /  \  | |__) | |__) | |__  | |__) | (___  
// | |\/| | / /\ \ |  ___/|  ___/|  __| |  _  / \___ \ 
// | |  | |/ ____ \| |    | |    | |____| | \ \ ____) |
// |_|  |_/_/    \_\_|    |_|    |______|_|  \_\_____/ 
//                                                     
//    
    private static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setName(rs.getString("heroName"));
            hero.setDescription(rs.getString("description"));
            hero.setHeroId(rs.getInt("id"));
            return hero;
        }
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingId(rs.getInt("id"));
            s.setDate(rs.getTimestamp("sightingDate").toLocalDateTime());
            return s;
        }
    }

    private static final class OrgMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setName(rs.getString("orgName"));
            org.setOrgId(rs.getInt("id"));
            org.setDescription(rs.getString("description"));
            return org;
        }
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location l = new Location();
            l.setLocationId(rs.getInt("id"));
            l.setName(rs.getString("locationName"));
            l.setDescription(rs.getString("description"));
            l.setAddress(rs.getString("address"));
            l.setCity(rs.getString("city"));
            l.setCountry(rs.getString("country"));
            l.setLatitude(rs.getString("latitude"));
            l.setLongitude(rs.getString("longitude"));
            return l;
        }
    }

    private static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power p = new Power();
            p.setPowerId(rs.getInt("id"));
            p.setName(rs.getString("powerName"));
            p.setDescription(rs.getString("description"));
            return p;
        }
    }
}
