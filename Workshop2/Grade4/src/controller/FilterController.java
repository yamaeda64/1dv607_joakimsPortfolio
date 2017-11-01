package controller;

import controller.filter.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import view.FilterView;
import view.MainWindow;

import java.time.Month;
import java.util.ArrayList;


public class FilterController
{
    FilterView filterView;
    FilterView.ToggleSwitch toggle;
    MainWindow mainWindow;
    Criteria activeCriteria;
    Button updateFilterButton;
    CriteriaFactory criteriaFactory;
    
    
    public FilterController(MainWindow mainWindow)
    {
        criteriaFactory = new CriteriaFactory();
        activeCriteria = criteriaFactory.getAllMembersCriteria();
        this.mainWindow = mainWindow;
        filterView = new FilterView();
        toggle = filterView.getToggle();
        toggle.setOnMouseClicked(event->
        {
            toggle.changeToggleState();
            if(toggle.getToggleState())
            {
                updateCriteria();
            }
            else
            {
                mainWindow.setCriteria(criteriaFactory.getAllMembersCriteria());
                mainWindow.modelIsChanged();
            }
            
        });
        
        updateFilterButton = filterView.getUpdateFilterbutton();
        updateFilterButton.setOnAction(event ->
        {
            ArrayList<Criteria> criteriaList = new ArrayList<>();
            
            if(filterView.getSearchString() != null)
            {
                criteriaList.add(criteriaFactory.getNameCriteria(filterView.getSearchString()));
            }
            
            if(filterView.getFemaleCheckBox() && filterView.getMaleCheckBox())
            {
                // Do nothing since both are selected
            } else if(filterView.getFemaleCheckBox())
            {
                criteriaList.add(criteriaFactory.getFemaleCriteria(true));
            } else
            {
                criteriaList.add(criteriaFactory.getFemaleCriteria(false)); // get Males
            }
            
            if(filterView.getSelectedBoatType() != null)
            {
                criteriaList.add(criteriaFactory.getBoatTypeCriteria(filterView.getSelectedBoatType()));
            }
            
            if(filterView.getOlderThan())
            {
                criteriaList.add(criteriaFactory.getAgeCriteria(true, filterView.getAgeField()));
            } else
            {
                criteriaList.add(criteriaFactory.getAgeCriteria(false, filterView.getAgeField()));
            }
            
            
            if(criteriaList.isEmpty())
            {
                activeCriteria = criteriaFactory.getAllMembersCriteria();
                updateCriteria();
            }
            else if(criteriaList.size() == 1)
            {
                activeCriteria = criteriaList.get(0);
                updateCriteria();
            }
            else
            {
                activeCriteria = criteriaFactory.getMultipleAndCriteria(criteriaList);
                updateCriteria();
            }
        });
        
    }
    
    private void updateCriteria()
    {
        if(toggle.getToggleState())
        {
            mainWindow.setCriteria(activeCriteria);
            mainWindow.modelIsChanged();
        }
    }
    
    public HBox getFilterView()
    {
        return filterView;
    }
    
    // Hardcoded advanced search-example (not implemented)
    public void hardCodedSearchExample()
    {
        BirthMonthCriteria birthMonthCriteria = new BirthMonthCriteria(Month.JANUARY);
        AgeCriteria ageCriteria = new AgeCriteria(true,18);
        NameCriteria nameCriteria = new NameCriteria("ni");
        AndCriteria andCriteria = new AndCriteria(ageCriteria,nameCriteria);
        OrCriteria baseCriteria = new OrCriteria(birthMonthCriteria,andCriteria);
        
        activeCriteria = baseCriteria;
    }
}
