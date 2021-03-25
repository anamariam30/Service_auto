package service;

public class Owner{
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String CNP;
    private long phoneNumber;

    public Owner(String firstName, String lastName, int age, String address, String CNP,long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.CNP = CNP;
        this.phoneNumber=phoneNumber;
    }


    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCNP()
    {
        return CNP;
    }

    public void setCNP(String CNP)
    {
        this.CNP = CNP;
    }

    @Override
    public String toString() {
        return "firstName='" + firstName +
                ", lastName='" + lastName +
                ", phoneNumber='" + phoneNumber ;
    }
}
