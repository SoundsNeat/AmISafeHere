package edu.csupomona.cs480.util.data;

/**
 * @author william
 */
public class MismatchedData {
    private String teleportCity;
    private String teleportState;
    private String dataCity;
    private String dataState;

    public MismatchedData(final String teleportCity, final String teleportState, final String dataCity, final String dataState) {
        this.teleportCity = teleportCity;
        this.teleportState = teleportState;
        this.dataCity = dataCity;
        this.dataState = dataState;
    }

    public String getTeleportCity() {
        return teleportCity;
    }

    public String getTeleportState() {
        return teleportState;
    }

    public String getDataCity() {
        return dataCity;
    }

    public String getDataState() {
        return dataState;
    }
}
