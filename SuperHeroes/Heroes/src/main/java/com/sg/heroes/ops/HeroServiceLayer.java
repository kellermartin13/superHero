/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.ops;

import com.sg.heroes.model.Hero;
import com.sg.heroes.model.Location;
import com.sg.heroes.model.Organization;
import com.sg.heroes.model.Power;
import com.sg.heroes.model.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Keller Martin
 */
public interface HeroServiceLayer {
    //powers
    public void addNewPower(Power power);
    public Power getPower(Integer id);
    public List<Power> getAllPowers();
    public Power updatePower(Power power);
    public void deletePower(Integer id);
    
    //orgs
    public void addNewOrg(Organization org);
    public Organization getOrg(Integer id);
    public List<Organization> getAllOrganizations();
    public List<Organization> getOrgsByHeroId(Integer id);
    public List<Organization> getOrgsByLocationId(Integer id);
    public Organization updateOrg(Organization org);
    public void deleteOrg(Integer id);
    
    //heroes
    public List<Hero> getAllHeroes();
    public Hero getHero(Integer id);
    public void addHero(Hero hero);
    public Hero updateHero(Hero hero);
    public void deleteHero(Integer id);
    
    //locations
    public void addNewLocation(Location l);
    public List<Location> getAllLocations();
    
    //sightings
    public List<Sighting> getAllSightings();
    public List<Sighting> getSightingsByHeroId(Integer id);
    public List<Sighting> getSightingsByLocationId(Integer id);
    public List<Sighting> getSightingsByDate(LocalDate date);
    public List<Sighting> getRecentSightings();
    public void addSighting(Sighting sighting);
}
