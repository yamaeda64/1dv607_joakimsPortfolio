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
    private ArrayList<Member> memberList; // TODO, only used by XML import, can it be removed? used by export???
    private XmlHandler xmlHandler;
    
    // Constructor
    public MemberRegister()
    {
        memberList = new ArrayList<Member>();
        observableList = FXCollections.observableList(memberList);
        xmlHandler = new XmlHandler();
        
    }
    
    
    
    public void addMember(String firstName, String lastName, String personalID)
    {
        Member newMember = new Member();
        newMember.setFirstName(firstName);
        newMember.setLastName(lastName);
        newMember.setPersonalNumber(personalID);
        newMember.setMemberID(memberIdCounter++);
      //  memberList.add(newMember);
        observableList.add(newMember);
    
        // TODO, rewrite to save on exit and a saveButton
     //   try
     //   {
     //       xmlHandler.exportXML(this);
     //   }
    //    catch(Exception e)
    //    {
    //        e.printStackTrace();
    //    }
        
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
    
    //public ArrayList<Member> getMemberList() {return memberList;}
}