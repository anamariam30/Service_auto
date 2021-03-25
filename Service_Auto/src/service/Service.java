package service;


import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Service {
    public CarsOnYard cars;
    public List<Repairs> repairs;
    public static void addHistory(String fileContent,BufferedWriter writer) throws IOException {
        writer.write(fileContent);
        writer.newLine();
    }

    public Service() {
        this.cars=new CarsOnYard();
        repairs=new LinkedList<>();
    }

    public Repairs searchRepair(Car carr)
    {


        for(Repairs repair:repairs)
        {
            if(repair.getCar().equals(carr))
                return repair;
        }
     return null;

    }


    public static void history(Repairs repair) {
        BufferedWriter writer=null;
        try{
            writer=new BufferedWriter(new FileWriter(".idea/History/histories",true));
            if (repair.getStatusOfReparation().compareTo("ready") == 0)
            {
                String string=" Car: "+repair.getCar()+" " +" Owner: "+repair.getCar().getOwner()+" Details: "+repair;
                addHistory(string,writer);

                writer.close();
            }
        }catch (NullPointerException | IOException e)
        {
            e.printStackTrace();

        }
    }




}




