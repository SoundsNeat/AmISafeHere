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

    private static final int MURDER_WEIGHT = 40;
    private static final int RAPE_WEIGHT = 30;
    private static final int ASSAULT_WEIGHT = 10;
    private static final int ROBBERY_WEIGHT = 8;
    private static final int BURGLARY_WEIGHT = 5;
    private static final int AUTO_THEFT_WEIGHT = 5;
    private static final int THEFT_WEIGHT = 2;
    private static final int TOTAL_WEIGHT = MURDER_WEIGHT + RAPE_WEIGHT
            + ASSAULT_WEIGHT + ROBBERY_WEIGHT + BURGLARY_WEIGHT + AUTO_THEFT_WEIGHT + THEFT_WEIGHT;

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
    private int amISafeIndex; // takes on values 1 (very dangerous), through 5 (very safe).

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
     * @param city name of city
     * @param state name of state
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
        float murderPerHundredThousand = 0;
        float rapePerHundredThousand = 0;
        float robberyPerHundredThousand = 0;
        float assaultPerHundredThousand = 0;
        float burglaryPerHundredThousand = 0;
        float theftPerHundredThousand = 0;
        float autoTheftPerHundredThousand = 0;

        for (int i = 0; i < crimeDataYears.length; i++) {
            murderPerHundredThousand += getCrimeStat(MURDER_INDEX).getPerHundredThousand(i);
            rapePerHundredThousand += getCrimeStat(RAPE_INDEX).getPerHundredThousand(i);
            robberyPerHundredThousand += getCrimeStat(ROBBERY_INDEX).getPerHundredThousand(i);
            assaultPerHundredThousand += getCrimeStat(ASSAULT_INDEX).getPerHundredThousand(i);
            burglaryPerHundredThousand += getCrimeStat(BURGLARY_INDEX).getPerHundredThousand(i);
            theftPerHundredThousand += getCrimeStat(THEFT_INDEX).getPerHundredThousand(i);
            autoTheftPerHundredThousand += getCrimeStat(AUTO_THEFT_INDEX).getPerHundredThousand(i);
        }

        // average out the indexes
        murderPerHundredThousand /= crimeDataYears.length;
        rapePerHundredThousand /= crimeDataYears.length;
        robberyPerHundredThousand /= crimeDataYears.length;
        assaultPerHundredThousand /= crimeDataYears.length;
        burglaryPerHundredThousand /= crimeDataYears.length;
        theftPerHundredThousand /= crimeDataYears.length;
        autoTheftPerHundredThousand /= crimeDataYears.length;

        int murderCrimeIndex = calcMurderCrimeIndex(murderPerHundredThousand);
        int rapeCrimeIndex = calcRapeCrimeIndex(rapePerHundredThousand);
        int robberyCrimeIndex = calcRobberyCrimeIndex(robberyPerHundredThousand);
        int assaultCrimeIndex = calcAssaultCrimeIndex(assaultPerHundredThousand);
        int burglaryCrimeIndex = calcBurglaryCrimeIndex(burglaryPerHundredThousand);
        int theftCrimeIndex = calcTheftCrimeIndex(theftPerHundredThousand);
        int autoTheftCrimeIndex = calcAutoTheftCrimeIndex(autoTheftPerHundredThousand);

        int crimeIndex = (murderCrimeIndex * MURDER_WEIGHT) + (rapeCrimeIndex * RAPE_WEIGHT) +
                (robberyCrimeIndex * ROBBERY_WEIGHT) + (assaultCrimeIndex * ASSAULT_WEIGHT) +
                (burglaryCrimeIndex * BURGLARY_WEIGHT) + (theftCrimeIndex * THEFT_WEIGHT) +
                (autoTheftCrimeIndex * AUTO_THEFT_WEIGHT);

        amISafeIndex = Math.round((float) crimeIndex / TOTAL_WEIGHT);

        System.out.println("AmISafeIndex: " + amISafeIndex);
    }

    int calcMurderCrimeIndex(float murderPerHundredThousand) {
        if(murderPerHundredThousand > 32) {
            return 5;
        } else if (murderPerHundredThousand > 24) {
            return 4;
        } else if (murderPerHundredThousand > 16) {
            return 3;
        } else if (murderPerHundredThousand > 8) {
            return 2;
        } else {
            return 1;
        }
    }

    int calcRapeCrimeIndex(float rapePerHundredThousand) {
        if(rapePerHundredThousand > 50) {
            return 5;
        } else if (rapePerHundredThousand > 42) {
            return 4;
        } else if (rapePerHundredThousand > 36) {
            return 3;
        } else if (rapePerHundredThousand > 28) {
            return 2;
        } else {
            return 1;
        }
    }

    int calcRobberyCrimeIndex(float robberyPerHundredThousand) {
        if(robberyPerHundredThousand > 580) {
            return 5;
        } else if (robberyPerHundredThousand > 460) {
            return 4;
        } else if (robberyPerHundredThousand > 340) {
            return 3;
        } else if (robberyPerHundredThousand > 220) {
            return 2;
        } else {
            return 1;
        }
    }

    int calcAssaultCrimeIndex(float assaultPerHundredThousand) {
        if(assaultPerHundredThousand > 1060) {
            return 5;
        } else if (assaultPerHundredThousand > 815) {
            return 4;
        } else if (assaultPerHundredThousand > 570) {
            return 3;
        } else if (assaultPerHundredThousand > 325) {
            return 2;
        } else {
            return 1;
        }
    }

    int calcBurglaryCrimeIndex(float burglaryPerHundredThousand) {
        if(burglaryPerHundredThousand > 1810) {
            return 5;
        } else if (burglaryPerHundredThousand > 1465) {
            return 4;
        } else if (burglaryPerHundredThousand > 1120) {
            return 3;
        } else if (burglaryPerHundredThousand > 775) {
            return 2;
        } else {
            return 1;
        }
    }

    int calcTheftCrimeIndex(float theftPerHundredThousand) {
        if(theftPerHundredThousand > 4905) {
            return 5;
        } else if (theftPerHundredThousand > 4250) {
            return 4;
        } else if (theftPerHundredThousand > 3595) {
            return 3;
        } else if (theftPerHundredThousand > 2940) {
            return 2;
        } else {
            return 1;
        }
    }

    int calcAutoTheftCrimeIndex(float autoTheftPerHundredThousand) {
        if(autoTheftPerHundredThousand > 1640) {
            return 5;
        } else if (autoTheftPerHundredThousand > 1265) {
            return 4;
        } else if (autoTheftPerHundredThousand > 890) {
            return 3;
        } else if (autoTheftPerHundredThousand > 515) {
            return 2;
        } else {
            return 1;
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
     * @return object as JSON string
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
