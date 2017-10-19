package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This is a member class to store all important details about a members
 * It also have a list of boats.
 */
public class Member
{
    private String firstName;
  
    private String lastName;

    private String personalNumber;

    private int memberID;
 
    private int boatCount;

    private ArrayList<Boat> boatList;
    
    private ArrayList<ModelChangedObserver> subscribers;
   
    /**
     * Constructor that initialize the new Member Object
     */
    public Member()
    {
        subscribers = new ArrayList<>();
        boatList = new ArrayList<>();
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
        boatCount++;
        boatList.add(boat);
        notifySubscribers();
        System.out.println("Member.addBoat");
    }
    
    public void editBoat(Boat boat, BoatType boatType, int length, String boatID)
    {
        boat.editBoat(boatType, length, boatID);
        notifySubscribers();
    }
    
    public void deleteBoat(Boat boat)
    {
        boatList.remove(boat);
        boatCount--;
        notifySubscribers();
    }
    
    public void editMember(String firstName, String lastName, String personalID)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalID;
        notifySubscribers();
    }
    
    
    public void notifySubscribers()
    {
        System.out.println("-------");
        for(ModelChangedObserver subscriber : subscribers)
        {
            
            System.out.println("MemberSendNotifySubscribers");
            subscriber.modelIsChanged();
        }
    }
    
    
    // Getters & Setters
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    @XmlAttribute
    public String getFirstName()
    {
        return firstName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    @XmlAttribute
    public String getLastName()
    {
        return lastName;
    }
    
    public void setPersonalNumber(String personalNumber)
    {
        this.personalNumber = personalNumber;
    }
    
    @XmlAttribute
    public String getPersonalNumber()
    {
        return personalNumber;
    }
   
    @XmlAttribute
    public int getMemberID()
    {
        return memberID;
    }
    
    public void setMemberID(int memberID)
    {
        this.memberID = memberID;
    }
    
    @XmlAttribute
    public int getBoatCount()
    {
        return boatCount;
    }
    
    public void setBoatCount(int boatCount)
    {
        this.boatCount = boatCount;
    }
    
    public void addSubscriber(ModelChangedObserver subscriber)
    {
        
        if(!subscribers.contains(subscriber))
        {
            System.out.println("SubsriberWasAdded: Member");
            subscribers.add(subscriber);
        }
    }
    public void addSubscriber(Iterator<ModelChangedObserver> inputSubscribers)
    {
        while(inputSubscribers.hasNext())
        {
            ModelChangedObserver temp = inputSubscribers.next();
            if(!subscribers.contains(temp))
            {
                System.out.println("SubscriberWasAdded: MemberCollection");
                subscribers.add(temp);
            }
        }
    }
    
    public void removeSubscriber(ModelChangedObserver subscriber)
    {
        subscribers.remove(subscriber);
    }
    
    @XmlElementWrapper
    @XmlElement(name="Boats")
    private ArrayList<Boat> getBoatList()// only for Xml handling
    {
        return boatList;
    }
    
    public Iterator<Boat> getBoatIterator()
    {
        return boatList.iterator();
    }
    
}
