package com.example.draweractivity.Model;

public class GasStation {
    private double lattitude;
    private double longitude;
    private String nameGasStation;
    private String addressGasStation;

    public GasStation() {
    }

    public GasStation(double lattitude, double longitude, String nameGasStation, String addressGasStation) {
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.nameGasStation = nameGasStation;
        this.addressGasStation = addressGasStation;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNameGasStation() {
        return nameGasStation;
    }

    public void setNameGasStation(String nameGasStation) {
        this.nameGasStation = nameGasStation;
    }

    public String getAddressGasStation() {
        return addressGasStation;
    }

    public void setAddressGasStation(String addressGasStation) {
        this.addressGasStation = addressGasStation;
    }
}
