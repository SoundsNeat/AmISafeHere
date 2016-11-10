package edu.csupomona.cs480.util;

import edu.csupomona.cs480.util.CrimeStats.CreateCrimeStats;
import edu.csupomona.cs480.util.CrimeStats.CrimeStats;
import edu.csupomona.cs480.util.CrimeStats.ICreateCrimeStats;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Connor A. Haskins
 *         class: CS 480, Cal Poly Pomona
 *         group: Sounds Neat
 */

public class CrimeStatsTest {
    ICreateCrimeStats ccs;
    @Before
    public void init() {
        ccs = new CreateCrimeStats();
    }

    @Test
    public void CreateCrimeStatsTest1() throws IOException {
        ccs.execute("Pomona", "California");
        CrimeStats city = ccs.getCrimeStats();

        Assert.assertTrue(city.getCity().equals("Pomona"));
        Assert.assertEquals(12.5, city.getArsonStats(2012), 0.1);
        Assert.assertEquals(847, city.getNumBurglaries(2013), 0);
        Assert.assertEquals(29.0, city.getRapeStats(2014), 0.1);

        // uncomment if you want to inspect the CrimeStats object manually during test
        //System.out.println("JSON: " + city.toJson()+ "\n");
    }

    @Test
    public void CreateCrimeStatsTest2() throws IOException {
        ccs.execute("Lake Elsinore", "California");
        CrimeStats city = ccs.getCrimeStats();

        Assert.assertTrue(city.getCity().equals("Lake Elsinore"));
        Assert.assertEquals(3, city.getNumArsons(2004), 0);
        Assert.assertEquals(856.3, city.getBurglaryStats(2005), 0.1);
        Assert.assertEquals(13, city.getNumRapes(2006), 0);
    }

    @Test
    public void CreateCrimeStatsTest3() throws IOException {
        ccs.execute("New York", "New York");
        CrimeStats city = ccs.getCrimeStats();

        Assert.assertTrue(city.getCity().equals("New York"));
        Assert.assertEquals(98.8, city.getAutoTheftStats(2012), 0.1);
        Assert.assertEquals(335, city.getNumMurders(2013), 0);
        Assert.assertEquals(-1, city.getNumArsons(2014));
    }

    @Test(expected = RuntimeException.class)
    public void nullCreateCrimeStatsTest() throws IOException {
        ccs.execute(null, null);
    }

    @Test(expected = RuntimeException.class)
    public void nullCityCreateCrimeStatsTest() throws IOException {
        ccs.execute(null, "CA");
    }

    @Test(expected = RuntimeException.class)
    public void nullStateCreateCrimeStatsTest() throws IOException {
        ccs.execute("Pomona", null);
    }

    @Test(expected = RuntimeException.class)
    public void emptyCreateCrimeStatsTest() throws IOException {
        ccs.execute("", "");
    }

    @Test(expected = RuntimeException.class)
    public void emptyCityCreateCrimeStatsTest() throws IOException {
        ccs.execute("", "CA");
    }

    @Test(expected = RuntimeException.class)
    public void emptyStateCreateCrimeStatsTest() throws IOException {
        ccs.execute("Pomona", "");
    }

    @Test(expected = RuntimeException.class)
    public void spaceCreateCrimeStatsTest() throws IOException {
        ccs.execute("  ", "  ");
    }

    @Test(expected = RuntimeException.class)
    public void spaceCityCreateCrimeStatsTest() throws IOException {
        ccs.execute("  ", "CA");
    }

    @Test(expected = RuntimeException.class)
    public void spaceStateCreateCrimeStatsTest() throws IOException {
        ccs.execute("Pomona", "  ");
    }
}