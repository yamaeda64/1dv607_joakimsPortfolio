package controller.filter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Member;

import java.util.Iterator;


public class NameCriteria implements Criteria
{
    private String searchString;
    
    public NameCriteria(String searchInput)
    {
        this.searchString = searchInput;
    }
    
    @Override
    public ObservableList<Member> meetCriteria(Iterator<Member> members)
    {
        ObservableList<Member> criteriaList = FXCollections.observableArrayList();
        while(members.hasNext())
        {
            Member member = members.next();
            String fullName = member.getFirstName() + " " + member.getLastName();
            if(fullName.toLowerCase().contains(searchString.toLowerCase()))
            {
                criteriaList.add(member);
            }
        }
        
        return criteriaList;
    }
    
    
    public String toString()
    {
        return "Name contains \"" + searchString + "\"";
    }
    
}

