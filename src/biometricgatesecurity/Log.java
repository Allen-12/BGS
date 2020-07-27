package biometricgatesecurity;

import java.util.Date;

public class Log 
{
    private int serial;
    private String name,house,vehicle;
    private Date date,timeIn,timeOut;

    public Log() 
    {
        
    }

    public Log(int serial, String name, String house, String vehicle, Date date, Date timeIn, Date timeOut) 
    {
        this.serial = serial;
        this.name = name;
        this.house = house;
        this.vehicle = vehicle;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public int getSerial() 
    {
        return serial;
    }

    public void setSerial(int serial) 
    {
        this.serial = serial;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getHouse() 
    {
        return house;
    }

    public void setHouse(String house) 
    {
        this.house = house;
    }

    public String getVehicle() 
    {
        return vehicle;
    }

    public void setVehicle(String vehicle) 
    {
        this.vehicle = vehicle;
    }

    public Date getDate() 
    {
        return date;
    }

    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getTimeIn() 
    {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) 
    {
        this.timeIn = timeIn;
    }

    public Date getTimeOut() 
    {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) 
    {
        this.timeOut = timeOut;
    }
    
    
}
