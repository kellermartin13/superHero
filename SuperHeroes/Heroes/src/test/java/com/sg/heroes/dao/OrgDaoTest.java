/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.dao;

import com.sg.heroes.model.Hero;
import com.sg.heroes.model.Organization;
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
public class OrgDaoTest {
    private HeroesDao dao;

    public OrgDaoTest() {
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
        
        forCleanup.execute("INSERT INTO Organizations VALUES (1, 1, "
                + "'The Justice League', 'I do not know')");
        forCleanup.execute("INSERT INTO Organizations VALUES (2, 2, "
                + "'DC', 'I do not know')");
        
        forCleanup.execute("INSERT INTO HeroOrgs VALUES (1, 1)");
        forCleanup.execute("INSERT INTO HeroOrgs VALUES (2, 2)");
        
        
    }
    
    @After
    public void tearDown() {

    }
    
    @Test
    public void addGetOrg(){
        Organization newOrg = new Organization();
        newOrg.setOrgId(3);
        newOrg.setLocation(dao.getLocationById(1));
        newOrg.setName("School");
        newOrg.setDescription("School");
        List<Hero> heroes = dao.getAllHeroes();
        newOrg.setHeroes(heroes);
        dao.addOrg(newOrg);
        Integer id = newOrg.getOrgId();
        Organization test = dao.getOrgById(id);
        assertEquals(test.getName(), "School");
    }
    
    @Test
    public void deleteGetAllOrg(){
        Organization toBeDeleted = dao.getOrgById(2);
        assertEquals(2, dao.getAllOrgs().size());
        dao.deleteOrg(2);
        assertEquals(1, dao.getAllOrgs().size());
    }
    
    @Test
    public void updateOrg(){
        Organization toBeUpdated = dao.getOrgById(2);
        toBeUpdated.setName("Evil Alliance");
        toBeUpdated.setDescription("All the villains say yeah");
        dao.updateOrg(toBeUpdated);
        Organization updated = dao.getOrgById(2);
        assertEquals(updated.getName(), "Evil Alliance");
    }
    
    @Test
    public void getOrgsByHeroId(){
        List<Organization> orgs = dao.getOrgsByHeroId(1);
        Organization shouldBeJustice = orgs.get(0);
        assertEquals(shouldBeJustice.getName(), "The Justice League");
        
    }
    
    @Test
    public void getOrgsByLocationId(){
        List<Organization> orgs = dao.getOrgsByLocationId(1);
        Organization shouldBeJustice = orgs.get(0);
        assertEquals(shouldBeJustice.getName(), "The Justice League");
    }
}
