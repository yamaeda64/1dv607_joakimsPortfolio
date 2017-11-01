package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * This is a member class to store all important details about a members
 * the fields are stored as propertys, i.e. StringProperty instead of String
 * to keep compatibility with TableView.
 * Also an ObservableList<Boat> is used instead of a simple Array of Boats for the
 * same reason.
 */
public class Member
{
    private StringProperty firstName;
  
    private StringProperty lastName;

    private StringProperty personalNumber;

    private SimpleIntegerProperty memberID;
 
    private SimpleIntegerProperty boatCount;

    private ObservableList<Boat> boatList;
   
    /**
     * Constructor that initialize the new Member Object
     */
    public Member()
    {
        
        boatList = FXCollections.observableList(new ArrayList<>());
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.personalNumber = new SimpleStringProperty();
        this.memberID = new SimpleIntegerProperty();
        this.boatCount = new SimpleIntegerProperty();
        
    }
    
    /**
     * Adds a Boat to the member. Arguments for the boat creation need to be passed in.
     * @param boatType  type of boat
     * @param length length in centimeters
     * @param boatID not required argument, can be used if boat has a registration number
     */
    public void addBoat(model.BoatType boatType, int length, String boatID)
    {
        Boat boat = new Boat();
        boat.setBoatType(boatType);
        boat.setBoatID(boatID);
        boat.setLength(length);
        boatCount.setValue(boatCount.get() + 1);
        boatList.add(boat);
    }
    
    /**
     * Deletes a boat from a Members collection of Boats.
     * @param boat the boat that are to be deleted.
     */
    public void deleteBoat(Boat boat)
    {
        boatList.remove(boat);
        boatCount.setValue(boatList.size());
    }
    
    /**
     * Edits a members fields by the input of the arguments
     * @param firstName a Members first name (Should only be characters)
     * @param lastName a Members family name (Should only be characters)
     * @param personalID A Members birthday and personal numbers.
     */
    public void editMember(String firstName, String lastName, String personalID)
    {
        this.firstName.setValue(firstName);
        this.lastName.setValue(lastName);
        this.personalNumber.setValue(personalID);
    }
    
    // Getters & Setters
    
    public void setFirstName(String firstName)
    {
        this.firstName.setValue(firstName);
    }
    @XmlAttribute
    public String getFirstName()
    {
        return firstName.get();
    }
    
    public void setLastName(String lastName)
    {
        this.lastName.setValue(lastName);
    }
    @XmlAttribute
    public String getLastName()
    {
        return lastName.get();
    }
    
    public void setPersonalNumber(String personalNumber)
    {
        this.personalNumber.setValue(personalNumber);
    }
    @XmlAttribute
    public String getPersonalNumber()
    {
        return personalNumber.get();
    }
    @XmlAttribute
    public int getMemberID()
    {
        return memberID.get();
    }
    public void setMemberID(int memberID){ this.memberID.setValue(memberID);}
    @XmlAttribute
    public int getBoatCount()
    {
        return boatCount.get();
    }
    public void setBoatCount(int boatCount)
    {
        this.boatCount.set(boatCount);
    }
    
    @XmlElementWrapper
    @XmlElement(name="Boats")
    public ObservableList<Boat> getBoatList()
    {
        return boatList;
    }
    

}
