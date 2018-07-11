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
import java.util.List;

/**
 *
 * @author Keller Martin
 */
public interface HeroesDao {
    
    //Heroes
    public void addHero(Hero hero);
    
    public void deleteHero(int heroId);
    
    public Hero updateHero(Hero hero);
    
    public Hero getHeroById(int heroId);
    
    public List<Hero> getAllHeroes();
    
    public List<Hero> getHeroesByPowerId(int powerId);
    
    //Sightings
    public void addSighting(Sighting sighting);
    
    public void deleteSighting(int sightingId);
    
    public Sighting updateSighting(Sighting sighting);
    
    public Sighting getSightingById(int sightingId);
    
    public List<Sighting> getAllSightings();
    
    public List<Sighting> getSightingsByHeroId(int heroId);
    
    public List<Sighting> getSightingsByLocationId(int locationId);
    
    public List<Sighting> getRecentSightings();
    
    //Organizations
    public void addOrg(Organization org);
    
    public void deleteOrg(int OrgId);
    
    public Organization updateOrg(Organization org);
    
    public Organization getOrgById(int orgId);
    
    public List<Organization> getAllOrgs();
    
    public List<Organization> getOrgsByHeroId(int heroId);
    
    public List<Organization> getOrgsByLocationId(int locationId);
    
    //Locations
    public void addLocation(Location location);
    
    public void deleteLocation(int locationId);
    
    public Location updateLocation(Location location);
    
    public Location getLocationById(int locationId);
    
    public List<Location> getAllLocations();
    
    //Powers
    public void addPower(Power power);
    
    public void deletePower(int powerId);
    
    public Power updatePower(Power power);
    
    public Power getPowerById(int powerId);
    
    public List<Power> getAllPowers();
}
