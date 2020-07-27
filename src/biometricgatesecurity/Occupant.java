package biometricgatesecurity;

public class Occupant 
{
    private String name,houseNo;
    private int ID;

    public Occupant() 
    {
        
    }

    public Occupant(String name, String houseNo, int ID) 
    {
        this.name = name;
        this.houseNo = houseNo;
        this.ID = ID;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getHouseNo() 
    {
        return houseNo;
    }

    public void setHouseNo(String houseNo) 
    {
        this.houseNo = houseNo;
    }

    public int getID() 
    {
        return ID;
    }

    public void setID(int ID) 
    {
        this.ID = ID;
    }
}
