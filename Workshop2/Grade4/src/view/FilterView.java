package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.BoatType;

/**
 * Created by joakimbergqvist on 2017-10-21.
 */
public class FilterView extends HBox
{
    private ToggleSwitch toggle;
    private TextField nameField;
    private RadioButton olderThanRadio;
    private TextField ageField;
    private CheckBox femaleCheckBox;
    private CheckBox maleCheckBox;
    private ComboBox<BoatType> boatTypeComboBox;
    private Button updateFilterbutton;
    
    
    public FilterView()
    {
        setSpacing(34);
        Pane switchArea = new Pane();
        
        Text text = new Text("Filter Switch");
        text.setFill(Color.WHITE);
        switchArea.getChildren().add(text);
        toggle = new ToggleSwitch();
        switchArea.getChildren().add(toggle);
        
        updateFilterbutton = new Button("Update Filter Criteria");
        switchArea.getChildren().add(updateFilterbutton);
        getChildren().add(switchArea);
        VBox outerouterBox = new VBox();
        VBox leftOuterBox = new VBox();
        
        getChildren().add(outerouterBox);
        VBox rightOuterBox = new VBox();
        outerouterBox.getChildren().addAll(leftOuterBox, rightOuterBox);
    
        HBox ageBox = new HBox();
        Text memberAgeText = new Text("Member age: ");
        olderThanRadio = new RadioButton("Older than");
        RadioButton youngerThanRadio = new RadioButton("Younger than");
        ToggleGroup ageToggle = new ToggleGroup();
        olderThanRadio.setToggleGroup(ageToggle);
        youngerThanRadio.setToggleGroup(ageToggle);
        olderThanRadio.setSelected(true);
        
        ageField = new TextField();
        ageField.setPrefSize(45,15);
        ageField.setText("0");
        ageField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    ageField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        HBox nameBox = new HBox();
        
        Text nameText = new Text("Name");
        nameField = new TextField();
        nameBox.getChildren().addAll(nameText, nameField);
        
        
        HBox sexBox = new HBox();
        
        leftOuterBox.getChildren().addAll(nameBox, sexBox);
        Text sexText = new Text("Sex:   ");
        femaleCheckBox = new CheckBox("Female");
        femaleCheckBox.setSelected(true);
        
        maleCheckBox = new CheckBox("Male");
        maleCheckBox.setSelected(true);
        
        sexBox.getChildren().addAll(sexText,  femaleCheckBox, maleCheckBox);
        
        // Logic for at least one sex selected
        
        femaleCheckBox.setOnAction(event ->
                {
                    if(!femaleCheckBox.isSelected() && !maleCheckBox.isSelected())
                    {
                       maleCheckBox.setSelected(true);
                    }
                }
        );
        maleCheckBox.setOnAction(event ->
        {
            if(!maleCheckBox.isSelected() && !femaleCheckBox.isSelected())
            {
                femaleCheckBox.setSelected(true);
            }
        });
        
        leftOuterBox.getChildren().add(ageBox);
    
        HBox boatTypeBox = new HBox();
        Text boatTypeText = new Text("owns boat of type:");
        boatTypeComboBox = new ComboBox<BoatType>();
        boatTypeBox.getChildren().addAll(boatTypeText, boatTypeComboBox);
        boatTypeComboBox.setItems(FXCollections.observableArrayList(BoatType.values()));
        
        rightOuterBox.getChildren().addAll(boatTypeBox);
        
        ageBox.getChildren().addAll(memberAgeText, olderThanRadio, youngerThanRadio, ageField);

        // layout
        text.setLayoutX(15);
        text.setLayoutY(50);
        toggle.setLayoutX(20);
        toggle.setLayoutY(60);
        
        leftOuterBox.setSpacing(10);
        rightOuterBox.setSpacing(10);
    }
    
    public ToggleSwitch getToggle()
        {
            return toggle;
        }
    
    public String getSearchString()
    {
        return nameField.getText();
    }
    
    public Boolean getOlderThan()
    {
        return olderThanRadio.isSelected();
    }
    
    public int getAgeField()
    {
       if(ageField.getText().isEmpty())
       {
           return 0;
       }
       else
       {
           return Integer.parseInt(ageField.getText());
       }
    }
    
    
    public Boolean getFemaleCheckBox()
    {
        return femaleCheckBox.isSelected();
    }
    
    public Boolean getMaleCheckBox()
    {
        return maleCheckBox.isSelected();
    }
    
    public BoatType getSelectedBoatType()
    {
        return boatTypeComboBox.getValue();
    }
    
    public Button getUpdateFilterbutton()
    {
        return updateFilterbutton;
    }
    
    public class ToggleSwitch extends Pane
    {
        private Rectangle background;
        private Circle trigger;
        private Boolean toggleState;
        
        public ToggleSwitch()
        {
            toggleState = false;
            background = new Rectangle(60, 30);
            background.setArcWidth(30);
            background.setArcHeight(30);
            
            trigger = new Circle(15);
            
            trigger.setCenterX(15);
            trigger.setCenterY(15);
            trigger.setLayoutX(0); // 0 for off, 30 for on
            
            // colors
            background.setFill(Color.WHITE);
            background.setStroke(Color.LIGHTGRAY);
            trigger.setFill(Color.WHITE);
            trigger.setStroke(Color.LIGHTGRAY);
            
            getChildren().add(background);
            getChildren().add(trigger);
            background.setLayoutY(0);
            
        }
    
        public Boolean getToggleState()
        {
            return toggleState;
        }
        
        public void changeToggleState()
        {
            if(toggleState)
            {
                toggleState = false;
                trigger.setLayoutX(0);
                background.setFill(Color.WHITE);
                background.setStroke(Color.WHITE);
            }
            else
            {
                toggleState = true;
                trigger.setLayoutX(30);
                background.setFill(Color.rgb(160,230,30));
                background.setStroke(Color.rgb(160,230,30));
            }
        }
    }
}
