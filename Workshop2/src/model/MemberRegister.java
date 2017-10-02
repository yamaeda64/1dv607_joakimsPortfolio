package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * This class holds all members and methods for adding members, deleting members, change info.
 */
@XmlRootElement(name="memberRegister")
public class MemberRegister
{
    private int memberIdCounter;
    private ObservableList<Member> observableList;
    private XmlHandler xmlHandler;
    
    // Constructor
    public MemberRegister()
    {
        observableList = FXCollections.observableList(new ArrayList<>());
        xmlHandler = new XmlHandler();
    }
    
    public void addMember(String firstName, String lastName, String personalID)
    {
        Member newMember = new Member();
        newMember.setFirstName(firstName);
        newMember.setLastName(lastName);
        newMember.setPersonalNumber(personalID);
        newMember.setMemberID(memberIdCounter++);
        observableList.add(newMember);
    }

    public void deleteMember(Member memberToBeDeleted)
    {
        getMembers().remove(memberToBeDeleted);
    }
    
    public void exportXML(MemberRegister input)
    {
           try
           {
               xmlHandler.exportXML(input);
           }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }
    
    // getters & setters
    
    @XmlElement(name="memberList")
    public ObservableList<Member> getMembers()
    {
        return observableList;
    }
    
    public int getMemberIdCounter(){ return memberIdCounter; }
    public void setMemberIdCounter(int input)
    {
        this.memberIdCounter = input;
    }
    
}