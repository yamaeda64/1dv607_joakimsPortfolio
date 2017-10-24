package controller.filter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Member;

import java.util.Iterator;

/**
 * Created by joakimbergqvist on 2017-10-21.
 */
public class AllMembersCriteria implements Criteria
{
    @Override
    public ObservableList<Member> meetCriteria(Iterator<Member> members)
    {
        ObservableList<Member> criteriaList = FXCollections.observableArrayList();
        while(members.hasNext())
        {
            criteriaList.add(members.next());
        }
        
        return criteriaList;
    }
}
