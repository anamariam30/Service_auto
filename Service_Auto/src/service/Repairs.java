package service;

import java.time.LocalDate;

public class Repairs implements Comparable<Repairs> {
    public Car car;
    ReturningCars returnCar;
    private LocalDate dateOfIntrance;
    private LocalDate dateofExit;
    private String statusOfReparation;
    private double price;
    private String diagnostic;

    public Repairs(Car car, LocalDate dateOfIntrance) {
        this.car = car;
        this.dateOfIntrance=dateOfIntrance;
        returnCar=new ReturningCars(car);
    }


    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public Car getCar() {

        return car;
    }

    public void setCar(Car car) {

        this.car = car;
    }

    public LocalDate getDateOfIntrance() {

        return dateOfIntrance;
    }

    public void setDateOfIntrance(LocalDate dateOfIntrance) {

        this.dateOfIntrance = dateOfIntrance;
    }

    public LocalDate getDateofExit() {

        return dateofExit;
    }

    public void setDateofExit(LocalDate dateofExit) {

        this.dateofExit = dateofExit;
    }

    public String getStatusOfReparation() {

        return statusOfReparation;
    }

    public void setStatusOfReparation(String statusOfReparation) {

        this.statusOfReparation = statusOfReparation;
    }

    @Override
    public int compareTo(Repairs arepairs) {
        int result=0;
        if(dateOfIntrance.isBefore(arepairs.dateOfIntrance))
            result=1;
        else
            return result;
        return result;
    }

    public void changingStatus(String newStatus)
    {
        this.statusOfReparation=newStatus;
    }

    @Override
    public String toString() {
        return "Repairs: " +
                "car=" + car +
                ", dateOfIntrance=" + dateOfIntrance +
                ", dateofExit=" + dateofExit +
                ", statusOfReparation=" + statusOfReparation  +
                ", price=" + price +
                ", diagnostic= "+ diagnostic;
    }



}
