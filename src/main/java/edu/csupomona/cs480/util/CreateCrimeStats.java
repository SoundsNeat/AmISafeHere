package edu.csupomona.cs480.util;

import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;

/**
 * @author Jonathan T. Fetzer
 * clean-up/documentation: Connor A. Haskins
 * class: CS 480, Cal Poly Pomona
 * group: Sounds Neat
 */

/**
 *  This class uses Jsoup to fetch and parse html pages.
 *  To use this class, download the Jsoup jar and add it to you java build path.
 *  In Eclipse, right click on project name -> properties -> java build path -> add external jars.
 */
public class CreateCrimeStats{  
	private CrimeStats crimeStats;
    private int [] crimeDataYears;
    private String html;
	
	CreateCrimeStats(String city, String state) throws IOException{
        // create a CrimeStats object for the chosen city
    	crimeStats = new CrimeStats(city, state);

        // ensure a multiple word city is url friendls
    	city = city.replaceAll(" ", "-");
        // get the html page for the desired city
        Document doc = Jsoup.connect("http://www.city-data.com/city/" + city + "-" + state + ".html").get();  
        html = doc.toString();
        // revert the city back to its original value
        city = city.replaceAll("-", " ");

        // iterator we will use to parse through the html String
        int parseIterator = 0;

        //-----------------Parse-crime-data-years-----------------
        int crimeData = html.indexOf("Crime rates in " + city + " by Year") - 10;
        int crimeDataYearsBegin = html.indexOf("\"", crimeData) + 1;
        int crimeDataYearsEnd = html.indexOf("\"", crimeDataYearsBegin );
        int numCrimeDataYears = Integer.parseInt(html.substring(crimeDataYearsBegin, crimeDataYearsEnd)) - 1;
        this.crimeDataYears = new int[numCrimeDataYears];

        int parseYearStart = html.indexOf("</th>", crimeData + 10) + 5;
        for (int i = 0; i < crimeDataYears.length; i++) {
        	parseYearStart = html.indexOf(">", parseYearStart) + 1;
        	parseIterator = html.indexOf("</th>", parseYearStart);
        	crimeDataYears[i] = Integer.parseInt(html.substring(parseYearStart, parseIterator));
//        	System.out.println(crimeDataYears[i]);
        	parseYearStart = parseIterator + 5;
		} // end for i
        crimeStats.setCrimeDataYears(crimeDataYears);

        /**
         * Ask for memory for all of our data
         */
        int [] numMurders = new int[crimeDataYears.length];
        int [] numRapes = new int[crimeDataYears.length];
        int [] numRobberies = new int[crimeDataYears.length];
        int [] numAssaults = new int[crimeDataYears.length];
        int [] numBurglaries = new int[crimeDataYears.length];
        int [] numThefts = new int[crimeDataYears.length];
        int [] numTheftsAuto = new int[crimeDataYears.length];
        int [] numArson = new int[crimeDataYears.length];
        float [] arsonStats = new float[crimeDataYears.length];
        float [] autoStats = new float[crimeDataYears.length];
        float [] theftStats = new float[crimeDataYears.length];
        float [] burglaryStats = new float[crimeDataYears.length];
        float [] assaultStats = new float[crimeDataYears.length];
        float [] robberyStats = new float[crimeDataYears.length];
        float [] rapeStats = new float[crimeDataYears.length];
        float [] murderStats = new float[crimeDataYears.length];

        /**
         * Scrape the page for all crime stats per year and per year per 100,000 citizens
         */

        /**
         * Get murder stats
         */
        parseIterator = this.numPerYear("Murders", parseIterator, numMurders);
        crimeStats.setNumMurders(numMurders);

        parseIterator = this.numPerYearPer100k(parseIterator, murderStats);
        crimeStats.setMurderStats(murderStats);

        /**
         * Get rape stats
         */
        parseIterator = this.numPerYear("Rapes", parseIterator, numRapes);
        crimeStats.setNumRapes(numRapes);

        parseIterator = this.numPerYearPer100k(parseIterator, rapeStats);
        crimeStats.setRapeStats(rapeStats);

        /**
         * Get robbery stats
         */
        parseIterator = this.numPerYear("Robberies", parseIterator, numRobberies);
        crimeStats.setNumRobberies(numRobberies);

        parseIterator = this.numPerYearPer100k(parseIterator, robberyStats);
        crimeStats.setRobberyStats(robberyStats);

        /**
         * Get assult stats
         */
        parseIterator = this.numPerYear("Assaults", parseIterator, numAssaults);
        crimeStats.setNumAssaults(numAssaults);

        parseIterator = this.numPerYearPer100k(parseIterator, assaultStats);
        crimeStats.setAssaultStats(assaultStats);

        /**
         * Get burglary stats
         */
        parseIterator = this.numPerYear("Burglaries", parseIterator, numBurglaries);
        crimeStats.setNumBurglaries(numBurglaries);

        parseIterator = this.numPerYearPer100k(parseIterator, burglaryStats);
        crimeStats.setBurglaryStats(burglaryStats);

        /**
         * Get theft stats
         */
        parseIterator = this.numPerYear("Thefts", parseIterator, numThefts);
        crimeStats.setNumThefts(numThefts);

        parseIterator = this.numPerYearPer100k(parseIterator, theftStats);
        crimeStats.setTheftStats(theftStats);

        /**
         * Get auto theft stats
         */
        parseIterator = this.numPerYear("Auto", parseIterator, numTheftsAuto);
        crimeStats.setNumAutoThefts(numTheftsAuto);

        parseIterator = this.numPerYearPer100k(parseIterator, autoStats);
        crimeStats.setAutoTheftStats(autoStats);

        /**
         * Get arson stats
         */
        parseIterator = this.numPerYear("Arson", parseIterator, numArson);
        crimeStats.setNumArsons(numArson);

        this.numPerYearPer100k(parseIterator, arsonStats);
        crimeStats.setArsonStats(arsonStats);
    }

    /**
     * !IMPORTANT! The following two functions are very specific to the html page we are scraping
     */

    /**
     * Get the number of crimes in of the city for each year of available data
     * @param crime String representing the crime to be scraped
     * @param prevParseEnd the current state of the parse iterator used to scrape the page
     * @param numCrimes an array used to return the crime data values
     * @return the new state of the parse iterator
     */
    public int numPerYear(String crime, int prevParseEnd, int [] numCrimes) {
        int startData = html.indexOf(crime, prevParseEnd);
        int endData = startData;
        int data = 0;

        for (int i = 0; i < crimeDataYears.length; i++) {
            startData = html.indexOf("<td>", startData) + 4;
            endData = html.indexOf("</td>", startData);
            data = Integer.parseInt(html.substring(startData, endData).replaceAll(",",""));
            numCrimes[i] = data;
        } // end for i
        return endData;
    }

    /**
     * Get the number of crimes in of the city for each year of available data per 100,000 citizens
     * @param prevParseEnd the current state of the parse iterator used to scrape the page
     * @param stats an array used to return the crime data values
     * @return the new state of the parse iterator
     */
    public int numPerYearPer100k(int prevParseEnd, float [] stats) {
        int startStats = html.indexOf("</td>", prevParseEnd + 5);
        int endStats = 0;
        float murderStat;

        for (int i = 0; i < crimeDataYears.length; i++) {
            startStats = html.indexOf("<td>", startStats) + 4;
            endStats = html.indexOf("</td>", startStats);
            murderStat = Float.parseFloat(html.substring(startStats, endStats).replaceAll(",",""));
            stats[i] = murderStat;
        } // end for i
        return endStats;
    }

    /**
     * Getters
     */

    public CrimeStats getCrimeStats() {
        return crimeStats;
    }

    public int[] getCrimeDataYears() {
        return crimeDataYears;
    }
}