package controller.filter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Member;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * Created by joakimbergqvist on 2017-10-21.
 */
public class AgeCriteria implements Criteria
{
    private Boolean olderThan; // compares if older than input if true, otherwise younger than
    private int age;
    
    public AgeCriteria(Boolean olderThan, int age)
    {
        this.olderThan = olderThan;
        this.age = age;
    }
    @Override
    public ObservableList<Member> meetCriteria(Iterator<Member> members)
    {
        ObservableList<Member> criteriaList = FXCollections.observableArrayList();
        while(members.hasNext())
        {
            Member member = members.next();
            LocalDate birthDate = member.getPersonalNumberObject().getBirthdate();
            if(olderThan)
            {
                if(birthDate.plusYears(age).isBefore(LocalDate.now()))
                {
                    criteriaList.add(member);
                }
            }
            else
            {
                if(birthDate.plusYears(age).isAfter(LocalDate.now()))
                {
                    criteriaList.add(member);
                }
            }
            
        }
        
        return criteriaList;
    }
    
    
    public String toString()
    {
        String string = "";
        if(olderThan)
        {
            string += "older than ";
        }
        else
        {
            string += "younger than ";
        }
        string += age + " years";
        return string;
    }
    
}

