package controller.filter;

import javafx.collections.ObservableList;
import model.Member;

import java.util.Iterator;


public interface Criteria
{
    ObservableList<Member> meetCriteria(Iterator<Member> members);
    
}

