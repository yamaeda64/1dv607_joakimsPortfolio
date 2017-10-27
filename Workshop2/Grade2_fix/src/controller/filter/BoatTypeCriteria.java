package controller.filter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Boat;
import model.BoatType;
import model.Member;

import java.util.Iterator;

/**
 * Created by joakimbergqvist on 2017-10-21.
 */
public class BoatTypeCriteria implements Criteria
{
    private BoatType boatType;
    
    public BoatTypeCriteria(BoatType boatType)
    {
        this.boatType = boatType;
    }
    @Override
    public ObservableList<Member> meetCriteria(Iterator<Member> members)
    {
        ObservableList<Member> criteriaList = FXCollections.observableArrayList();
        while(members.hasNext())
        {
            Member member = members.next();
            Iterator<Boat> boats = member.getBoatIterator();
            Boolean boatLoop = true;
            while(boats.hasNext() && boatLoop)
            {
                Boat boat = boats.next();
                if(boat.getBoatType().equals(boatType))
                {
                    criteriaList.add(member);
                    boatLoop = false;
                }
            }
        }
        
        return criteriaList;
    }
    
    public String toString()
    {
        return "Boat type: " + boatType;
    }
}
