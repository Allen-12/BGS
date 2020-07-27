package biometricgatesecurity;

public class Vehicle 
{
    private String houseNo,regNo;
    private String colour;

    public Vehicle() 
    {
        
    }

    public Vehicle(String houseNo, String regNo, String colour) 
    {
        this.houseNo = houseNo;
        this.regNo = regNo;
        this.colour = colour;
    }

    public String getHouseNo() 
    {
        return houseNo;
    }

    public void setHouseNo(String houseNo) 
    {
        this.houseNo = houseNo;
    }

    public String getRegNo() 
    {
        return regNo;
    }

    public void setRegNo(String regNo) 
    {
        this.regNo = regNo;
    }

    public String getColour() 
    {
        return colour;
    }

    public void setColour(String colour) 
    {
        this.colour = colour;
    }
}
