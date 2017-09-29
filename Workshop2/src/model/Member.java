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
 * This is a member class to store all details about the members
 */
public class Member
{
    private static int counter;         // TODO, this probably get weird when restarting program
                                        // maybe set to highest member id +1 at start?
    
    private StringProperty firstName;
  
    private StringProperty lastName;

    private StringProperty personalNumber;

    private SimpleIntegerProperty memberID;
 
    private SimpleIntegerProperty boatCount;

        private ObservableList<Boat> boatList;
    // END TEMPORARY!!!!
    /**
     * Constructor that adds up to member counter and assigns the current value to memberID
     */
    public Member()
    {
        // TODO THIS IS TEMPORARY!!!
        
        boatList = FXCollections.observableList(new ArrayList<>());

        // END TEMPORARY!!!!
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.personalNumber = new SimpleStringProperty();
        this.memberID = new SimpleIntegerProperty();
        this.boatCount = new SimpleIntegerProperty();
        memberID.setValue(++counter);
    }
    
    public void addBoat(model.BoatType boatType, int length, String boatID)
    {
        Boat boat = new Boat();
        boat.setBoatType(boatType);
        boat.setBoatID(boatID);
        boat.setLength(length);
        boatCount.setValue(boatCount.get() + 1);
        
        //TODO, TEMPORARY
        boatList.add(boat);
    }
    
    public void deleteBoat(Boat boat)
    {
        boatList.remove(boat);
    }
    
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
