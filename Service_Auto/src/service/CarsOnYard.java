package service;

import java.util.*;
public class CarsOnYard {
    public static List<Car> repairStation=new LinkedList<>();
    public static Queue<Car> waitingQueue;


    public CarsOnYard() {
        this.waitingQueue=new LinkedList<>();
        this.repairStation=new LinkedList<>();
    }

    public boolean isStationFree()
    {
       if(repairStation.size()<8)
           return true;
       else
           return false;

    }

    public void addCarToStation(Car car)
    {try {


        if(isStationFree()==true) {

            repairStation.add(car);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    public void addCarToStationFromWaitingQueue()
    {try {
        if(isStationFree()==true) {
            repairStation.add( waitingQueue.poll());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    public void removeCarFromStation(Car car)
    {
        repairStation.remove(car);
    }

    public void addCarToWaitingQueue(Car car)
    {
        if(emptySpaceForNewCars()==true)
            waitingQueue.add(car);
        else{
            throw new StringIndexOutOfBoundsException("No more space for new cars!!");

        }

    }

    public boolean emptySpaceForNewCars()
    {   boolean GotSpace=true;
        if(waitingQueue.size()<12)
            return  GotSpace;
        else
            GotSpace=false;
        return GotSpace;
    }

}
