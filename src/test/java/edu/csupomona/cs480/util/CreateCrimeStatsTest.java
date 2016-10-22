package edu.csupomona.cs480.util;
/**
 * 
 * @author Jonathan T. Fetzer
 * class: CS 480, Cal Poly Pomona
 * group: Sounds Neat
 *
 */

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateCrimeStatsTest {

	public static void main(String[] args) throws Exception {
		boolean JSON = true; // example input: {"city":"Pomona","state":"California"}
		CreateCrimeStats cityA = null;

		if(JSON){
			ObjectMapper mapper = new ObjectMapper();
	//		replaceAll method prevents mapper.readValue from stripping quotation marks out of JSON format.
			CityState cityState = mapper.readValue((args[0].replaceAll("\"", "\\\"")), CityState.class);
			try {
				cityA = new CreateCrimeStats(cityState.city, cityState.state);
			} catch (IOException e) {
				e.printStackTrace();
			} // end catch
		} else { // example input: Pomona California
			if(args.length < 2) {
				//commented out to test w/o args
				//throw new Exception("must provide city and state as arguments, e.g. Pomona California");
				args = new String[2];
				args[0] = "Pomona";
				args[1] = "California";
			} else if(args.length > 2){ // for inputs such as Los Angeles California
				int i;
				for (i = 1; i < args.length - 1; i++) {
					args[0] = args[0] + " " + args[i];
				} // end for i
				args[1] = args[i];
				try {
					cityA = new CreateCrimeStats(args[0], args[1]);
				} catch (IOException e) {
					e.printStackTrace();
				} // end catch
			} // end else if
		} // end else
		
		System.out.println("JSON: " + cityA.crimeStats.toJson()+ "\n");
		
		/*
		// print crime data years
    	System.out.print("Crime data years for " + args[0] + ", " + args[1] + ": ");
    	CrimeStats.print(cityA.crimeStats.getCrimeDataYears());
    	System.out.println();
        	
        // Print number of murders
    	System.out.print("Number of murders per year: ");
    	CrimeStats.print(cityA.crimeStats.getNumMurders());
        	
    	// Print murder stats
    	System.out.print("Murders per year per 100,000: ");
    	CrimeStats.print(cityA.crimeStats.getMurderStats());
    	System.out.println();
        	
    	// Print number of rapes
    	System.out.print("Number of rapes per year: ");
    	CrimeStats.print(cityA.crimeStats.getNumRapes());
    	
    	// Print rate stats
       	System.out.print("Number of rapes per year per 100,000: ");
       	CrimeStats.print(cityA.crimeStats.getRapeStats());
       	System.out.println();
	   
       // Print number of robberies
       	System.out.print("Number of robberies per year: ");
       	CrimeStats.print(cityA.crimeStats.getNumRobberies());
       
       // Print robbery stats
       	System.out.print("Number of robberies per year per 100,000: ");
       	CrimeStats.print(cityA.crimeStats.getRobberyStats());
       	System.out.println();
       
       // Print assault stats
       	System.out.print("Number of assaults per year: ");
       	CrimeStats.print(cityA.crimeStats.getNumAssaults());

       // Print assault stats
       	System.out.print("Number of assaults per year per 100,000: ");
       	CrimeStats.print(cityA.crimeStats.getAssaultStats());
       	System.out.println();
       
       // Print number of burglaries       
       	System.out.print("Number of burglaries per year: ");
       	CrimeStats.print(cityA.crimeStats.getNumBurglaries());
       
       // Print burglary stats
       	System.out.print("Number of burglaries per year per 100,000: ");
       	CrimeStats.print(cityA.crimeStats.getBurglaryStats());
       	System.out.println();
       
       // Print number of robberies
       	System.out.print("Number of thefts per year: ");
       	CrimeStats.print(cityA.crimeStats.getNumThefts());
       
       // Print theft stats
       	System.out.print("Number of thefts per year per 100,000: ");
       	CrimeStats.print(cityA.crimeStats.getTheftStats());
       	System.out.println();
       
       // Print number of auto thefts
        System.out.print("Number of auto thefts per year: ");
       	CrimeStats.print(cityA.crimeStats.getNumAutoThefts());
       
       // Print Auto Theft stats
       	System.out.print("Number of auto thefts per year per 100,000: ");
       	CrimeStats.print(cityA.crimeStats.getAutoTheftStats());
       	System.out.println();
       
       // Print number of robberies
       	System.out.print("Number of incidents of arson per year: ");
       	CrimeStats.print(cityA.crimeStats.getNumArsons());
       
       
       // Print Auto Theft stats
       	System.out.print("Number of incidents of arson per year per 100,000: ");
       	CrimeStats.print(cityA.crimeStats.getArsonStats());
       	System.out.println(); 
       	
       	*/
	} // end method main
	
} // end class
