package controller.filter;

import javafx.collections.ObservableList;
import model.Member;

import java.util.ArrayList;
import java.util.Iterator;


public class MultipleAndCriteria implements Criteria
{
    private ArrayList<Criteria> criteriaInput;
    
    
    public MultipleAndCriteria(ArrayList<Criteria> criteriaList)
    {
        if(criteriaList.isEmpty())
        {
            throw new NullPointerException("No criteria was included");
        }
        else
        {
            this.criteriaInput = criteriaList;
        }
    }
    
    @Override
    public ObservableList<Member> meetCriteria(Iterator<Member> members)
    {
        
        ObservableList<Member> criteriaList = criteriaInput.get(0).meetCriteria(members);
        for(int i = 1; i < criteriaInput.size(); i++)
        {
            criteriaList = criteriaInput.get(i).meetCriteria(criteriaList.iterator());
        }
        return criteriaList;
    }
}
