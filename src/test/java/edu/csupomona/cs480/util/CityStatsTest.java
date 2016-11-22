package edu.csupomona.cs480.util;

import edu.csupomona.cs480.util.CrimeStats.CityStats;
import edu.csupomona.cs480.util.CrimeStats.CityStatsManager;
import edu.csupomona.cs480.util.CrimeStats.ICityStatsManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Connor A. Haskins
 *         class: CS 480, Cal Poly Pomona
 *         group: Sounds Neat
 */

public class CityStatsTest {
    ICityStatsManager ccs;
    @Before
    public void init() {
        ccs = new CityStatsManager();
    }

    @Test
    public void CreateCrimeStatsTest1() throws IOException {
        ccs.execute("Pomona", "California");
        CityStats city = ccs.getCityStats();

        Assert.assertTrue(city.getCity().equals("Pomona"));
        Assert.assertEquals(12.5, city.getCrimeStat(CityStats.ARSON_INDEX).getPerHundredThousand(city.getYearIndex(2012)), 0.1);
        Assert.assertEquals(847, city.getCrimeStat(CityStats.BURGLARY_INDEX).getTotalCrimes(city.getYearIndex(2013)), 0);
        Assert.assertEquals(29.0, city.getCrimeStat(CityStats.RAPE_INDEX).getPerHundredThousand(city.getYearIndex(2014)), 0.1);
    }

    @Test
    public void CreateCrimeStatsTest2() throws IOException {
        ccs.execute("Lake Elsinore", "California");
        CityStats city = ccs.getCityStats();

        Assert.assertTrue(city.getCity().equals("Lake Elsinore"));
        Assert.assertEquals(3, city.getCrimeStat(CityStats.ARSON_INDEX).getTotalCrimes(city.getYearIndex(2004)), 0);
        Assert.assertEquals(856.3, city.getCrimeStat(CityStats.BURGLARY_INDEX).getPerHundredThousand(city.getYearIndex(2005)), 0.1);
        Assert.assertEquals(13, city.getCrimeStat(CityStats.RAPE_INDEX).getTotalCrimes(city.getYearIndex(2006)), 0);
    }

    @Test
    public void CreateCrimeStatsTest3() throws IOException {
        ccs.execute("New York", "New York");
        CityStats city = ccs.getCityStats();

        Assert.assertTrue(city.getCity().equals("New York"));
        Assert.assertEquals(98.8, city.getCrimeStat(CityStats.AUTO_THEFT_INDEX).getPerHundredThousand(city.getYearIndex(2012)), 0.1);
        Assert.assertEquals(335, city.getCrimeStat(CityStats.MURDER_INDEX).getTotalCrimes(city.getYearIndex(2013)), 0);
        Assert.assertEquals(-1, city.getCrimeStat(CityStats.ARSON_INDEX).getTotalCrimes(city.getYearIndex(2014)), 0);
    }

    @Test
    public void SafeIndex1LowerBound() throws IOException {
        ccs.execute("Virginia Beach", "Virginia");
        CityStats city = ccs.getCityStats();
        Assert.assertEquals(1, city.getAmISafeIndex(), 0);
    }

    @Test
    public void SafeIndex1UpperBound() throws IOException {
        ccs.execute("Austin", "Texas");
        CityStats city = ccs.getCityStats();
        Assert.assertEquals(1, city.getAmISafeIndex(), 0);
    }

    @Test
    public void SafeIndex2LowerBound() throws IOException {
        ccs.execute("Aurora", "Colorado");
        CityStats city = ccs.getCityStats();
        Assert.assertEquals(2, city.getAmISafeIndex(), 0);
    }

    @Test
    public void SafeIndex2UpperBound() throws IOException {
        ccs.execute("Pittsburgh", "Pennsylvania");
        CityStats city = ccs.getCityStats();
        Assert.assertEquals(2, city.getAmISafeIndex(), 0);
    }

    @Test
    public void SafeIndex3LowerBound() throws IOException {
        ccs.execute("Tulsa", "Oklahoma");
        CityStats city = ccs.getCityStats();
        Assert.assertEquals(3, city.getAmISafeIndex(), 0);
    }

    @Test
    public void SafeIndex3UpperBound() throws IOException {
        ccs.execute("Washington", "District of Columbia");
        CityStats city = ccs.getCityStats();
        Assert.assertEquals(3, city.getAmISafeIndex(), 0);
    }

    @Test
    public void SafeIndex4LowerBound() throws IOException {
        ccs.execute("Atlanta", "Georgia");
        CityStats city = ccs.getCityStats();
        Assert.assertEquals(4, city.getAmISafeIndex(), 0);
    }

    @Test
    public void SafeIndex4UpperBound() throws IOException {
        ccs.execute("Memphis", "Tennessee");
        CityStats city = ccs.getCityStats();
        Assert.assertEquals(4, city.getAmISafeIndex(), 0);
    }

    @Test
    public void SafeIndex5LowerBound() throws IOException {
        ccs.execute("Detroit", "Michigan");
        CityStats city = ccs.getCityStats();
        Assert.assertEquals(5, city.getAmISafeIndex(), 0);
    }

    @Test
    public void SafeIndex5UpperBound() throws IOException {
        ccs.execute("St. Louis", "Missouri");
        CityStats city = ccs.getCityStats();
        Assert.assertEquals(5, city.getAmISafeIndex(), 0);
    }

    @Test
    public void CreateCrimeStatsToJSON1() throws IOException {
        ccs.execute("Pomona", "California");
        CityStats city = ccs.getCityStats();

        String jsonString = city.toJson();
        StringBuilder correctJSONFormat = new StringBuilder();
        correctJSONFormat.append("{\"city\":\"Pomona\",\"state\":\"California\",\"success\":true,\"amISafeIndex\":2,\"crimeStats\":[");
        correctJSONFormat.append("{\"type\":\"murder\",\"totalCrimes\":[18,17,21,21,19,27,20,17,16,11,17,29,18],\"perHundredThousand\":[11.6,11.0,13.5,13.4,12.2,17.4,13.1,11.1,10.5,7.3,11.2,19.2,11.8]},");
        correctJSONFormat.append("{\"type\":\"rape\",\"totalCrimes\":[77,49,50,44,17,32,37,46,37,54,63,31,44],\"perHundredThousand\":[49.7,31.6,32.1,28.1,11.0,20.6,24.2,30.0,24.2,35.8,41.6,20.5,29.0]},");
        correctJSONFormat.append("{\"type\":\"robbery\",\"totalCrimes\":[448,422,349,385,464,485,463,344,323,339,377,295,241],\"perHundredThousand\":[289.1,272.0,223.8,246.0,299.0,312.6,302.2,224.5,211.6,224.8,248.8,194.9,158.7]},");
        correctJSONFormat.append("{\"type\":\"assault\",\"totalCrimes\":[805,729,784,785,755,691,638,561,501,523,564,454,474],\"perHundredThousand\":[519.5,469.8,502.8,501.7,486.6,445.3,416.4,366.1,328.2,346.8,372.3,299.9,312.0]},");
        correctJSONFormat.append("{\"type\":\"burglary\",\"totalCrimes\":[886,914,1013,997,1027,972,1101,854,867,890,901,847,677],\"perHundredThousand\":[571.7,589.0,649.7,637.1,661.8,626.4,718.7,557.4,567.9,590.1,594.7,559.6,445.7]},");
        correctJSONFormat.append("{\"type\":\"theft\",\"totalCrimes\":[2681,2905,2630,2775,2781,3085,2663,2273,2406,2578,3044,2591,2686],\"perHundredThousand\":[1730.1,1872.2,1686.7,1773.4,1792.2,1988.3,1738.2,1483.5,1575.9,1709.4,2009.1,1711.7,1768.3]},");
        correctJSONFormat.append("{\"type\":\"autoTheft\",\"totalCrimes\":[1217,1640,1543,1536,1419,1154,1175,1007,1231,976,1110,956,1040],\"perHundredThousand\":[785.3,1056.9,989.6,981.6,914.5,743.7,767.0,657.2,806.3,647.2,732.6,631.6,684.7]},");
        correctJSONFormat.append("{\"type\":\"arson\",\"totalCrimes\":[28,30,19,25,22,22,22,39,16,17,19,17,17],\"perHundredThousand\":[18.1,19.3,12.2,16.0,14.2,14.2,14.4,25.5,10.5,11.3,12.5,11.2,11.2]}],");
        correctJSONFormat.append("\"crimeDataYears\":[2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014]}");
        String correctJSONString = correctJSONFormat.toString();

        Assert.assertEquals(jsonString.compareTo(correctJSONString), 0, 0);
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
