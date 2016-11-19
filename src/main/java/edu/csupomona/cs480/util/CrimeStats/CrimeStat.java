package edu.csupomona.cs480.util.CrimeStats;

/**
 * @author Connor
 */
public class CrimeStat {
    private String type;
    private int [] totalCrimes;
    private float [] perHundredThousand;

    public CrimeStat (String type){
        this.type = type;
    }

    public CrimeStat (String type, int [] totalCrimes, float [] perHundredThousand){
        this.type = type;
        this.totalCrimes = totalCrimes;
        this.perHundredThousand = perHundredThousand;
    }

    public float[] getPerHundredThousand() {
        return perHundredThousand;
    }

    public float getPerHundredThousand(int index) {
        if (this.perHundredThousand != null && index < this.perHundredThousand.length) {
            return this.perHundredThousand[index];
        } else {
            return -1;
        }
    }

    public int[] getTotalCrimes() {
        return totalCrimes;
    }

    public float getTotalCrimes(int index) {
        if (this.totalCrimes != null && index < this.totalCrimes.length) {
            return this.totalCrimes[index];
        } else {
            return -1;
        }
    }

    public String getType() {
        return type;
    }

    public void setTotalCrimes(int[] totalCrimes) {
        this.totalCrimes = totalCrimes;
    }

    public void setPerHundredThousand(float[] perHundredThousand) {
        this.perHundredThousand = perHundredThousand;
    }
}
