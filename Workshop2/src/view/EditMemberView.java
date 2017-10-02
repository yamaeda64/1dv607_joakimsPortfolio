package view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * A View that showing Member Info as well as let the user to change that info.
 * Also from this view it's possible to add new boats to the member as well as
 * edit the current ones.
 */
public class EditMemberView implements BoatCRUD
{
    private Boat selectedBoat;
    private Button confirm;
    private Button cancel;
    
    /**
     * Constructor that set up all elements so the user can view member info, edit it and
     * table view for viewing and handling the members boats.
     * @param member The member that will be affected of this class
     * @param memberRegister the member collection class
     */
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
        
        HBox numberOfBoatBox = new HBox();
        Text boatText = new Text("Boats:");
        Label numberOfBoats = new Label();
       
        HBox boatBox = new HBox();
        VBox boatButtonBox = new VBox();
        TableView<Boat> boatTableView = new TableView<Boat>();
        
        Button addBoat = new Button("Add Boat");
        Button editBoat = new Button( "Edit Boat");
        Button deleteBoat = new Button("Delete Boat");
        
        HBox confirmButtonsBox = new HBox();
        confirm = new Button("Confirm");            // TODO, boat changes only takes place if click save???
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
    
        mainPane.getChildren().add(numberOfBoatBox);
        numberOfBoatBox.getChildren().add(boatText);
        numberOfBoatBox.getChildren().add(numberOfBoats);
        
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
        
        stage.show();
        
        // colors
        mainPane.setStyle("-fx-background-color: rgb(165,195,225);");
        mainText.setFill(Color.WHITE);
        
        // Setting prefilled fields
        firstNameField.setText(member.getFirstName());
        lastNameField.setText(member.getLastName());
        personalIdField.setText(member.getPersonalNumber());
        
        // Setting upp TableView for boats,
        // setting upp columns
    
        TableColumn boatTypeCol = new TableColumn("Boat Type");
        boatTypeCol.setCellValueFactory(
                new PropertyValueFactory<Boat, BoatType>("boatType")
        );
        boatTypeCol.setMinWidth(115);
    
        TableColumn lengthCol = new TableColumn("Length");
        lengthCol.setCellValueFactory(
                new PropertyValueFactory<Boat, Integer>("length")
        );
    
        TableColumn boatIdCol = new TableColumn("Boat Reg");
        boatIdCol.setCellValueFactory(
                new PropertyValueFactory<Boat, String>("boatID")
        );
        
        boatTableView.getColumns().addAll(boatTypeCol, lengthCol, boatIdCol);
        boatTableView.setItems(member.getBoatList());
        
        // Context Menu
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem editBoatContext = new MenuItem("Edit Boat");
        MenuItem deleteBoatContext = new MenuItem("Delete Boat");
    
        contextMenu.getItems().addAll(editBoatContext,deleteBoatContext);
        boatTableView.setContextMenu(contextMenu);
        
        // Binds
        
        numberOfBoats.textProperty().bind(Bindings.size(boatTableView.getItems()).asString());
        
        // Actions
        
        addBoat.setOnAction(event ->
        {
           addBoat(member, memberRegister);
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
    
        // TODO, now boat edits will change both if you click Cancel and Save
        cancel.setOnAction(event ->
                {
                    // stupid fix to make the observable list trigger a change
                    memberRegister.getMembers().set(0, memberRegister.getMembers().get(0));
                    Stage closeStage = (Stage) confirm.getScene().getWindow();
                    closeStage.close();
                }
        );
        
        editBoat.setOnAction(event ->
        {
            if(selectedBoat!= null)
            {
                editBoat(selectedBoat, member);
            }
            // TODO, show error message that no boat is selected.
        });
        
        editBoatContext.setOnAction(event ->
        {
            editBoat(selectedBoat, member);
        });
        
        deleteBoat.setOnAction(event ->
        {
            if(selectedBoat!= null)
            {
            deleteBoat(selectedBoat, member);
            selectedBoat = null;
            }
        });
        
        deleteBoatContext.setOnAction(event ->
        {
            deleteBoat(selectedBoat, member);
            selectedBoat = null;
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
    
    @Override
    public void addBoat(Member member, MemberRegister memberRegister)
    {
        AddBoatView addBoatView = new AddBoatView(member,memberRegister);
    }
    
    @Override
    public void editBoat(Boat selectedBoat, Member member)
    {
        EditBoatView addBoatView = new EditBoatView(selectedBoat, member);
    }
    
    @Override
    public void deleteBoat(Boat selectedBoat, Member member)
    {
        member.deleteBoat(selectedBoat);
    }
}