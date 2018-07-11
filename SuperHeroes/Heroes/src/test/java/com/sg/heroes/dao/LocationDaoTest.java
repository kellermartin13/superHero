/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.dao;

import com.sg.heroes.model.Location;
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
public class LocationDaoTest {
    private HeroesDao dao;

    public LocationDaoTest() {
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
                + "'Place to learn things', '252 E Market St', 'Louisville', 'United States', '`38.2540652`', "
                + "'`-85.74835840`')");
        System.out.println("");
    }
    

    @After
    public void tearDown() {

    }

    @Test
    public void getLocationByIdUpdate() {
        Location testLocation = dao.getLocationById(1);
        assertNotNull(testLocation);
        assertEquals(new Integer(1), testLocation.getLocationId());
        assertEquals("The Software Guild", testLocation.getName());
        assertEquals("Place to learn things", testLocation.getDescription());
        assertEquals("252 E Market St", testLocation.getAddress());
        assertEquals("Louisville", testLocation.getCity());
        
        testLocation.setName("Wayne Enterprise");
        testLocation.setDescription("The Dark Tower");
        testLocation = dao.updateLocation(testLocation);
        assertNotNull(testLocation);
        assertEquals(new Integer(1), testLocation.getLocationId());
        assertEquals("Wayne Enterprise", testLocation.getName());
        assertEquals("The Dark Tower", testLocation.getDescription());
    }

    @Test
    public void addLocation() {
        Location newLocation = new Location();
        newLocation.setName("Wayne Enterprise");
        newLocation.setDescription("The Dark Tower");
        newLocation.setAddress("321 Gotham Way");
        newLocation.setCity("Gotham");
        newLocation.setCountry("United States");
        newLocation.setLatitude("94.678");
        newLocation.setLongitude("-87.64738");
        dao.addLocation(newLocation);
        Integer id = newLocation.getLocationId();
        assertNotNull(id);
        Location test = dao.getLocationById(id);
        assertNotNull(test);
        assertEquals("Wayne Enterprise", test.getName());
        assertEquals("The Dark Tower",
                test.getDescription());
    }

    @Test
    public void deleteLocation() {
        Location toBeDeleted = dao.getLocationById(1);
        assertEquals(toBeDeleted.getLocationId(), new Integer(1));

        dao.deleteLocation(1);
        List<Location> locations = dao.getAllLocations();
        assertEquals(locations.size(), 0);
    }
    
    @Test
    public void getAllLocations() {
        List<Location> locations = dao.getAllLocations();
        assertEquals(locations.size(), 1);
        assertEquals(locations.get(0).getName(), "The Software Guild");
        
        Location newLocation = new Location();
        newLocation.setName("Wayne Enterprise");
        newLocation.setDescription("The Dark Tower");
        newLocation.setAddress("321 Gotham Way");
        newLocation.setCity("Gotham");
        newLocation.setCountry("United States");
        newLocation.setLatitude("94.678");
        newLocation.setLongitude("-87.64738");
        dao.addLocation(newLocation);
        Integer id = newLocation.getLocationId();
        locations = dao.getAllLocations();
        assertEquals(locations.size(), 2);
        assertEquals(locations.get(0).getLocationId(), new Integer(1));
        assertEquals(locations.get(1).getLocationId(), id);
    }
}
