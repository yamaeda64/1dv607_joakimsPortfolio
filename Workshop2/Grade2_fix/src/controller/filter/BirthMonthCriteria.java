package controller.filter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Member;

import java.time.Month;
import java.util.Iterator;

/**
 * Created by joakimbergqvist on 2017-10-21.
 */
public class BirthMonthCriteria implements Criteria
{
    Month month;
    
    public BirthMonthCriteria(Month month)
    {
        this.month = month;
    }
    @Override
    public ObservableList<Member> meetCriteria(Iterator<Member> members)
    {
        ObservableList<Member> criteriaList = FXCollections.observableArrayList();
        while(members.hasNext())
        {
            Member member = members.next();
            
            if(member.getPersonalNumberObject().getBirthdate().getMonth().equals(month))
            {
                criteriaList.add(member);
            }
        }
        
        return criteriaList;
    }
    
    
    public String toString()
    {
        String returnString = "Month: ";
       
            returnString += "" + month.toString();
        
        return returnString;
    }
    
}

