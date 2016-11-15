package edu.csupomona.cs480.util.CrimeStats;

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
    private boolean result;
    private int amISafeIndex; // takes on values 1 (very safe), 2 (mostly safe), 3 (somewhat dangerous), and 4 (very dangerous).
   
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

    private int murderWeight = 10;
    private int rapeWeight = 8;
    private int robberyWeight = 2;
    private int assaultWeight = 5;
    private int burglaryWeight = 3;
    private int theftWeight = 1;
    private int autoTheftWeight = 1;
    private int arsonWeight = 3;

    /**
     * Creates an instance of CrimeStats with no initialized data
     */
    public CrimeStats(){

    }

    /**
     * Create an instance of CrimeStats using city and state name strings
     *
     * @param city
     * @param state
     */
    protected CrimeStats(String city, String state) {
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
        if (murderStats != null) {
            return murderStats[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public float getRapeStats(int year) {
        if (rapeStats != null) {
            return rapeStats[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public float getRobberyStats(int year) {
        if (robberyStats != null) {
            return robberyStats[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public float getAssaultStats(int year) {
        if (assaultStats != null) {
            return assaultStats[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public float getBurglaryStats(int year) {
        if (burglaryStats != null) {
            return burglaryStats[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public float getTheftStats(int year) {
        if (theftStats != null) {
            return theftStats[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public float getAutoTheftStats(int year) {
        if (autoTheftStats != null) {
            return autoTheftStats[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public float getArsonStats(int year) {
        if (arsonStats != null) {
            return arsonStats[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public float getCrimeDataIndex(int year) {
        if (crimeDataIndex != null) {
            return crimeDataIndex[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public boolean getResult() {
        return result;
    }

    public int getNumMurders(int year) {
        if (numMurders != null) {
            return numMurders[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public int getNumRapes(int year) {
        if (numRapes != null) {
            return numRapes[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public int getNumRobberies(int year) {
        if (numRobberies != null) {
            return numRobberies[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public int getNumAssaults(int year) {
        if (numAssaults != null) {
            return numAssaults[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public int getNumBurglaries(int year) {
        if (numBurglaries != null) {
            return numBurglaries[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public int getNumThefts(int year) {
        if (numThefts != null) {
            return numThefts[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public int getNumAutoThefts(int year) {
        if (numAutoThefts != null) {
            return numAutoThefts[getYearIndex(year)];
        } else {
            return -1;
        }
    }

    public int getNumArsons(int year) {
        if (numArsons != null) {
            return numArsons[getYearIndex(year)];
        } else {
            return -1;
        }
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
            crimeIndex += murderWeight * getMurderStats(crimeDataYears[i]);
            crimeIndex += rapeWeight * getMurderStats(crimeDataYears[i]);
            crimeIndex += robberyWeight * getMurderStats(crimeDataYears[i]);
            crimeIndex += assaultWeight * getMurderStats(crimeDataYears[i]);
            crimeIndex += burglaryWeight * getMurderStats(crimeDataYears[i]);
            crimeIndex += theftWeight * getMurderStats(crimeDataYears[i]);
            crimeIndex += autoTheftWeight * getMurderStats(crimeDataYears[i]);
            crimeIndex += arsonWeight * getMurderStats(crimeDataYears[i]);
            crimeDataIndex[i] = crimeIndex;
        } // end for i
        for (int i = 0; i < crimeDataIndex.length; i++) {
            index += crimeDataIndex[i];
        } // end for i
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

    public void setResult(final boolean result) {
        this.result = result;
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
     *
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
     *
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
