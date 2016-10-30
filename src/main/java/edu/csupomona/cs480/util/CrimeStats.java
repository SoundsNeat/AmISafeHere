package edu.csupomona.cs480.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Jonathan T. Fetzer
 * clean-up/documentation: Connor A. Haskins
 * class: CS 480, Cal Poly Pomona
 * group: Sounds Neat
 */

/**
 * Class used to hold all of the crime statistics of a city
 */
public class CrimeStats {

    private String city;
    private String state;

    private int amISafeIndex; // takes on values 1 through 5 with 3 being the baseline (e.g. state or national average).

    private int[] crimeDataYears;
    private int[] numMurders;
    private int[] numRapes;
    private int[] numRobberies;
    private int[] numAssaults;
    private int[] numBurglaries;
    private int[] numThefts;
    private int[] numAutoThefts;
    private int[] numArsons;

    private float[] murderStats;
    private float[] rapeStats;
    private float[] robberyStats;
    private float[] assaultStats;
    private float[] burglaryStats;
    private float[] theftStats;
    private float[] autoTheftStats;
    private float[] arsonStats;
    private float[] crimeDataIndex;

    /**
     * Create an instance of CrimeState using city and state name strings
     * @param city
     * @param state
     */
    CrimeStats(String city, String state) {
        this.city = city;
        this.state = state;
    } // end constructor CrimeStats

    /**
     * Getters
     */

    public int getCrimeDataYear(int index) {
        if (index < 0 || index > crimeDataYears.length) {
            throw new IndexOutOfBoundsException("index passed: " + index +
                    ", must be between 0 and " + crimeDataYears.length +
                    " for " + city + ", " + state);
        }
        return crimeDataYears[index];
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int[] getCrimeDataYears() {
        return crimeDataYears;
    }

    public float[] getMurderStats() {
        return murderStats;
    }

    public float[] getRapeStats() {
        return rapeStats;
    }

    public float[] getRobberyStats() {
        return robberyStats;
    }

    public float[] getAssaultStats() {
        return assaultStats;
    }

    public float[] getBurglaryStats() {
        return burglaryStats;
    }

    public float[] getTheftStats() {
        return theftStats;
    }

    public float[] getAutoTheftStats() {
        return autoTheftStats;
    }

    public float[] getArsonStats() {
        return arsonStats;
    }

    public float[] getCrimeDataIndex() {
        return crimeDataIndex;
    }

    public int getAmISafeIndex() {
        return amISafeIndex;
    }

    public int[] getNumMurders() {
        return numMurders;
    }

    public int[] getNumRapes() {
        return numRapes;
    }

    public int[] getNumRobberies() {
        return numRobberies;
    }

    public int[] getNumAssaults() {
        return numAssaults;
    }

    public int[] getNumBurglaries() {
        return numBurglaries;
    }

    public int[] getNumThefts() {
        return numThefts;
    }

    public int[] getNumAutoThefts() {
        return numAutoThefts;
    }

    public int[] getNumArsons() {
        return numArsons;
    }

    public float getMurderStats(int year) {
        return murderStats[getYearIndex(year)];
    }

    public float getRapeStats(int year) {
        return rapeStats[getYearIndex(year)];
    }

    public float getRobberyStats(int year) {
        return robberyStats[getYearIndex(year)];
    }

    public float getAssaultStats(int year) {
        return assaultStats[getYearIndex(year)];
    }

    public float getBurglaryStats(int year) {
        return burglaryStats[getYearIndex(year)];
    }

    public float getTheftStats(int year) {
        return theftStats[getYearIndex(year)];
    }

    public float getAutoTheftStats(int year) {
        return autoTheftStats[getYearIndex(year)];
    }

    public float getArsonStats(int year) {
        return arsonStats[getYearIndex(year)];
    }

    public float getCrimeDataIndex(int year) {
        return crimeDataIndex[getYearIndex(year)];
    }

    public int getNumMurders(int year) {
        return numMurders[getYearIndex(year)];
    }

    public int getNumRapes(int year) {
        return numRapes[getYearIndex(year)];
    }

    public int getNumRobberies(int year) {
        return numRobberies[getYearIndex(year)];
    }

    public int getNumAssaults(int year) {
        return numAssaults[getYearIndex(year)];
    }

    public int getNumBurglaries(int year) {
        return numBurglaries[getYearIndex(year)];
    }

    public int getNumThefts(int year) {
        return numThefts[getYearIndex(year)];
    }

    public int getNumAutoThefts(int year) {
        return numAutoThefts[getYearIndex(year)];
    }

    public int getNumArsons(int year) {
        return numArsons[getYearIndex(year)];
    }

    /**
     * Setters
     */

    public void setCrimeDataIndex(float[] crimeDataIndex) {
        this.crimeDataIndex = crimeDataIndex;
    }

    public void setAmISafeIndex() {
        // TODO: calculate amISafeIndex using crimeDataIndex
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setNumArsons(int[] numArsons) {
        this.numArsons = numArsons;
    }

    public void setNumAutoThefts(int[] numAutoThefts) {
        this.numAutoThefts = numAutoThefts;
    }

    public void setNumThefts(int[] numThefts) {
        this.numThefts = numThefts;
    }

    public void setNumBurglaries(int[] numBurgleries) {
        this.numBurglaries = numBurgleries;
    }

    public void setNumAssaults(int[] numAssaults) {
        this.numAssaults = numAssaults;
    }

    public void setCrimeDataYears(int[] crimeDataYears) {
        this.crimeDataYears = crimeDataYears;
    } // end method setCrimeDataYears

    public void setMurderStats(float[] murderStats) {
        this.murderStats = murderStats;
    }

    public void setRapeStats(float[] rapeStats) {
        this.rapeStats = rapeStats;
    }

    public void setRobberyStats(float[] robberyStats) {
        this.robberyStats = robberyStats;
    }

    public void setAssaultStats(float[] assaultStats) {
        this.assaultStats = assaultStats;
    }

    public void setBurglaryStats(float[] burglaryStats) {
        this.burglaryStats = burglaryStats;
    }

    public void setTheftStats(float[] theftStats) {
        this.theftStats = theftStats;
    }

    public void setAutoTheftStats(float[] autoTheftStats) {
        this.autoTheftStats = autoTheftStats;
    }

    public void setArsonStats(float[] arsonStats) {
        this.arsonStats = arsonStats;
    }

    public void setNumMurders(int[] numMurders) {
        this.numMurders = numMurders;
    }

    public void setNumRapes(int[] numRapes) {
        this.numRapes = numRapes;
    }

    public void setNumRobberies(int[] numRobberies) {
        this.numRobberies = numRobberies;
    }

    /**
     * Helper function to link the desired year to the proper index of the arrays
     * @param year (ex: 2011)
     * @return an index (ex: 2, to be used for numRobberries[2])
     */
    private int getYearIndex(int year) {
        for (int i = 0; i < crimeDataYears.length; i++) {
            if (crimeDataYears[i] == year) {
                return i;
            }
        }
        return -1; // data for year requested not available
    }

    /**
     * Super cool function that turns the CrimeStats object into JSON format
     * @return
     */
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonString;
    }
}
