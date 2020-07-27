package biometricgatesecurity;

import java.time.LocalDate;
import java.util.Date;

public class Visitor 
{
    private String houseNo,name,email;
    private LocalDate dateOfVisit;
    private int OTP;

    public Visitor() 
    {
        
    }

    public Visitor(String houseNo, String name, String email, LocalDate dateOfVisit, int OTP) 
    {
        this.houseNo = houseNo;
        this.name = name;
        this.email = email;
        this.dateOfVisit = dateOfVisit;
        this.OTP = OTP;
    }

    public String getHouseNo()
    {
        return houseNo;
    }

    public void setHouseNo(String houseNo) 
    {
        this.houseNo = houseNo;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public LocalDate getDateOfVisit() 
    {
        return dateOfVisit;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) 
    {
        this.dateOfVisit = dateOfVisit;
    }

    
    public int getOTP() 
    {
        return OTP;
    }

    public void setOTP(int OTP) 
    {
        this.OTP = OTP;
    }

    @Override
    public String toString() 
    {
        return "Visitor{" + "houseNo=" + houseNo + ", name=" + name + ", email=" + email + ", dateOfVisit=" + dateOfVisit + ", OTP=" + OTP + '}';
    }
    
    
}
