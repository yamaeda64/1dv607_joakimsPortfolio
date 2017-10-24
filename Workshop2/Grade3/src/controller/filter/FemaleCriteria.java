package controller.filter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Member;

import java.util.Iterator;

/**
 * Created by joakimbergqvist on 2017-10-21.
 */
public class FemaleCriteria implements Criteria
{
    private Boolean isFemale;
    
    public FemaleCriteria(Boolean isFemale)
    {
        this.isFemale = isFemale;
    }
    @Override
    public ObservableList<Member> meetCriteria(Iterator<Member> members)
    {
        ObservableList<Member> criteriaList = FXCollections.observableArrayList();
        while(members.hasNext())
        {
            Member member = members.next();
            if(isFemale)
            {
                if(member.getPersonalNumberObject().isFemaleNumber())
                {
                    criteriaList.add(member);
                }
            }
            else
            {
                if(member.getPersonalNumberObject().isMaleNumber())
                {
                    criteriaList.add(member);
                }
            }
        }
        
        return criteriaList;
    }
    
    
    public String toString()
    {
        String returnString = "Sex: ";
        if(isFemale)
        {
            returnString += "Female, ";
        }
        else
        {
            returnString += "Male, ";
        }
        return returnString;
    }
    
}

