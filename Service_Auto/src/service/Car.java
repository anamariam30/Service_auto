package service;


public class Car {
    public Owner owner;
    private String model;
    private String manufacturer;
    private int year;
    private String licensePlate;


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Car(Owner owner, String model, String manufacturer, int year, String licensePlate) {
        this.owner = owner;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.licensePlate=licensePlate;
    }



    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return licensePlate;
    }
}
