package view;

import controller.CRUDController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Boat;
import model.BoatType;
import model.Member;
import model.ModelChangedObserver;

import java.util.IllegalFormatException;
import java.util.InputMismatchException;
import java.util.Iterator;

/**
 * A View that showing Member Info as well as let the user to change that info.
 * Also from this view it's possible to add new boats to the member as well as
 * edit the current ones.
 */
public class EditMemberView implements ModelChangedObserver
{
    private TableView<Boat> boatTableView;
    private Member member;
    private Button confirm;
    private Button cancel;
    private Label numberOfBoats;
    private CRUDController controller;
    private UserStrategy user;
    /**
     * Constructor that set up all elements so the user can view member info, edit it and
     * table view for viewing and handling the members boats.
     * @param inputMember The member that will be affected of this class
     */
    public EditMemberView(Member inputMember, CRUDController controller, UserStrategy user)
    {
        this.user = user;
        this.controller = controller;
        inputMember.addSubscriber(this);
        this.member = inputMember;
        
        Stage stage = new Stage();
        stage.setTitle("View Member");
        stage.initModality(Modality.APPLICATION_MODAL);
        
       
        
        stage.show();
        
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
        Text personalIdFormat = new Text("YYYYMMDD-XXXX");
    
        user.makeEditViewInfo(mainText, firstNameField, lastNameField, personalIdField);
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
    
    
        mainText.setFont(new Font(40));
    
        firstNameBox.setPadding(new Insets(0,0,0,5));
        lastNameBox.setPadding(new Insets(0,0,0,5));
        personalIdBox.setPadding(new Insets(0,0,0,5));
        firstNameBox.setSpacing(13);
        lastNameBox.setSpacing(13);
        personalIdBox.setSpacing(7);
        
        mainText.setFill(Color.WHITE);
    
        firstNameField.setText(member.getFirstName());
        lastNameField.setText(member.getLastName());
        personalIdField.setText(member.getPersonalNumber());
    
        
        HBox numberOfBoatBox = new HBox();
        Text boatText = new Text("Boats:");
        numberOfBoats = new Label();
       
        HBox boatBox = new HBox();
        VBox boatButtonBox = new VBox();
        boatTableView = new TableView<Boat>();
        
        user.makeEditViewBoatButtonBox(boatButtonBox);
       
        // adding elements to view
        
        mainPane.getChildren().add(numberOfBoatBox);
        numberOfBoatBox.getChildren().add(boatText);
        numberOfBoatBox.getChildren().add(numberOfBoats);
        
        
        mainPane.getChildren().add(boatBox);
        boatBox.getChildren().add(boatButtonBox);
       
        boatBox.getChildren().add(boatTableView);
    
        HBox confirmButtonBox = new HBox();
    
        Button confirm = new Button("Confirm");
        Button cancel = new Button("Cancel");
    
        confirmButtonBox.getChildren().add(confirm);
        confirmButtonBox.getChildren().add(cancel);
    
        user.setButtonsEditView(confirm, cancel);
        confirmButtonBox.setPadding(new Insets(10,40,10,40));
        confirmButtonBox.setSpacing(30);
        
        mainPane.getChildren().add(confirmButtonBox);
        
        
        
        
        // Setting sizes
        stage.setScene(new Scene(mainPane, 430, 500));
        personalIdFormat.setFont(new Font(12));
        
        
        // Alignments
        
        mainPane.setPadding(new Insets(10,25,10,25));
        mainPane.setSpacing(10);
        boatButtonBox.setSpacing(10);
        personalIdFormat.setLayoutY(40);
       
        stage.show();
        
        
        // colors
        mainPane.setStyle("-fx-background-color: rgb(165,195,225);");
        personalIdFormat.setFill(Color.WHITE);
        
        // Setting prefilled fields
        
        numberOfBoats.setText(" "+member.getBoatCount());
        
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
        TableRow<Member> tr = new TableRow<>();
        updateTableView();
        
        // Context Menu
        final ContextMenu contextMenu = user.makeBoatContextMenu();
        
        boatTableView.setContextMenu(contextMenu);
        
        boatTableView.setOnMouseClicked(event ->
        {
            if(boatTableView.getSelectionModel().getSelectedItem() != null)
            {
                controller.setSelectedBoat(boatTableView.getSelectionModel().getSelectedItem());
            }
        });
        
        confirm.setOnAction(event ->
        {
            {
                try
                {
                    if(firstNameField.getText().isEmpty()
                            || lastNameField.getText().isEmpty()
                            || personalIdField.getText().isEmpty())
                    {
                        //TODO, show error message
                    }   // TODO, make names start with capital
                    else
                    {
                        // fields are approved
                        controller.editMember(firstNameField.getText(),
                                lastNameField.getText(),
                                personalIdField.getText());
                        
                        controller.setSelectedBoat(null);
                        inputMember.removeSubscriber(this);
                        Stage closeStage = (Stage) confirm.getScene().getWindow();
                        closeStage.close();
                    }
                }
                catch(IllegalFormatException e)
                {
                    // Output that personal number was wrong
                }
                catch(InputMismatchException e)
                {
                    // Output that name was wrong
                }
            }
        });
        
        cancel.setOnAction(event ->
        {
            inputMember.removeSubscriber(this);
            controller.setSelectedBoat(null);
            Stage closeStage = (Stage) confirm.getScene().getWindow();
            closeStage.close();
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
    
    
    private void updateTableView()
    {
        Iterator<Boat> boatIterator = member.getBoatIterator();
        ObservableList<Boat> observableList = FXCollections.observableArrayList();
        while(boatIterator.hasNext())
        {
            observableList.add(boatIterator.next());
        }
            boatTableView.setItems(observableList);
            boatTableView.refresh();
        
    }
    private void updateBoatCount()
    {
        numberOfBoats.setText(" "+member.getBoatCount());
    }
    
    @Override
    public void modelIsChanged()
    {
        updateTableView();
        updateBoatCount();
    }
}