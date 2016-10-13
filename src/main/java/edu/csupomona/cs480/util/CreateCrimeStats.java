package edu.csupomona.cs480.util;

import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
/**
 * 
 * @author Jonathan T. Fetzer
 * class: CS 480, Cal Poly Pomona
 * group: Sounds Neat
 *
 */

// This class uses Jsoup to fetch and parse html pages.
// To use this class, download the Jsoup jar and add it to you java build path.
// In Eclipse, right click on project name -> properties -> java build path -> add external jars.

public class CreateCrimeStats{  
	CrimeStats crimeStats;
	
	CreateCrimeStats(String city, String state) throws IOException{  
    	crimeStats = new CrimeStats(city, state);
    	  
    	city = city.replaceAll(" ", "-");
        Document doc = Jsoup.connect("http://www.city-data.com/city/" + city + "-" + state + ".html").get();  
        String html = doc.toString();  
        city = city.replaceAll("-", " ");        

        //-----------------Parse-crime-data-years-----------------
        int crimeData = html.indexOf("Crime rates in " + city + " by Year") - 10;
        int crimeDataYearsBegin = html.indexOf("\"", crimeData) + 1;
        int crimeDataYearsEnd = html.indexOf("\"", crimeDataYearsBegin );
        int numCrimeDataYears = Integer.parseInt(html.substring(crimeDataYearsBegin, crimeDataYearsEnd)) - 1;
        int[] crimeDataYears = new int[numCrimeDataYears];

        int parseYearStart = html.indexOf("</th>", crimeData + 10) + 5;
        int parseYearEnd = parseYearStart;
        for (int i = 0; i < crimeDataYears.length; i++) {
        	parseYearStart = html.indexOf(">", parseYearStart) + 1;
        	parseYearEnd = html.indexOf("</th>", parseYearStart);
        	crimeDataYears[i] = Integer.parseInt(html.substring(parseYearStart, parseYearEnd));
//        	System.out.println(crimeDataYears[i]);
        	parseYearStart = parseYearEnd + 5;
		} // end for i
        
        crimeStats.setCrimeDataYears(crimeDataYears);
        
        //-----------------Parse-number-of-murders-per-year-----------------
        int startMurderData = html.indexOf("Murders", parseYearEnd);
        int endMurderData = startMurderData;
        int[] numMurders = new int[crimeDataYears.length];
        int murderData = 0;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startMurderData = html.indexOf("<td>", startMurderData) + 4;
	        endMurderData = html.indexOf("</td>", startMurderData);
	        murderData = Integer.parseInt(html.substring(startMurderData, endMurderData));
	        numMurders[i] = murderData;
        } // end for i
        crimeStats.setNumMurders(numMurders);
        
        //----------Parse-number-of-murders-per-year-per-100,000------------
        int startMurderStats = html.indexOf("</td>", endMurderData + 5);        
        int endMurderStats = startMurderData;
        float[] murderStats = new float[crimeDataYears.length];
        float murderStat;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startMurderStats = html.indexOf("<td>", startMurderStats) + 4;
	        endMurderStats = html.indexOf("</td>", startMurderStats);
	        murderStat = Float.parseFloat(html.substring(startMurderStats, endMurderStats).replaceAll(",",""));
	        murderStats[i] = murderStat;
        } // end for i
        crimeStats.setMurderStats(murderStats);
        
        //-----------------Parse-number-of-rapes-per-year-----------------
        int startRapeData = html.indexOf("Rapes", endMurderStats);
        int endRapeData = startRapeData;
        int[] numRapes = new int[crimeDataYears.length];
        int RapeData = 0;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startRapeData = html.indexOf("<td>", startRapeData) + 4;
	        endRapeData = html.indexOf("</td>", startRapeData);
	        RapeData = Integer.parseInt(html.substring(startRapeData, endRapeData));
	        numRapes[i] = RapeData;
        } // end for i
        crimeStats.setNumRapes(numRapes);
        
        //----------Parse-number-of-Rapes-per-year-per-100,000------------
        int startRapeStats = html.indexOf("</td>", endRapeData + 5);        
        int endRapeStats = startRapeData;
        float[] RapeStats = new float[crimeDataYears.length];
        float RapeStat;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startRapeStats = html.indexOf("<td>", startRapeStats) + 4;
	        endRapeStats = html.indexOf("</td>", startRapeStats);
	        RapeStat = Float.parseFloat(html.substring(startRapeStats, endRapeStats).replaceAll(",",""));
	        RapeStats[i] = RapeStat;
        } // end for i
        crimeStats.setRapeStats(RapeStats);
        
        //-----------------Parse-number-of-robberies-per-year-----------------
        int startRobberyData = html.indexOf("Robberies", endRapeStats);
        int endRobberyData = startRobberyData;
        int[] numRobberys = new int[crimeDataYears.length];
        int robberyData = 0;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startRobberyData = html.indexOf("<td>", startRobberyData) + 4;
	        endRobberyData = html.indexOf("</td>", startRobberyData);
	        robberyData = Integer.parseInt(html.substring(startRobberyData, endRobberyData));
	        numRobberys[i] = robberyData;
        } // end for i
        crimeStats.setNumRobberies(numRobberys);
        
        //----------Parse-number-of-Robberies-per-year-per-100,000------------
        int startRobberyStats = html.indexOf("</td>", endRobberyData + 5);        
        int endRobberyStats = startRobberyData;
        float[] RobberyStats = new float[crimeDataYears.length];
        float RobberyStat;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startRobberyStats = html.indexOf("<td>", startRobberyStats) + 4;
	        endRobberyStats = html.indexOf("</td>", startRobberyStats);
	        RobberyStat = Float.parseFloat(html.substring(startRobberyStats, endRobberyStats).replaceAll(",",""));
	        RobberyStats[i] = RobberyStat;
        } // end for i
        crimeStats.setRobberyStats(RobberyStats);
        
        
        //-----------------Parse-number-of-assaults-per-year-----------------
        int startAssaultData = html.indexOf("Assaults", endRobberyStats);
        int endAssaultData = startAssaultData;
        int[] numAssaults = new int[crimeDataYears.length];
        int AssaultData = 0;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startAssaultData = html.indexOf("<td>", startAssaultData) + 4;
	        endAssaultData = html.indexOf("</td>", startAssaultData);
	        AssaultData = Integer.parseInt(html.substring(startAssaultData, endAssaultData));
	        numAssaults[i] = AssaultData;
        } // end for i
        crimeStats.setNumAssaults(numAssaults);
        
        //----------Parse-number-of-assaults-per-year-per-100,000------------
        int startAssaultStats = html.indexOf("</td>", endAssaultData + 5);        
        int endAssaultStats = startAssaultData;
        float[] assaultStats = new float[crimeDataYears.length];
        float assaultStat;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startAssaultStats = html.indexOf("<td>", startAssaultStats) + 4;
	        endAssaultStats = html.indexOf("</td>", startAssaultStats);
	        assaultStat = Float.parseFloat(html.substring(startAssaultStats, endAssaultStats).replaceAll(",",""));
	        assaultStats[i] = assaultStat;
        } // end for i
        crimeStats.setAssaultStats(assaultStats);
        
      //-----------------Parse-number-of-burglaries-per-year-----------------
        int startBurglaryData = html.indexOf("Burglaries", endAssaultStats);
        int endBurglaryData = startBurglaryData;
        int[] numBurglarys = new int[crimeDataYears.length];
        int burglaryData = 0;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startBurglaryData = html.indexOf("<td>", startBurglaryData) + 4;
	        endBurglaryData = html.indexOf("</td>", startBurglaryData);
	        burglaryData = Integer.parseInt(html.substring(startBurglaryData, endBurglaryData).replaceAll(",",""));
	        numBurglarys[i] = burglaryData;
        } // end for i
        crimeStats.setNumBurglaries(numBurglarys);
        
        //----------Parse-number-of-burgleries-per-year-per-100,000------------
        int startBurglaryStats = html.indexOf("</td>", endBurglaryData + 5);        
        int endBurglaryStats = startBurglaryData;
        float[] BurglaryStats = new float[crimeDataYears.length];
        float BurglaryStat;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startBurglaryStats = html.indexOf("<td>", startBurglaryStats) + 4;
	        endBurglaryStats = html.indexOf("</td>", startBurglaryStats);
	        BurglaryStat = Float.parseFloat(html.substring(startBurglaryStats, endBurglaryStats).replaceAll(",",""));
	        BurglaryStats[i] = BurglaryStat;
        } // end for i
        crimeStats.setBurglaryStats(BurglaryStats);
        
        //-----------------Parse-number-of-thefts-per-year-----------------
        int startTheftData = html.indexOf("Thefts", endBurglaryStats);
        int endTheftData = startTheftData;
        int[] numThefts = new int[crimeDataYears.length];
        int theftData = 0;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startTheftData = html.indexOf("<td>", startTheftData) + 4;
	        endTheftData = html.indexOf("</td>", startTheftData);
	        theftData = Integer.parseInt(html.substring(startTheftData, endTheftData).replaceAll(",",""));
	        numThefts[i] = theftData;
        } // end for i
        crimeStats.setNumThefts(numThefts);
        
        //----------Parse-number-of-thefts-per-year-per-100,000------------
        int startTheftStats = html.indexOf("</td>", endTheftData + 5);        
        int endTheftStats = startTheftData;
        float[] TheftStats = new float[crimeDataYears.length];
        float TheftStat;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startTheftStats = html.indexOf("<td>", startTheftStats) + 4;
	        endTheftStats = html.indexOf("</td>", startTheftStats);
	        TheftStat = Float.parseFloat(html.substring(startTheftStats, endTheftStats).replaceAll(",",""));
	        TheftStats[i] = TheftStat;
        } // end for i
        crimeStats.setTheftStats(TheftStats);
        
        //-----------------Parse-number-of-auto-thefts-per-year-----------------
        int startAutoTheftData = html.indexOf("Auto", endTheftStats);
        int endAutoTheftData = startAutoTheftData;
        int[] numAutoThefts = new int[crimeDataYears.length];
        int autoTheftData = 0;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startAutoTheftData = html.indexOf("<td>", startAutoTheftData) + 4;
	        endAutoTheftData = html.indexOf("</td>", startAutoTheftData);
	        autoTheftData = Integer.parseInt(html.substring(startAutoTheftData, endAutoTheftData).replaceAll(",",""));
	        numAutoThefts[i] = autoTheftData;
        } // end for i
        crimeStats.setNumAutoThefts(numAutoThefts);
        
        //----------Parse-number-of-auto-thefts-per-year-per-100,000------------
        int startAutoTheftStats = html.indexOf("</td>", endAutoTheftData + 5);        
        int endAutoTheftStats = startAutoTheftData;
        float[] autoTheftStats = new float[crimeDataYears.length];
        float autoTheftStat;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startAutoTheftStats = html.indexOf("<td>", startAutoTheftStats) + 4;
	        endAutoTheftStats = html.indexOf("</td>", startAutoTheftStats);
	        autoTheftStat = Float.parseFloat(html.substring(startAutoTheftStats, endAutoTheftStats).replaceAll(",",""));
	        autoTheftStats[i] = autoTheftStat;
        } // end for i
        crimeStats.setAutoTheftStats(autoTheftStats);
        
      //-----------------Parse-number-of-incidents-of-arson-per-year-----------------
        int startArsonData = html.indexOf("Arson", endAutoTheftStats);
        int endArsonData = startArsonData;
        int[] numArsons = new int[crimeDataYears.length];
        int arsonData = 0;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startArsonData = html.indexOf("<td>", startArsonData) + 4;
	        endArsonData = html.indexOf("</td>", startArsonData);
	        arsonData = Integer.parseInt(html.substring(startArsonData, endArsonData).replaceAll(",",""));
	        numArsons[i] = arsonData;
        } // end for i
        crimeStats.setNumArsons(numArsons);
      
        //----------Parse-number-of-incidents-of-arson-per-year-per-100,000------------
        int startArsonStats = html.indexOf("</td>", endArsonData + 5);        
        int endArsonStats = startArsonData;
        float[] arsonStats = new float[crimeDataYears.length];
        float arsonStat;
        
        for (int i = 0; i < crimeDataYears.length; i++) {        
	        startArsonStats = html.indexOf("<td>", startArsonStats) + 4;
	        endArsonStats = html.indexOf("</td>", startArsonStats);
	        arsonStat = Float.parseFloat(html.substring(startArsonStats, endArsonStats).replaceAll(",",""));
	        arsonStats[i] = arsonStat;
        } // end for i
        crimeStats.setArsonStats(arsonStats);
    }  
}  