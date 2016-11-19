package edu.csupomona.cs480.util.CrimeStats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class used to hold all of the crime statistics of a city
 * @author Jonathan T. Fetzer
 * clean-up/documentation: Connor A. Haskins
 * class: CS 480, Cal Poly Pomona
 * group: Sounds Neat
 */
public class CityStats {

    private static final int MURDER_WEIGHT = 10;
    private static final int RAPE_WEIGHT = 8;
    private static final int ROBBERY_WEIGHT = 2;
    private static final int ASSAULT_WEIGHT = 5;
    private static final int BURGLARY_WEIGHT = 3;
    private static final int THEFT_WEIGHT = 1;
    private static final int AUTO_THEFT_WEIGHT = 1;
    private static final int ARSON_WEIGHT = 5;

    public static final int MURDER_INDEX = 0;
    public static final int RAPE_INDEX = 1;
    public static final int ROBBERY_INDEX = 2;
    public static final int ASSAULT_INDEX = 3;
    public static final int BURGLARY_INDEX = 4;
    public static final int THEFT_INDEX = 5;
    public static final int AUTO_THEFT_INDEX = 6;
    public static final int ARSON_INDEX = 7;
    public static final int NUM_CATEGORIES = 8;

    private String city;
    private String state;
    private boolean success;
    private int amISafeIndex; // takes on values 1 (very safe), 2 (mostly safe), 3 (somewhat dangerous), and 4 (very dangerous).

    private CrimeStat [] crimeStats;

    private int [] crimeDataYears;
    private float [] crimeDataIndex;

    /**
     * Creates an instance of CityStats with no initialized data
     */
    protected CityStats(){
        crimeStats = new CrimeStat[NUM_CATEGORIES];
        crimeStats[MURDER_INDEX] = new CrimeStat("murder");
        crimeStats[RAPE_INDEX] = new CrimeStat("rape");
        crimeStats[ROBBERY_INDEX] = new CrimeStat("robbery");
        crimeStats[ASSAULT_INDEX] = new CrimeStat("assault");
        crimeStats[BURGLARY_INDEX] = new CrimeStat("burglary");
        crimeStats[THEFT_INDEX] = new CrimeStat("theft");
        crimeStats[AUTO_THEFT_INDEX] = new CrimeStat("autoTheft");
        crimeStats[ARSON_INDEX] = new CrimeStat("arson");
    }

    /**
     * Create an instance of CityStats using city and state name strings
     *
     * @param city
     * @param state
     */
    protected CityStats(String city, String state) {
        this.city = city;
        this.state = state;
        crimeStats = new CrimeStat[NUM_CATEGORIES];
        crimeStats[MURDER_INDEX] = new CrimeStat("murder");
        crimeStats[RAPE_INDEX] = new CrimeStat("rape");
        crimeStats[ROBBERY_INDEX] = new CrimeStat("robbery");
        crimeStats[ASSAULT_INDEX] = new CrimeStat("assault");
        crimeStats[BURGLARY_INDEX] = new CrimeStat("burglary");
        crimeStats[THEFT_INDEX] = new CrimeStat("theft");
        crimeStats[AUTO_THEFT_INDEX] = new CrimeStat("autoTheft");
        crimeStats[ARSON_INDEX] = new CrimeStat("arson");
    }

    /**
     * Getters
     */

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public boolean getSuccess() {
        return success;
    }

    public int getAmISafeIndex() {
        return amISafeIndex;
    }

    public CrimeStat[] getCrimeStats() {
        return crimeStats;
    }

    public CrimeStat getCrimeStat(int index) {
        return crimeStats[index];
    }

    public int getCrimeDataYear(int index) {
        if (index < 0 || index > crimeDataYears.length) {
            throw new IndexOutOfBoundsException("index passed: " + index +
                    ", must be between 0 and " + crimeDataYears.length +
                    " for " + city + ", " + state);
        }
        return crimeDataYears[index];
    }

    public int[] getCrimeDataYears() {
        return crimeDataYears;
    }

    /**
     * Setters
     */

    public void setCrimeDataIndex() {
        this.crimeDataIndex = new float[crimeDataYears.length];
        long crimeIndex;
        int index = 0;
        for (int i = 0; i < crimeDataYears.length; i++) {
            crimeIndex = 0;
            crimeIndex += MURDER_WEIGHT * getCrimeStat(MURDER_INDEX).getPerHundredThousand(i);
            crimeIndex += RAPE_WEIGHT * getCrimeStat(RAPE_INDEX).getPerHundredThousand(i);
            crimeIndex += ROBBERY_WEIGHT * getCrimeStat(ROBBERY_INDEX).getPerHundredThousand(i);
            crimeIndex += ASSAULT_WEIGHT * getCrimeStat(ASSAULT_INDEX).getPerHundredThousand(i);
            crimeIndex += BURGLARY_WEIGHT * getCrimeStat(BURGLARY_INDEX).getPerHundredThousand(i);
            crimeIndex += THEFT_WEIGHT * getCrimeStat(THEFT_INDEX).getPerHundredThousand(i);
            crimeIndex += AUTO_THEFT_WEIGHT * getCrimeStat(AUTO_THEFT_INDEX).getPerHundredThousand(i);
            crimeIndex += ARSON_WEIGHT * getCrimeStat(ARSON_INDEX).getPerHundredThousand(i);
            crimeDataIndex[i] = crimeIndex;
        }
        for (float aCrimeDataIndex : crimeDataIndex) {
            index += aCrimeDataIndex;
        }
        if (index / crimeDataIndex.length >= 700) { // very dangerous
            amISafeIndex = 4;
        } else if (index / crimeDataIndex.length >= 300) { // somewhat dangerous
            amISafeIndex = 3;
        } else if (index / crimeDataIndex.length > 200) { // mostly safe
            amISafeIndex = 2;
        } else { // very safe
            amISafeIndex = 1;
        }
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setCrimeDataYears(int[] crimeDataYears) {
        this.crimeDataYears = crimeDataYears;
    }

    /**
     * fUnction to link the desired year to the proper index of the arrays
     *
     * @param year (ex: 2011)
     * @return an index (ex: 2, to be used for numRobberries[2])
     */
    public int getYearIndex(int year) {
        for (int i = 0; i < crimeDataYears.length; i++) {
            if (crimeDataYears[i] == year) {
                return i;
            }
        }
        return -1; // data for year requested not available
    }

    /**
     * Super cool function that turns the CityStats object into JSON format
     *
     * @return
     */
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.print("Error: Cannot turn CityStats object into JSON");
            e.printStackTrace();
        }
        return jsonString;
    }
}
