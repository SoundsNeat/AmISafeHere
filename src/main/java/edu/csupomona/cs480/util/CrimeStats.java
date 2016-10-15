package edu.csupomona.cs480.util;
/**
 * 
 * @author Jonathan T. Fetzer
 * class: CS 480, Cal Poly Pomona
 * group: Sounds Neat
 *
 */

public class CrimeStats {	
	
	private float[] murderStats;
	private float[] rapeStats;
	private float[] robberyStats;
	private float[] assaultStats;
	private float[] burglaryStats;
	private float[] theftStats;
	private float[] autoTheftStats;
	private float[] arsonStats;
	private float[] crimeDataIndex;
	
	private int[] crimeDataYears;
	private int[] numMurders;
	private int[] numRapes;
	private int[] numRobberies;
	private int[] numAssaults;
	private int[] numBurgleries;
	private int[] numThefts;
	private int[] numAutoThefts;
	private int[] numArsons;
	
	private String city;
	private String state;
	
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
			if(murderStats[i] == year){
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
			if(rapeStats[i] == year){
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
			if(robberyStats[i] == year){
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
			if(assaultStats[i] == year){
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
			if(burglaryStats[i] == year){
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
			if(theftStats[i] == year){
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
			if(autoTheftStats[i] == year){
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
			if(arsonStats[i] == year){
				return arsonStats[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setArsonStats(float[] arsonStats) {
		this.arsonStats = arsonStats;
	}

	public float[] getCrimeDataStats() {
		return crimeDataIndex;
	}

	public float getCrimeDataIndex(int year) {
		for (int i = 0; i < crimeDataYears.length; i++) {
			if(crimeDataIndex[i] == year){
				return crimeDataIndex[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	public void setCrimeDataIndex(float[] crimeDataIndex) {
		this.crimeDataIndex = crimeDataIndex;
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
			if(numMurders[i] == year){
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
			if(numRapes[i] == year){
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
			if(numRobberies[i] == year){
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
			if(numAssaults[i] == year){
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
			if(numBurgleries[i] == year){
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
			if(numThefts[i] == year){
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
			if(numAutoThefts[i] == year){
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
			if(numArsons[i] == year){
				return numArsons[i];
			} // end if
		} // end for i
		return -1; // data for year requested not available
	}
	
	public void setNumArsons(int[] numArsons) {
		this.numArsons = numArsons;
	}
}
