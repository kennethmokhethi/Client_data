package model;

public class Client{
    private String name;
    private String lastname;
    private String address;
    private int acc_num;


    public Client(String name,String surname,String address,int acc_num)
    {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setAcc_num(acc_num);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setSurname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAcc_num() {
        return acc_num;
    }

    public void setAcc_num(int acc_num) {
        this.acc_num = acc_num;
    }
}
