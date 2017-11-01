package controller.filter;

import javafx.collections.ObservableList;
import model.Member;

import java.util.Iterator;


public class AndCriteria implements Criteria
{
    private Criteria firstCriteria;
    private Criteria secondCriteria;
    
    public AndCriteria(Criteria firstCriteria, Criteria secondCriteria)
    {
        this.firstCriteria = firstCriteria;
        this.secondCriteria = secondCriteria;
    }
    
    @Override
    public ObservableList<Member> meetCriteria(Iterator<Member> members)
    {
        ObservableList<Member> firstFilteredList = firstCriteria.meetCriteria(members);
        ObservableList<Member> criteriaList = secondCriteria.meetCriteria(firstFilteredList.iterator());
        
        return criteriaList;
    }
}
