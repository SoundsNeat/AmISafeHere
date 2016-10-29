package edu.csupomona.cs480.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Jonathan T. Fetzer
 * class: CS 480, Cal Poly Pomona
 * group: Sounds Neat
 *
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
	private int[] numBurgleries;
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
	
	CrimeStats(String city, String state){
		this.city = city;
		this.state = state;
	} // end constructor CrimeStats
	
	public String getCity(){
		return city;
	} // end method getCity
	
	public void setCity(String city){
		this.city = city;
	} // end method setCity
	
	public String getState(){
		return state;
	} // end method getCity
	
	public void setState(String state){
		this.state = state;
	} // end method setCity
	
	public void setCrimeDataYears(int[] crimeDataYears){
		this.crimeDataYears = crimeDataYears;
	} // end method setCrimeDataYears
	
	public int getCrimeDataYear(int index){
		if(index < 0 || index > crimeDataYears.length){
			throw new IndexOutOfBoundsException("index passed: " + index + 
					", must be between 0 and " + crimeDataYears.length + 
					" for " + city + ", " + state);
		} // end if
		return crimeDataYears[index];
	} // end method getDataYear

	public int[] getCrimeDataYears() {
		return crimeDataYears;
	}
	
	public float[] getMurderStats() {
		return murderStats;
	}

	public float getMurderStats(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return murderStats[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setMurderStats(float[] murderStats) {
		this.murderStats = murderStats;
	}

	public float[] getRapeStats() {
		return rapeStats;
	}

	public float getRapeStats(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return rapeStats[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setRapeStats(float[] rapeStats) {
		this.rapeStats = rapeStats;
	}

	public float[] getRobberyStats() {
		return robberyStats;
	}

	public float getRobberyStats(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return robberyStats[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setRobberyStats(float[] robberyStats) {
		this.robberyStats = robberyStats;
	}

	public float[] getAssaultStats() {
		return assaultStats;
	}

	public float getAssaultStats(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return assaultStats[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setAssaultStats(float[] assaultStats) {
		this.assaultStats = assaultStats;
	}

	public float[] getBurglaryStats() {
		return burglaryStats;
	}

	public float getBurglaryStats(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return burglaryStats[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setBurglaryStats(float[] burglaryStats) {
		this.burglaryStats = burglaryStats;
	}

	public float[] getTheftStats() {
		return theftStats;
	}

	public float getTheftStats(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return theftStats[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setTheftStats(float[] theftStats) {
		this.theftStats = theftStats;
	}

	public float[] getAutoTheftStats() {
		return autoTheftStats;
	}

	public float getAutoTheftStats(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return autoTheftStats[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setAutoTheftStats(float[] autoTheftStats) {
		this.autoTheftStats = autoTheftStats;
	}

	public float[] getArsonStats() {
		return arsonStats;
	}

	public float getArsonStats(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return arsonStats[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setArsonStats(float[] arsonStats) {
		this.arsonStats = arsonStats;
	}

	public float[] getCrimeDataIndex() {
		return crimeDataIndex;
	}

	public float getCrimeDataIndex(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return crimeDataIndex[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	public void setCrimeDataIndex(float[] crimeDataIndex) {
		this.crimeDataIndex = crimeDataIndex;
	}
	
	public int getAmISafeIndex() {
		return amISafeIndex;
	}

	public void setAmISafeIndex() {
		// calculate amISafeIndex using crimeDataIndex
	}
	
	public static void print(int[] param){
		int i;
		for (i = 0; i < param.length - 1; i++) {
			System.out.print(param[i] + ", ");
		} // end for i
		System.out.println(param[i]);
	}
	
	public static void print(float[] param){
		int i;
		for (i = 0; i < param.length - 1; i++) {
			System.out.print(param[i] + ", ");
		} // end for i
		System.out.println(param[i]);
	}
	
	public int[] getNumMurders() {
		return numMurders;
	}
	
	public int getNumMurders(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return numMurders[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}

	public void setNumMurders(int[] numMurders) {
		this.numMurders = numMurders;
	}

	public int[] getNumRapes() {
		return numRapes;
	}

	public int getNumRapes(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return numRapes[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setNumRapes(int[] numRapes) {
		this.numRapes = numRapes;
	}

	public int[] getNumRobberies() {
		return numRobberies;
	}

	public int getNumRobberies(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return numRobberies[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setNumRobberies(int[] numRobberies) {
		this.numRobberies = numRobberies;
	}

	public int[] getNumAssaults() {
		return numAssaults;
	}

	public int getNumAssaults(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return numAssaults[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setNumAssaults(int[] numAssaults) {
		this.numAssaults = numAssaults;
	}

	public int[] getNumBurglaries() {
		return numBurgleries;
	}

	public int getNumBurglaries(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return numBurgleries[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setNumBurglaries(int[] numBurgleries) {
		this.numBurgleries = numBurgleries;
	}

	public int[] getNumThefts() {
		return numThefts;
	}

	public int getNumThefts(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return numThefts[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setNumThefts(int[] numThefts) {
		this.numThefts = numThefts;
	}

	public int[] getNumAutoThefts() {
		return numAutoThefts;
	}

	public int getNumAutoThefts(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return numAutoThefts[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setNumAutoThefts(int[] numAutoThefts) {
		this.numAutoThefts = numAutoThefts;
	}

	public int[] getNumArsons() {
		return numArsons;
	}

	public int getNumArsons(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataYears[i] == year){
				return numArsons[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setNumArsons(int[] numArsons) {
		this.numArsons = numArsons;
	}
	
	public String toJson(){
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	} // end method toJson
}
