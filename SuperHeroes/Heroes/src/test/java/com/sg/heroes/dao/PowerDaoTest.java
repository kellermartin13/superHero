/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.dao;

import com.sg.heroes.model.Power;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
public class PowerDaoTest {

    private HeroesDao dao;

    public PowerDaoTest() {
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
    }

    @After
    public void tearDown() {

    }

    @Test
    public void getPowerByIdUpdate() {
        Power testPower = dao.getPowerById(1);
        assertNotNull(testPower);
        assertEquals(new Integer(1), testPower.getPowerId());
        assertEquals("Lightning Speed", testPower.getName());
        assertEquals("He fast", testPower.getDescription());
        
        testPower.setName("Flying");
        testPower.setDescription("Like levitation, "
                + "but can go anywhere in the air.");
        testPower = dao.updatePower(testPower);
        assertNotNull(testPower);
        assertEquals(new Integer(1), testPower.getPowerId());
        assertEquals("Flying", testPower.getName());
        assertEquals("Like levitation, "
                + "but can go anywhere in the air.", testPower.getDescription());
    }

    @Test
    public void addPower() {
        Power newPower = new Power();
        newPower.setName("Flying");
        newPower.setDescription("Like levitation, "
                + "but can go anywhere in the air.");
        dao.addPower(newPower);
        Integer id = newPower.getPowerId();
        assertNotNull(id);
        Power test = dao.getPowerById(id);
        assertNotNull(test);
        assertEquals("Flying", test.getName());
        assertEquals("Like levitation, but can go anywhere in the air.",
                test.getDescription());
    }

    @Test
    public void deletePower() {
        Power toBeDeleted = dao.getPowerById(1);
        assertEquals(toBeDeleted.getPowerId(), new Integer(1));

        dao.deletePower(1);
        List<Power> powers = dao.getAllPowers();
        assertEquals(powers.size(), 0);
    }
    
    @Test
    public void getAllPowers() {
        List<Power> powers = dao.getAllPowers();
        assertEquals(powers.size(), 1);
        assertEquals(powers.get(0).getName(), "Lightning Speed");
        
        Power newPower = new Power();
        newPower.setName("Flying");
        newPower.setDescription("Like levitation, "
                + "but can go anywhere in the air.");
        dao.addPower(newPower);
        Integer id = newPower.getPowerId();
        powers = dao.getAllPowers();
        assertEquals(powers.size(), 2);
        assertEquals(powers.get(0).getPowerId(), new Integer(1));
        assertEquals(powers.get(1).getPowerId(), id);
    }

}
