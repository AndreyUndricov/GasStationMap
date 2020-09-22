package com.example.draweractivity.Model;

public class GasStation {

    private double lattitude;  //  широта
    private double longitude;  // долгота
    private String nameGasStation;
    private String addressGasStation;
    private double ai95;
    private double ai92;
    private double disel;


    public GasStation() {
    }


    public GasStation(double lattitude, double longitude, String nameGasStation, String addressGasStation) {
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.nameGasStation = nameGasStation;
        this.addressGasStation = addressGasStation;
    }

    public GasStation( String nameGasStation, String addressGasStation, double lattitude, double longitude, double ai95, double ai92, double disel) {

        this.nameGasStation = nameGasStation;
        this.addressGasStation = addressGasStation;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.ai95 = ai95;
        this.ai92 = ai92;
        this.disel = disel;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setNameGasStation(String nameGasStation) {
        this.nameGasStation = nameGasStation;
    }

    public void setAddressGasStation(String addressGasStation) {
        this.addressGasStation = addressGasStation;
    }

    public void setAi95(double ai95) {
        this.ai95 = ai95;
    }

    public void setAi92(double ai92) {
        this.ai92 = ai92;
    }

    public void setDisel(double disel) {
        this.disel = disel;
    }


    public double getLattitude() {
        return lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getNameGasStation() {
        return nameGasStation;
    }

    public String getAddressGasStation() {
        return addressGasStation;
    }

    public double getAi95() {
        return ai95;
    }

    public double getAi92() {
        return ai92;
    }

    public double getDisel() {
        return disel;
    }


}
