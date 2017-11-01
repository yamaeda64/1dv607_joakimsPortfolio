package view;

import controller.CRUDController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.BoatType;
import model.Member;

/**
 * A class that shows a GUI to add a new boat to a member of the Happy Pirate Boat Club
 */
public class AddBoatView
{
    private Button confirm;
    private Button cancel;
    private CRUDController controller;
    
    /**
     * The Constructor that initialize the stage and create all elements that is needed
     * for the user to add a new boat to the system.
     * @param member owner of the boat that are to be added
     */
    public AddBoatView(Member member, CRUDController controller)
    {
        this.controller = controller;
        Stage stage = new Stage();
        stage.setTitle("Add Boat");
        stage.initModality(Modality.APPLICATION_MODAL);
        
        // Create elements
        VBox mainPane = new VBox();
        Text mainText = new Text("Add Boat");
        
        HBox boatTypeBox = new HBox();
        Text boatTypeText = new Text("Boat Type:");
        ComboBox<BoatType> boatTypeComboBox = new ComboBox<BoatType>();
        
        HBox lengthBox = new HBox();
        Text lengthText = new Text("Length:");
        TextField lengthField = new TextField();
        Text cm = new Text("cm");
        
        HBox registerIdBox = new HBox();
        Text registerIdText = new Text("Register ID:");
        TextField registerIdField = new TextField();
        
        HBox confirmButtonsBox = new HBox();
        confirm = new Button("Add Boat");
        cancel = new Button("Cancel");
        
        // adding elements to view
        mainPane.getChildren().add(mainText);
        
        boatTypeBox.getChildren().add(boatTypeText);
        boatTypeBox.getChildren().add(boatTypeComboBox);
        mainPane.getChildren().add(boatTypeBox);
        
        lengthBox.getChildren().addAll(lengthText,lengthField,cm);
        mainPane.getChildren().add(lengthBox);
        
        registerIdBox.getChildren().add(registerIdText);
        registerIdBox.getChildren().add(registerIdField);
        mainPane.getChildren().add(registerIdBox);
        
        confirmButtonsBox.getChildren().add(confirm);
        confirmButtonsBox.getChildren().add(cancel);
        mainPane.getChildren().add(confirmButtonsBox);
        
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
                        showErrorMessage("Empty fields", "Make sure boat type and length are entered");
                    }
                    else
                    {
                        member.addBoat(boatTypeComboBox.getValue(),
                                                Integer.parseInt(lengthField.getText()),
                                registerIdField.getText());
                      //  member.notifySubscribers();
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
    
        lengthField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    lengthField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    
    private void showErrorMessage(String header, String message)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(header);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
