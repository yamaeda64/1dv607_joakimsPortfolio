package controller.filter;

import model.BoatType;
import java.util.ArrayList;

public class CriteriaFactory
{
    public Criteria getAgeCriteria(Boolean olderThan, int age)
    {
        return new AgeCriteria(olderThan, age);
    }
    
    public Criteria getAllMembersCriteria()
    {
        return new AllMembersCriteria();
    }
    
    public Criteria getAndCriteria(Criteria firstCriteria, Criteria secondCriteria)
    {
        return new AndCriteria(firstCriteria, secondCriteria);
    }
    
    public Criteria getBoatTypeCriteria(BoatType boatType)
    {
        return new BoatTypeCriteria(boatType);
    }
    
    public Criteria getFemaleCriteria(Boolean isFemale)
    {
        return new FemaleCriteria(isFemale);
    }
    
    public Criteria getMultipleAndCriteria(ArrayList<Criteria> criteriaList)
    {
        return new MultipleAndCriteria(criteriaList);
    }
    
    public Criteria getNameCriteria(String seachString)
    {
        return new NameCriteria(seachString);
    }
}
