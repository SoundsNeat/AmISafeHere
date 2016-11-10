package edu.csupomona.cs480.util.CrimeStats;

import java.io.IOException;

/**
 * @author william
 */
public interface ICreateCrimeStats {
    public void execute(String city, String state) throws IOException;
    public CrimeStats getCrimeStats();
    public int[] getCrimeDataYears();
}
