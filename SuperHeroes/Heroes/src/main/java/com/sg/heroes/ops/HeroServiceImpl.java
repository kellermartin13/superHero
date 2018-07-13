/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.ops;

import com.sg.heroes.dao.HeroesDao;
import com.sg.heroes.model.Hero;
import com.sg.heroes.model.Location;
import com.sg.heroes.model.Organization;
import com.sg.heroes.model.Power;
import com.sg.heroes.model.Sighting;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Keller Martin
 */
public class HeroServiceImpl implements HeroServiceLayer {
    
    private HeroesDao dao;
    
    @Inject
    public HeroServiceImpl(HeroesDao dao){
        this.dao = dao;
    }

    
//      _____   ______          ________ _____   _____ 
// |  __ \ / __ \ \        / /  ____|  __ \ / ____|
// | |__) | |  | \ \  /\  / /| |__  | |__) | (___  
// |  ___/| |  | |\ \/  \/ / |  __| |  _  / \___ \ 
// | |    | |__| | \  /\  /  | |____| | \ \ ____) |
// |_|     \____/   \/  \/   |______|_|  \_\_____/ 
//                                             
//    
    @Override
    public void addNewPower(Power power) {
        dao.addPower(power);
    }

    @Override
    public Power getPower(Integer id) {
        return dao.getPowerById(id);
    }

    @Override
    public List<Power> getAllPowers() {
        return dao.getAllPowers();
    }

    @Override
    public Power updatePower(Power power) {
        return dao.updatePower(power);
    }

    @Override
    public void deletePower(Integer id) {
        dao.deletePower(id);
    }

//       ____  _____   _____  _____ 
//  / __ \|  __ \ / ____|/ ____|
// | |  | | |__) | |  __| (___  
// | |  | |  _  /| | |_ |\___ \ 
// | |__| | | \ \| |__| |____) |
//  \____/|_|  \_\\_____|_____/ 
//                              
//    
    @Override
    public void addNewOrg(Organization org) {
        dao.addOrg(org);
    }

    @Override
    public Organization getOrg(Integer id) {
        return dao.getOrgById(id);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return dao.getAllOrgs();
    }

    @Override
    public List<Organization> getOrgsByHeroId(Integer id) {
        return dao.getOrgsByHeroId(id);
    }

    @Override
    public List<Organization> getOrgsByLocationId(Integer id) {
        return dao.getOrgsByLocationId(id);
    }

    @Override
    public Organization updateOrg(Organization org) {
        return dao.updateOrg(org);
    }

    @Override
    public void deleteOrg(Integer id) {
        dao.deleteOrg(id);
    }
    
//     _    _ ______ _____   ____  ______  _____ 
// | |  | |  ____|  __ \ / __ \|  ____|/ ____|
// | |__| | |__  | |__) | |  | | |__  | (___  
// |  __  |  __| |  _  /| |  | |  __|  \___ \ 
// | |  | | |____| | \ \| |__| | |____ ____) |
// |_|  |_|______|_|  \_\\____/|______|_____/ 
//    
//  

    @Override
    public List<Hero> getAllHeroes() {
        return dao.getAllHeroes();
    }
    
    @Override
    public Hero getHero(Integer id) {
        return dao.getHeroById(id);
    }
    
    @Override
    public void addHero(Hero hero){
        dao.addHero(hero);
    }
    
    @Override
    public Hero updateHero(Hero hero){
        return dao.updateHero(hero);
    }
    
    public void deleteHero(Integer id){
        dao.deleteHero(id);
    }
    
//      _      ____   _____       _______ _____ ____  _   _  _____ 
// | |    / __ \ / ____|   /\|__   __|_   _/ __ \| \ | |/ ____|
// | |   | |  | | |       /  \  | |    | || |  | |  \| | (___  
// | |   | |  | | |      / /\ \ | |    | || |  | | . ` |\___ \ 
// | |___| |__| | |____ / ____ \| |   _| || |__| | |\  |____) |
// |______\____/ \_____/_/    \_\_|  |_____\____/|_| \_|_____/ 
//                                                             
//                               

    @Override
    public void addNewLocation(Location l) {
        dao.addLocation(l);
    }

    @Override
    public List<Location> getAllLocations() {
        return dao.getAllLocations();
    }
    
    @Override
    public Location getLocation(Integer id){
        return dao.getLocationById(id);
    }
    
    @Override
    public Location updateLocation(Location location){
        return dao.updateLocation(location);
    }
    
    @Override
    public void deleteLocation(Integer id){
        dao.deleteLocation(id);
    }
    
//       _____ _____ _____ _    _ _______ _____ _   _  _____  _____ 
//  / ____|_   _/ ____| |  | |__   __|_   _| \ | |/ ____|/ ____|
// | (___   | || |  __| |__| |  | |    | | |  \| | |  __| (___  
//  \___ \  | || | |_ |  __  |  | |    | | | . ` | | |_ |\___ \ 
//  ____) |_| || |__| | |  | |  | |   _| |_| |\  | |__| |____) |
// |_____/|_____\_____|_|  |_|  |_|  |_____|_| \_|\_____|_____/ 
//                                                              
//     

    @Override
    public List<Sighting> getAllSightings() {
        return dao.getAllSightings();
    }

    @Override
    public List<Sighting> getSightingsByHeroId(Integer id) {
        return dao.getSightingsByHeroId(id);
    }

    @Override
    public List<Sighting> getSightingsByLocationId(Integer id) {
        return dao.getSightingsByLocationId(id);
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        List<Sighting> allSightings = dao.getAllSightings();
        List<Sighting> filtered = new ArrayList<>();
        for(Sighting s : allSightings){
            if(s.getDate().toLocalDate().equals(date)){
                filtered.add(s);
            }
        }
        return filtered;
    }
    
    @Override
    public List<Sighting> getRecentSightings(){
        return dao.getRecentSightings();
    }

    @Override
    public void addSighting(Sighting sighting) {
        dao.addSighting(sighting);
    }

    @Override
    public void deleteSighting(Integer id) {
        dao.deleteSighting(id);
    }
    
}
