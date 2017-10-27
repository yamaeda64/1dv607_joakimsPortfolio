package controller.filter;

import javafx.collections.ObservableList;
import model.Member;

import java.util.Iterator;


public class OrCriteria implements Criteria
{
    private Criteria firstCriteria;
    private Criteria secondCriteria;
    
    public OrCriteria(Criteria firstCriteria, Criteria secondCriteria)
    {
        this.firstCriteria = firstCriteria;
        this.secondCriteria = secondCriteria;
    }
    
    @Override
    public ObservableList<Member> meetCriteria(Iterator<Member> members)
    {
        
        ObservableList<Member> criteriaList = firstCriteria.meetCriteria(members);
       
    
        ObservableList<Member> otherList = secondCriteria.meetCriteria(members);
        for(Member member:criteriaList)
        {
            if(!criteriaList.contains(member))
            {
                criteriaList.add(member);
            }
        }
        
        return criteriaList;
    }
}
