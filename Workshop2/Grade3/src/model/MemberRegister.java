package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class holds all members and methods for adding members, deleting members, change info.
 */
@XmlRootElement(name="memberRegister")
public class MemberRegister
{
    private int memberIdCounter;
    private ArrayList<Member> members;
    private XmlHandler xmlHandler;
    private ArrayList<ModelChangedObserver> subscribers;
    
    // Constructor
    public MemberRegister()
    {
        subscribers = new ArrayList<>();
        members = new ArrayList<>();
        xmlHandler = new XmlHandler();
    }
    
    public void addMember(String firstName, String lastName, String personalID)
    {
        Member newMember = new Member();
        newMember.setFirstName(firstName);
        newMember.setLastName(lastName);
        newMember.setPersonalNumber(personalID);
        newMember.setMemberID(memberIdCounter++);
        members.add(newMember);
        notifySubscribers();
    }
    
    
    
    public void deleteMember(Member memberToBeDeleted)
    {
        getMembers().remove(memberToBeDeleted);
        notifySubscribers();
    }
    
    public void exportXML()
    {
           try
           {
               xmlHandler.exportXML(this);
           }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }
    
    public void addSubscriber(ModelChangedObserver newSubscriber)
    {
        subscribers.add(newSubscriber);
    }
    
    public void notifySubscribers()
    {
        for(ModelChangedObserver subscriber : subscribers)
        {
            subscriber.modelIsChanged();
        }
    }
    
    public void updateMemberID()
    {
       for(Member member : members)
       {
           if(member.getMemberID() >= memberIdCounter)
           {
               memberIdCounter = member.getMemberID() +1;
           }
       }
    }
    // getters & setters
    
    @XmlElement(name="memberList")
    private ArrayList<Member> getMembers()  // Only for XML handling
    {
        return members;
    }
    
    public Iterator<Member> getMemberIterator()
    {
        return members.iterator();
    }
    
    public Iterator<ModelChangedObserver>getSubscribers()
    {
        return subscribers.iterator();
    }
    
}