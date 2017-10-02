package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Boat;
import model.BoatType;
import model.Member;

/**
 * This class is a GUI for edit an existing boat.
 */
public class EditBoatView
{
    private Button confirm;
    private Button cancel;
    
    /**
     * Constructor that initialize and starts the window
     * with all elements the user need to edit a boat.
     * @param boat the boat that are to be edited.
     * @param member the owner of the boat.
     */
    public EditBoatView(Boat boat, Member member)
    {
        Stage stage = new Stage();
        stage.setTitle("Edit Boat");
        
        // Create elements
        VBox mainPane = new VBox();
        
        Text mainText = new Text("Edit Boat");
        
        // Change to list of boatType
        HBox boatTypeBox = new HBox();
        Text boatTypeText = new Text("Boat Type:");
        ComboBox<BoatType> boatTypeComboBox = new ComboBox<BoatType>();
        
        HBox lengthBox = new HBox();
        Text lengthText = new Text("Length:");
        TextField lengthField = new TextField();
        
        HBox registerIdBox = new HBox();
        Text registerIdText = new Text("Register ID:");
        TextField registerIdField = new TextField();
        
        HBox confirmButtonsBox = new HBox();
        confirm = new Button("Confirm");
        cancel = new Button("Cancel");
        
        
        // adding elements to view
        mainPane.getChildren().add(mainText);
        
        boatTypeBox.getChildren().add(boatTypeText);
        boatTypeBox.getChildren().add(boatTypeComboBox);
        mainPane.getChildren().add(boatTypeBox);
        
        lengthBox.getChildren().add(lengthText);
        lengthBox.getChildren().add(lengthField);
        mainPane.getChildren().add(lengthBox);
        
        registerIdBox.getChildren().add(registerIdText);
        registerIdBox.getChildren().add(registerIdField);
        mainPane.getChildren().add(registerIdBox);
        
        confirmButtonsBox.getChildren().add(confirm);
        confirmButtonsBox.getChildren().add(cancel);
        mainPane.getChildren().add(confirmButtonsBox);
        
       
       // adding the previous values to the field
        boatTypeComboBox.setValue(boat.getBoatType());
        lengthField.setText(boat.getLength()+"");
        registerIdField.setText(boat.getBoatID());
       
        // ComboBox
      
        boatTypeComboBox.setItems(FXCollections.observableArrayList(BoatType.values()));
        
        
        // Setting sizes
        stage.setScene(new Scene(mainPane, 330, 250));
        mainText.setFont(new Font(40));
        
        
        // Alignments
        
        mainPane.setPadding(new Insets(10,25,10,25));
        mainPane.setSpacing(10);
        boatTypeBox.setPadding(new Insets(0,0,0,5));
        lengthBox.setPadding(new Insets(0,0,0,5));
        registerIdBox.setPadding(new Insets(0,0,0,5));
        boatTypeBox.setSpacing(13);
        lengthBox.setSpacing(13);
        registerIdBox.setSpacing(7);
        
        confirmButtonsBox.setPadding(new Insets(10,40,10,40));
        confirmButtonsBox.setSpacing(30);
        
        stage.show();
        
        // colors
        mainPane.setStyle("-fx-background-color: rgb(165,195,225);");
        mainText.setFill(Color.WHITE);
        
        
        // Actions
        confirm.setOnAction(event ->
                {
                    if(boatTypeComboBox.getValue() == null
                            || lengthField.getText().isEmpty())      // registerID is ok to be empty)
                    {
                        //TODO, show error message
                    }
                    else if(!lengthField.getText().matches("^[0-9]*$"))
                    {
                        //TODO, show error message that length needs to be a number
                    }
                    else
                    {
                        // TODO, make names start with capital
                        boat.editBoat(boatTypeComboBox.getValue(),
                                                Integer.parseInt(lengthField.getText()),
                                registerIdField.getText());
                        // stupid fix to make tableView update on edit
                        member.getBoatList().set(0, member.getBoatList().get(0));
                        Stage closeStage = (Stage) confirm.getScene().getWindow();
                        closeStage.close();
                    }
                }
        );
        
        cancel.setOnAction(event ->
                {
                    Stage closeStage = (Stage) confirm.getScene().getWindow();
                    closeStage.close();
                }
        );
    }
    
    // Checks if a String is bigger than 20 characters
    private Boolean isTooLong(String inputString)
    {
        if(inputString.length() > 20)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    // Checks if a String is only alphabetic numbers
    private Boolean isAlphabetic(String inputString)
    {
        if(inputString.matches("[a-zA-Z]+"))     // only lets the user have alphabetical names
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
