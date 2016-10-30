package edu.csupomona.cs480.util.CrimeStats;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;

/**
 * @author Jonathan T. Fetzer
 * clean-up/documentation: Connor A. Haskins
 * class: CS 480, Cal Poly Pomona
 * group: Sounds Neat
 */

/**
 *  This class scrapes www.city-data.com for crime statistics based on the specified city and state
 *  Much of the code written in this class are specific to this website
 */
public class CreateCrimeStats{
    // the CrimeStats object to hold the crime statistics
	private CrimeStats crimeStats;
    // the available years or information offered by the www.city-data.com
    private int [] crimeDataYears;
    // number of years available
    private int numYears;
    // the html page to be scraped
    private String html;

    /**
     * A constructor taking the city and state name and acquiring the crime
     * statistics for that city
     * @param city city name
     * @param state state name
     * @throws IOException from the Jsoup.connect function call
     */
	public CreateCrimeStats(String city, String state) throws IOException{
        // create a CrimeStats object for the chosen city
    	crimeStats = new CrimeStats(city, state);

        // ensure a multiple word city is url friendly
    	city = city.replaceAll(" ", "-");
        // ensure a multiple word state is url friendly
        state = state.replaceAll(" ", "-");
        // get the html page for the desired city
        Document doc = Jsoup.connect("http://www.city-data.com/city/" + city + "-" + state + ".html").get();
        html = doc.toString();

        /**
         * iterator we will use to parse through the html
         * we are using an AtomicInteger, so we can essentially pass an int by reference and remember
         * it's value as we scrape through the html
         * We want to start the parsing iterator at the end of  initial scrape
         */
        AtomicInteger parseIterator = new AtomicInteger(this.initScrape());

        /**
         * Scrape the page for all crime stats per year and per year per 100,000 citizens
         * !!IMPORTANT!! The order of these function calls DO matter
         */

        // Get and set murder stats
        crimeStats.setNumMurders(this.numPerYear("Murders", parseIterator));
        crimeStats.setMurderStats(this.numPerYearPer100k(parseIterator));

        // Get and set rape stats
        crimeStats.setNumRapes(this.numPerYear("Rapes", parseIterator));
        crimeStats.setRapeStats(this.numPerYearPer100k(parseIterator));

        // Get and set robbery stats
        crimeStats.setNumRobberies(this.numPerYear("Robberies", parseIterator));
        crimeStats.setRobberyStats(this.numPerYearPer100k(parseIterator));

        // Get and set assault stats
        crimeStats.setNumAssaults(this.numPerYear("Assaults", parseIterator));
        crimeStats.setAssaultStats(this.numPerYearPer100k(parseIterator));

        // Get and set burglary stats
        crimeStats.setNumBurglaries(this.numPerYear("Burglaries", parseIterator));
        crimeStats.setBurglaryStats(this.numPerYearPer100k(parseIterator));

        // Get and set theft stats
        crimeStats.setNumThefts(this.numPerYear("Thefts", parseIterator));
        crimeStats.setTheftStats(this.numPerYearPer100k(parseIterator));

        // Get and set auto theft stats
        crimeStats.setNumAutoThefts(this.numPerYear("Auto", parseIterator));
        crimeStats.setAutoTheftStats(this.numPerYearPer100k(parseIterator));

        // Get and set arson stats
        crimeStats.setNumArsons(this.numPerYear("Arson", parseIterator));
        crimeStats.setArsonStats(this.numPerYearPer100k(parseIterator));
    }

    // !!IMPORTANT!! : The following two functions are very specific to the html page we are scraping

    /**
     * initialize the html scrape by evaluating how many years of data are available
     * set the available years to our crimeStats object
     * @return an int representing the position in which we left off
     */
    private int initScrape() {
        // parse the years of crime data that are available
        int crimeData = html.indexOf("Crime rates in " + crimeStats.getCity() + " by Year") - 10;
        int crimeDataYearsBegin = html.indexOf("\"", crimeData) + 1;
        int crimeDataYearsEnd = html.indexOf("\"", crimeDataYearsBegin );
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
        crimeStats.setCrimeDataYears(crimeDataYears);
        return parseYearEnd;
    }

    /**
     * Get the number of crimes in of the city for each year of available data
     * @param crime String representing the crime to be scraped
     * @param parseIterator the current state of the parse iterator used to scrape the page
     * @return an array used to return the crime data values
     */
    private int [] numPerYear(String crime, AtomicInteger parseIterator) {
        int startData = html.indexOf(crime, parseIterator.intValue());
        int endData = 0;
        int [] numCrimes = new int[this.numYears];
        int data;

        try {
            for (int i = 0; i < this.numYears; i++) {
                startData = html.indexOf("<td>", startData) + 4;
                endData = html.indexOf("</td>", startData);
                data = Integer.parseInt(html.substring(startData, endData).replaceAll(",",""));
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
     * @param parseIterator the current state of the parse iterator used to scrape the page
     * @return an array used to return the crime data values
     */
    private float [] numPerYearPer100k(AtomicInteger parseIterator) {
        int startStats = html.indexOf("</td>", parseIterator.intValue() + 5);
        int endStats = 0;
        float [] stats = new float[this.numYears];
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

    public CrimeStats getCrimeStats() {
        return crimeStats;
    }

    public int [] getCrimeDataYears() {
        return crimeDataYears;
    }
}