/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.dao;

import com.sg.heroes.model.Hero;
import com.sg.heroes.model.Power;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
public class HeroDaoTest {
    private HeroesDao dao;

    public HeroDaoTest() {
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

        forCleanup.execute("INSERT INTO Powers VALUES (1, 'Lightning Speed', 'He fast')");
        forCleanup.execute("INSERT INTO Powers VALUES (2, 'Super Strength', 'Really strong')");
        forCleanup.execute("INSERT INTO Powers VALUES (3, 'Extremely Rich and Bored', 'Yup')");
        
        forCleanup.execute("INSERT INTO Heroes VALUES (1, 'Superman', 'Boring')");
        forCleanup.execute("INSERT INTO Heroes VALUES (2, 'Batman', 'Misses his parents')");
        
        forCleanup.execute("INSERT INTO HeroPowers VALUES (1, 1)");
        forCleanup.execute("INSERT INTO HeroPowers VALUES (1, 2)");
        forCleanup.execute("INSERT INTO HeroPowers VALUES (1, 3)");
        forCleanup.execute("INSERT INTO HeroPowers VALUES (2, 3)");
        
        
    }
    

    @After
    public void tearDown() {

    }
    
    @Test
    public void addGetHero(){
        Hero hero = new Hero();
        hero.setName("Spiderman");
        hero.setDescription("Squirrely dude");
        hero.setHeroId(3);
        Power power = new Power();
        power.setName("Spider Web Veins");
        power.setDescription("Shoots webs from his wrists and swings around on them.");
        power.setPowerId(4);
        dao.addPower(power);
        List<Power> powers = new ArrayList<>();
        powers.add(power);
        hero.setPowers(powers);
        dao.addHero(hero);
        
        
        
        Integer id = hero.getHeroId();
        Hero test = dao.getHeroById(id);
        assertEquals(test.getName(), "Spiderman");
        assertEquals(test.getDescription(), "Squirrely dude");
        assertEquals(test.getPowers(), powers);
        
    }
    
    @Test
    public void deleteGetAllHeroes(){
        List<Hero> heroes = dao.getAllHeroes();
        assertEquals(2, heroes.size());
        dao.deleteHero(2);
        heroes = dao.getAllHeroes();
        assertEquals(1, heroes.size());
    }
    
    @Test
    public void updateHero(){
        Hero toBeUpdated = dao.getHeroById(1);
        assertEquals("Superman", toBeUpdated.getName());
        
        toBeUpdated.setName("Stupidman");
        toBeUpdated.setDescription("Dumb disguise");
        dao.updateHero(toBeUpdated);
        Hero updated = dao.getHeroById(1);
        
        assertEquals("Stupidman", updated.getName());
        assertEquals("Dumb disguise", updated.getDescription());
    }
    
    @Test
    public void getHeroesByPowerId(){
        List<Hero> richPower = dao.getHeroesByPowerId(3);
        assertEquals(2, richPower.size());
        List<Hero> strength = dao.getHeroesByPowerId(2);
        assertEquals(1, strength.size());
        Hero shouldBeSuperman = strength.get(0);
        assertEquals(shouldBeSuperman, dao.getHeroById(1));
    }
    
}
