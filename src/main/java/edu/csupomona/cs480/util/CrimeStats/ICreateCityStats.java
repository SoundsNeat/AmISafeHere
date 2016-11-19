package edu.csupomona.cs480.util.CrimeStats;

import java.io.IOException;

/**
 * @author william
 */
public interface ICreateCityStats {
    public void execute(String city, String state) throws IOException;
    public CityStats getCityStats();
    public int [] getCityDataYears();
}
