package view;

import controller.CRUDController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.IllegalFormatException;
import java.util.InputMismatchException;


public class AddMemberView
{
    private Button confirm;
    private Button cancel;
    
    public AddMemberView(CRUDController controller)
    {
        Stage stage = new Stage();
        stage.setTitle("Add Member");
        stage.initModality(Modality.APPLICATION_MODAL);
        
        // Create elements
        VBox mainPane = new VBox();
        
        Text mainText = new Text("Add Member");
        
        HBox firstNameBox = new HBox();
        Text firstNameText = new Text("First name:");
        TextField firstNameField = new TextField();
        
        HBox lastNameBox = new HBox();
        Text lastNameText = new Text("Last name:");
        TextField lastNameField = new TextField();
        
        HBox personalIdBox = new HBox();
        Text personalIdText = new Text("Personal ID:");
        TextField personalIdField = new TextField();
        Text personalIdFormat = new Text("YYYYMMDD-XXXX");
        
        HBox confirmButtonsBox = new HBox();
        confirm = new Button("Add Member");
        cancel = new Button("Cancel");
        
        
        // adding elements to view
        mainPane.getChildren().add(mainText);
        
        firstNameBox.getChildren().add(firstNameText);
        firstNameBox.getChildren().add(firstNameField);
        mainPane.getChildren().add(firstNameBox);
        
        lastNameBox.getChildren().add(lastNameText);
        lastNameBox.getChildren().add(lastNameField);
        mainPane.getChildren().add(lastNameBox);
        
        personalIdBox.getChildren().add(personalIdText);
        personalIdBox.getChildren().add(personalIdField);
        personalIdBox.getChildren().add(personalIdFormat);
        mainPane.getChildren().add(personalIdBox);
        
        confirmButtonsBox.getChildren().add(confirm);
        confirmButtonsBox.getChildren().add(cancel);
        mainPane.getChildren().add(confirmButtonsBox);
        
        
        // Setting sizes
        stage.setScene(new Scene(mainPane, 390, 250));
        mainText.setFont(new Font(40));
        personalIdFormat.setFont(new Font(14));
        
        // Alignments
        
        mainPane.setPadding(new Insets(10,25,10,25));
        mainPane.setSpacing(10);
        firstNameBox.setPadding(new Insets(0,0,0,5));
        lastNameBox.setPadding(new Insets(0,0,0,5));
        personalIdBox.setPadding(new Insets(0,0,0,5));
        firstNameBox.setSpacing(13);
        lastNameBox.setSpacing(13);
        personalIdBox.setSpacing(7);
        
        confirmButtonsBox.setPadding(new Insets(10,40,10,40));
        confirmButtonsBox.setSpacing(30);
        
        personalIdFormat.setScaleX(0.7);
        
        // Effects
        DropShadow shadow = new DropShadow();
        
        //mainText.setEffect(shadow);
        
        stage.show();
        // Hide this current window (if this is what you want)
        //((Node)(event.getSource())).getScene().getWindow().hide();
        
        // colors
        mainPane.setStyle("-fx-background-color: rgb(165,195,225);");
        mainText.setFill(Color.WHITE);
        personalIdFormat.setFill(Color.WHITE);
        
        
        // Actions
        confirm.setOnAction(event ->
                {
                    try
                    {
                        if(firstNameField.getText().isEmpty()
                                || lastNameField.getText().isEmpty()
                                || personalIdField.getText().isEmpty())
                        {
                            showErrorMessage("Empty fields", "Fill in all the fields");
                        }
                        else
                        {
                            // fields are approved
                            controller.addMember(firstNameField.getText(),
                                    lastNameField.getText(),
                                    personalIdField.getText());
        
                            Stage closeStage = (Stage) confirm.getScene().getWindow();
                            closeStage.close();
                        }
                    }
                    catch(StringIndexOutOfBoundsException e)
                    {
                        showErrorMessage("Wrong personal number format", "Check the personal number and try again");
                    }
                    
                    catch(IllegalFormatException e)
                    {
                        showErrorMessage("error", e.getMessage());
                    }
                    catch(InputMismatchException e)
                    {
                        showErrorMessage("Personal ID error", e.getMessage());
                    }
                    catch(IllegalArgumentException e)
                    {
                        showErrorMessage(e.getMessage(),"The member is probably in the list or you have entered the wrong personal number");
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
    
    private void showErrorMessage(String header, String message)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(header);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
