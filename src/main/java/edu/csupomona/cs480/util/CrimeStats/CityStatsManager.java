package edu.csupomona.cs480.util.CrimeStats;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import edu.csupomona.cs480.util.data.MismatchedData;
import edu.csupomona.cs480.util.data.MismatchedDataLoader;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * This class scrapes www.city-data.com for crime statistics based on the specified city and state
 * <p>
 * Much of the code written in this class is specific to this website
 * <p>
 * @author Jonathan T. Fetzer
 * clean-up/documentation: Connor A. Haskins
 * class: CS 480, Cal Poly Pomona
 * group: Sounds Neat
 */
public class CityStatsManager implements ICityStatsManager {
    private static final Logger LOGGER = Logger.getLogger(CityStatsManager.class);
    // the CityStats object to hold the crime statistics for a single city
    private CityStats cityStats;
    // the available years or information offered by the www.city-data.com
    private int[] crimeDataYears;
    // number of years available
    private int numYears;
    // the html page to be scraped
    private String html;

    /**
     * Takes the city and state name and acquires the crime
     * statistics for that city.
     * <p>
     * NOTE:
     * <p>
     * An AtomicInteger, simulating pass by reference, is used as an iterator to parse the html,
     * allowing this method to retain its place. After parsing, the fields of the CityStats object are initialized.
     * <p>
     * @param city  city name
     * @param state state name
     * @throws IOException from the Jsoup.connect function call
     */
    @Override
    public void execute(String city, String state) throws IOException {
        if (StringUtils.isBlank(city) || StringUtils.isBlank(state)) {
            throw new RuntimeException("City or State must not be blank.");
        }
        for (MismatchedData each : MismatchedDataLoader.getInstance().getMismatchedDataList()) {
            if (each.getTeleportCity().equals(city) && each.getTeleportState().equals(state)) {
                state = each.getDataState();
                city = each.getDataCity();
                break;
            }
        }
        // create a CityStats object for the chosen city
        cityStats = new CityStats(city, state);
        // ensure a multiple word city is url friendly
        city = city.replaceAll(" ", "-");
        // ensure a multiple word state is url friendly
        state = state.replaceAll(" ", "-");
        getHtmlData(city, state);
        AtomicInteger parseIterator = new AtomicInteger(this.initScrape());
        setVariousCrimeCategories(parseIterator);
    }

    /**
     * Scrape the page for all crime stats per year and per year per 100,000 citizens
     * !!IMPORTANT!! The order of these function calls DO matter
     */
    private void setVariousCrimeCategories(AtomicInteger parseIterator) {
        // Get and set murder state
        cityStats.getCrimeStat(CityStats.MURDER_INDEX).setTotalCrimes(this.numPerYear("Murders", parseIterator));
        cityStats.getCrimeStat(CityStats.MURDER_INDEX).setPerHundredThousand(this.numPerYearPer100k(parseIterator));

        // Get and set rape stats
        cityStats.getCrimeStat(CityStats.RAPE_INDEX).setTotalCrimes(this.numPerYear("Rapes", parseIterator));
        cityStats.getCrimeStat(CityStats.RAPE_INDEX).setPerHundredThousand(this.numPerYearPer100k(parseIterator));

        // Get and set robbery stats
        cityStats.getCrimeStat(CityStats.ROBBERY_INDEX).setTotalCrimes(this.numPerYear("Robberies", parseIterator));
        cityStats.getCrimeStat(CityStats.ROBBERY_INDEX).setPerHundredThousand(this.numPerYearPer100k(parseIterator));

        // Get and set assault stats
        cityStats.getCrimeStat(CityStats.ASSAULT_INDEX).setTotalCrimes(this.numPerYear("Assaults", parseIterator));
        cityStats.getCrimeStat(CityStats.ASSAULT_INDEX).setPerHundredThousand(this.numPerYearPer100k(parseIterator));

        // Get and set burglary stats
        cityStats.getCrimeStat(CityStats.BURGLARY_INDEX).setTotalCrimes(this.numPerYear("Burglaries", parseIterator));
        cityStats.getCrimeStat(CityStats.BURGLARY_INDEX).setPerHundredThousand(this.numPerYearPer100k(parseIterator));

        // Get and set theft stats
        cityStats.getCrimeStat(CityStats.THEFT_INDEX).setTotalCrimes(this.numPerYear("Thefts", parseIterator));
        cityStats.getCrimeStat(CityStats.THEFT_INDEX).setPerHundredThousand(this.numPerYearPer100k(parseIterator));

        // Get and set auto theft stats
        cityStats.getCrimeStat(CityStats.AUTO_THEFT_INDEX).setTotalCrimes(this.numPerYear("Auto", parseIterator));
        cityStats.getCrimeStat(CityStats.AUTO_THEFT_INDEX).setPerHundredThousand(this.numPerYearPer100k(parseIterator));

        // Get and set arson stats
        cityStats.getCrimeStat(CityStats.ARSON_INDEX).setTotalCrimes(this.numPerYear("Arson", parseIterator));
        cityStats.getCrimeStat(CityStats.ARSON_INDEX).setPerHundredThousand(this.numPerYearPer100k(parseIterator));

        // set crimeDataIndex, which will internally set amISafeIndex to values 1, 2, 3 or 4.
        cityStats.setCrimeDataIndex();
    }

    /**
     * get the html page for the desired city
     * @param city error checked City Name
     * @param state error checked State Name
     * @throws IOException JSoup requirement
     */
    private void getHtmlData(String city, String state) {
        try {
            String url = "http://www.city-data.com/city/" + city + "-" + state + ".html";
            LOGGER.fatal(url);
            System.out.println(url);
            Document doc = Jsoup.connect(url).get();
            html = doc.toString().replaceAll("N/A", "-0"); // handles cases where there is no data availale
            cityStats.setSuccess(true);
        } catch (IOException e) {
            cityStats.setSuccess(false);
        }

    }

    // !!IMPORTANT!! : The following two functions are very specific to the html page we are scraping

    /**
     * Initializes the html scrape by evaluating how many years of data are available
     * set the available years to our cityStats object
     *
     * @return an int representing the position in which we left off
     */
    private int initScrape() {
        // parse the years of crime data that are available
        int crimeData = html.indexOf("Crime rates in " + cityStats.getCity() + " by Year") - 10;
        int crimeDataYearsBegin = html.indexOf("\"", crimeData) + 1;
        int crimeDataYearsEnd = html.indexOf("\"", crimeDataYearsBegin);
        this.numYears = Integer.parseInt(html.substring(crimeDataYearsBegin, crimeDataYearsEnd)) - 1;
        this.crimeDataYears = new int[this.numYears];

        int parseYearStart = html.indexOf("</th>", crimeData + 10) + 5;
        int parseYearEnd = 0;
        for (int i = 0; i < crimeDataYears.length; i++) {
            parseYearStart = html.indexOf(">", parseYearStart) + 1;
            parseYearEnd = html.indexOf("</th>", parseYearStart);
            crimeDataYears[i] = Integer.parseInt(html.substring(parseYearStart, parseYearEnd));
            parseYearStart = parseYearEnd + 5;
        }
        // set the crime years that are available
        cityStats.setCrimeDataYears(crimeDataYears);
        return parseYearEnd;
    }

    /**
     * Get the number of crimes in of the city for each year of available data
     *
     * @param crime         String representing the crime to be scraped
     * @param parseIterator the current state of the parse iterator used to scrape the page
     * @return an array used to return the crime data values
     */
    private int[] numPerYear(String crime, AtomicInteger parseIterator) {
        int startData = html.indexOf(crime, parseIterator.intValue());
        int endData = 0;
        int[] numCrimes = new int[this.numYears];
        int data;

        try {
            for (int i = 0; i < this.numYears; i++) {
                startData = html.indexOf("<td>", startData) + 4;
                endData = html.indexOf("</td>", startData);
                data = Integer.parseInt(html.substring(startData, endData).replaceAll(",", ""));
                numCrimes[i] = data;
            }
            parseIterator.set(endData);
        } catch (NumberFormatException nfe) {
            numCrimes = null;
        }

        return numCrimes;
    }

    /**
     * Get the number of crimes in of the city for each year of available data per 100,000 citizens
     *
     * @param parseIterator the current state of the parse iterator used to scrape the page
     * @return an array used to return the crime data values
     */
    private float[] numPerYearPer100k(AtomicInteger parseIterator) {
        int startStats = html.indexOf("</td>", parseIterator.intValue() + 5);
        int endStats = 0;
        float[] stats = new float[this.numYears];
        float crimeStat;

        try {
            for (int i = 0; i < this.numYears; i++) {
                startStats = html.indexOf("<td>", startStats) + 4;
                endStats = html.indexOf("</td>", startStats);
                crimeStat = Float.parseFloat(html.substring(startStats, endStats).replaceAll(",", ""));
                stats[i] = crimeStat;
            }
            parseIterator.set(endStats);
        } catch (NumberFormatException nfe) {
            stats = null;
        }

        return stats;
    }

    /**
     * Getters
     */

    public CityStats getCityStats() {
        return cityStats;
    }

    public int[] getCityDataYears() {
        return crimeDataYears;
    }
}
