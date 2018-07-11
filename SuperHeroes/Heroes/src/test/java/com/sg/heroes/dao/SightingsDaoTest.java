/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.dao;

import com.sg.heroes.model.Hero;
import com.sg.heroes.model.Sighting;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Keller Martin
 */
public class SightingsDaoTest {
    private HeroesDao dao;

    public SightingsDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("heroesDao", HeroesDao.class);

        JdbcTemplate forCleanup = ctx.getBean(JdbcTemplate.class);
        forCleanup.execute("DELETE FROM HeroPowers WHERE 1=1");
        forCleanup.execute("DELETE FROM HeroSightings WHERE 1=1");
        forCleanup.execute("DELETE FROM HeroOrgs WHERE 1=1");
        forCleanup.execute("DELETE FROM Organizations WHERE 1=1");
        forCleanup.execute("DELETE FROM Heroes WHERE 1=1");
        forCleanup.execute("DELETE FROM Sightings WHERE 1=1");
        forCleanup.execute("DELETE FROM Locations WHERE 1=1");
        forCleanup.execute("DELETE FROM Powers WHERE 1=1");

        forCleanup.execute("INSERT INTO Locations VALUES (1, 'The Software Guild', "
                + "'Place to learn things', '252 E Market St', "
                + "'Louisville', 'United States', '`38.2540652`', "
                + "'`-85.74835840`')");
        forCleanup.execute("INSERT INTO Locations VALUES (2, "
                + "'Wayne Enterprise', 'The Dark Tower', 'Gotham', "
                + "'United States', '321 Gotham Way', "
                + "'94.678', '-87.64738')");
        
        forCleanup.execute("INSERT INTO Heroes VALUES (1, 'Superman', "
                + "'Boring')");
        forCleanup.execute("INSERT INTO Heroes VALUES (2, 'Batman', "
                + "'Misses his parents')");
        
        forCleanup.execute("INSERT INTO Sightings VALUES (1, 1, '2018-07-07 12:05:00')");
        forCleanup.execute("INSERT INTO Sightings VALUES (2, 2, '2018-06-06 08:35:00')");
        
        //Saw both at software guild
        forCleanup.execute("INSERT INTO HeroSightings VALUES (1, 1)");
        forCleanup.execute("INSERT INTO HeroSightings VALUES (2, 1)");
        //saw superman at wayne enterprise
        forCleanup.execute("INSERT INTO HeroSightings VALUES (1, 2)");
        
        
    }
    
    @After
    public void tearDown() {

    }
    
    @Test
    public void addGetSighting(){
        Sighting s = new Sighting();
        s.setLocation(dao.getLocationById(2));
        s.setDate(LocalDateTime.now());
        List<Hero> heroes = dao.getAllHeroes();
        s.setHeroes(heroes);
        dao.addSighting(s);
        Integer id = s.getSightingId();
        Sighting test = dao.getSightingById(id);
        assertEquals(test.getHeroes().size(), 2);
        
        
    }
    
    @Test
    public void deleteSighting(){
        assertEquals(2, dao.getAllSightings().size());
        dao.deleteSighting(2);
        assertEquals(1, dao.getAllSightings().size());
    }
    
    @Test
    public void updateSighting(){
        
    }
    
    @Test
    public void getAllSightings(){
        Sighting toBeUpdated = dao.getSightingById(2);
        toBeUpdated.setDate(LocalDateTime.now());
        dao.updateSighting(toBeUpdated);
        Sighting updated = dao.getSightingById(2);
        assertNotEquals(updated, toBeUpdated);
    }
    
    @Test
    public void getSightingsByHeroId(){
        List<Sighting> sList = dao.getSightingsByHeroId(1);
        assertEquals(2, sList.size());
        sList = dao.getSightingsByHeroId(2);
        assertEquals(1, sList.size());
        Sighting s = sList.get(0);
        assertEquals("The Software Guild", s.getLocation().getName());
    }
    
    @Test
    public void getSightingsByLocationId(){
        List<Sighting> sList = dao.getSightingsByLocationId(1);
        List<Hero> hList = sList.get(0).getHeroes();
        assertEquals(2, hList.size());
        sList = dao.getSightingsByLocationId(2);
        hList = sList.get(0).getHeroes();
        assertEquals(1, hList.size());
        assertEquals("Superman", hList.get(0).getName());
        
    }
}
