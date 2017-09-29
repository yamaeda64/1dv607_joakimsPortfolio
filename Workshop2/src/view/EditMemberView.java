package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Boat;
import model.BoatType;
import model.Member;
import model.MemberRegister;

/**
 * Created by joakimbergqvist on 2017-09-27.
 */
public class EditMemberView
{
    private Boat selectedBoat;
    private Button confirm;
    private Button cancel;
    
    public EditMemberView(Member member, MemberRegister memberRegister)
    {
        
        Stage stage = new Stage();
        stage.setTitle("Edit Member");
        
        // Create elements
        VBox mainPane = new VBox();
        
        Text mainText = new Text("Edit Member");
        
        HBox firstNameBox = new HBox();
        Text firstNameText = new Text("First name:");
        TextField firstNameField = new TextField();
        
        HBox lastNameBox = new HBox();
        Text lastNameText = new Text("Last name:");
        TextField lastNameField = new TextField();
        
        HBox personalIdBox = new HBox();
        Text personalIdText = new Text("Personal ID:");
        TextField personalIdField = new TextField();
        
        HBox numberOfBoatBoat = new HBox();
        Text boatText = new Text("Boats:");
        Text numberOfBoats = new Text("" + member.getBoatCount()); //TODO, update so boatcount get added when adding boat
        
        HBox boatBox = new HBox();
        VBox boatButtonBox = new VBox();
        TableView<Boat> boatTableView = new TableView<Boat>();
    
        
        Button addBoat = new Button("Add Boat");
        Button editBoat = new Button( "Edit Boat");
        Button deleteBoat = new Button("Delete Boat");
        
        HBox confirmButtonsBox = new HBox();
        confirm = new Button("Save");            // TODO, boat changes only takes place if click save???
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
        mainPane.getChildren().add(personalIdBox);
    
        mainPane.getChildren().add(numberOfBoatBoat);
        numberOfBoatBoat.getChildren().add(boatText);
        numberOfBoatBoat.getChildren().add(numberOfBoats);
        
        mainPane.getChildren().add(boatBox);
        boatBox.getChildren().add(boatButtonBox);
       
        boatBox.getChildren().add(boatTableView);
        
        boatButtonBox.getChildren().add(addBoat);
        boatButtonBox.getChildren().add(editBoat);
        boatButtonBox.getChildren().add(deleteBoat);
        
        confirmButtonsBox.getChildren().add(confirm);
        confirmButtonsBox.getChildren().add(cancel);
        mainPane.getChildren().add(confirmButtonsBox);
    
       
        
        // Setting sizes
        stage.setScene(new Scene(mainPane, 430, 500));
        mainText.setFont(new Font(40));
        
        
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
        
        // Effects
        DropShadow shadow = new DropShadow();
        
        //mainText.setEffect(shadow);
        
        stage.show();
        // Hide this current window (if this is what you want)
        //((Node)(event.getSource())).getScene().getWindow().hide();
        
        // colors
        mainPane.setStyle("-fx-background-color: rgb(165,195,225);");
        mainText.setFill(Color.WHITE);
        
        // Setting prefilled fields
        firstNameField.setText(member.getFirstName());
        lastNameField.setText(member.getLastName());
        personalIdField.setText(member.getPersonalNumber());
        
        // Setting upp TableView for boats
    
        // tableView
        //setting upp columns
    
        TableColumn boatTypeCol = new TableColumn("Boat Type");
        boatTypeCol.setCellValueFactory(
                new PropertyValueFactory<Member, BoatType>("boatType")
        );
        boatTypeCol.setMinWidth(115);
    
        TableColumn lengthCol = new TableColumn("Length");
        lengthCol.setCellValueFactory(
                new PropertyValueFactory<Member, Integer>("length")
        );
    
        TableColumn boatIdCol = new TableColumn("Boat Reg");
        boatIdCol.setCellValueFactory(
                new PropertyValueFactory<Member, String>("boatID")
        );
    
        
        boatTableView.getColumns().addAll(boatTypeCol, lengthCol, boatIdCol);
        
        // Context Menu
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem editBoatContext = new MenuItem("Edit Boat");
        MenuItem deleteBoatContext = new MenuItem("Delete Boat");
    
        contextMenu.getItems().addAll(editBoatContext,deleteBoatContext);
    
    
        boatTableView.setContextMenu(contextMenu);
        //  TableColumn personalID = new TableColumn("Personal ID");
        //  numOfBoats.setCellValueFactory(
        //           new PropertyValueFactory<Member, String>("personalNumber")
        //   );
        //  tableView.getColumns().add(personalID);
    
    
        boatTableView.setItems(member.getBoatList());
        
        // Actions
        
        addBoat.setOnAction(event ->
        {
            AddBoatView addBoatView = new AddBoatView(member,memberRegister);
        });
        
        confirm.setOnAction(event ->
                {
                    if(firstNameField.getText().isEmpty()
                            || lastNameField.getText().isEmpty()
                            || personalIdField.getText().isEmpty())
                    {
                        //TODO, show error message
                    }
                    else
                    {
                        member.editMember(firstNameField.getText(),
                                                lastNameField.getText(),
                                                personalIdField.getText());
                        
                        // stupid fix to make the observable list trigger a change
                        memberRegister.getMembers().set(0, memberRegister.getMembers().get(0));
    
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
        
        
        editBoat.setOnAction(event ->
        {
            // TODO, add a editBoat window here
        });
        
        editBoatContext.setOnAction(event ->
        {
            // TODO, add a editBoat window here
        });
        
        deleteBoat.setOnAction(event ->
        {
            member.deleteBoat(selectedBoat);
        });
        
        deleteBoatContext.setOnAction(event ->
        {
            member.deleteBoat(selectedBoat);
        });
        
        
        boatTableView.setOnMouseClicked(event ->
        {
            if(boatTableView.getSelectionModel().getSelectedItem() != null)
            {
                selectedBoat = boatTableView.getSelectionModel().getSelectedItem();
            }
        });
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
