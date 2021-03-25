package service;

public class ReturningCars {
   Car car;

    public ReturningCars(Car car) {
       this.car = car;
    }
    public String callOwner()
    {
        return "Calling "+ car.owner.getFirstName()+" "+car.owner.getLastName()+" at phone number:"+car.owner.getPhoneNumber();
    }
}
