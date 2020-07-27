package biometricgatesecurity;

public class House 
{
    private String houseNo,ownerName;
    private int noOfOccupants;

    public House() 
    {
        
    }

    public House(String houseNo, String ownerName, int noOfOccupants) 
    {
        this.houseNo = houseNo;
        this.ownerName = ownerName;
        this.noOfOccupants = noOfOccupants;
    }

    public String getHouseNo() 
    {
        return houseNo;
    }

    public void setHouseNo(String houseNo) 
    {
        this.houseNo = houseNo;
    }

    public String getOwnerName() 
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName) 
    {
        this.ownerName = ownerName;
    }

    public int getNoOfOccupants() 
    {
        return noOfOccupants;
    }

    public void setNoOfOccupants(int noOfOccupants) 
    {
        this.noOfOccupants = noOfOccupants;
    }
}
