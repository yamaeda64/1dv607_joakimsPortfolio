package model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * This is a Boat class that has the important details for a Boat
 */
public class Boat
{
    private BoatType boatType;
    private int length;     // length in cm
    private String boatID;
    
    /**
     * Edit an existing boat with input arguments as the new data.
     * @param boatType  what type of boat
     * @param length length in centimeters
     * @param boatID an not required field to add if the boat has a registrationNumber
     */
   public void editBoat(BoatType boatType, int length, String boatID)
   {
       this.boatType = boatType;
       this.length = length;
       this.boatID = boatID;
   }
   
   
    // Getters & Setters
    @XmlAttribute
    public BoatType getBoatType()
    {
        return boatType;
    }
    
    public void setBoatType(BoatType boatType)
    {
        this.boatType = boatType;
    }
    @XmlAttribute
    public String getBoatID()
    {
        return boatID;
    }
    
    public void setBoatID(String boatID)
    {
        this.boatID = boatID;
    }
    @XmlAttribute
    public int getLength()
    {
        return length;
    }
    
    public void setLength(int length)
    {
        this.length = length;
    }
}
