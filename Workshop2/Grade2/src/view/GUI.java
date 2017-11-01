package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Member;

/**
 *  GUI Class is the base of the View package for the Happy Pirate Boat Club.
 *  This Class hold the GUI for the main page of the system where you can
 *  view members. But there is also buttons to handle changes in the program
 *  and a save button to export the list of members and boats.
 */

public class GUI implements MemberCRUD, BoatClubMainView
{
    private Member selectedMember;
    private model.MemberRegister memberRegister;
    private VBox mainPane;
    private TableView<Member> tableView;
    private ToggleGroup radioToggle;
    private RadioButton standardRadio;
    private RadioButton verboseRadio;
    
    /**
     * Constructor that saves the arguments in fields before calling the mainMenu
     * @param memberRegister class that holds the collection of members
     * @param pane the main pane where all graphic should be added on to be visible to user.
     */
    public GUI(model.MemberRegister memberRegister, VBox pane)
    {
        this.memberRegister = memberRegister;
        this.mainPane = pane;
        mainMenu();
    }
    
    @Override
    public void mainMenu()
    {
        // creating elements
        Text text1HappyPirate = new Text("The Happy Pirate");
        Text text2BoatClub = new Text("Boat Club");
        HBox radioButtonPane = new HBox(50);
        standardRadio = new RadioButton("Standard List");
        verboseRadio = new RadioButton("Verbose List");
        radioToggle = new ToggleGroup();
        HBox hbox = new HBox();
        VBox buttonPane = new VBox();
        tableView = new TableView<Member>();
        Button addMemberButton = new Button("Add Member");
        Button editMemberButton = new Button("Edit Member");
        Button deleteMemberButton = new Button("Delete Member");
        Button saveButton = new Button("SAVE");
        //Button helpButton = new Button("Help");   // help is replaced by a readme file
    
        // Making radiobuttons toggle
        standardRadio.setToggleGroup(radioToggle);
        verboseRadio.setToggleGroup(radioToggle);
        standardRadio.setSelected(true);
    
        // mainPane size
        mainPane.setMinSize(500, 700);
    
        // adding the elements to the view
        mainPane.getChildren().add(text1HappyPirate);
        mainPane.getChildren().add(text2BoatClub);
        mainPane.getChildren().add(radioButtonPane);
        radioButtonPane.getChildren().add(standardRadio);
        radioButtonPane.getChildren().add(verboseRadio);
        mainPane.getChildren().add(hbox);
        hbox.getChildren().add(buttonPane);
        hbox.getChildren().add(tableView);
        buttonPane.getChildren().add(addMemberButton);
        buttonPane.getChildren().add(editMemberButton);
        buttonPane.getChildren().add(deleteMemberButton);
     //   buttonPane.getChildren().add(helpButton);
        buttonPane.getChildren().add(saveButton);
        
        // position the elements in the view
        text1HappyPirate.setTextAlignment(TextAlignment.CENTER);
        text1HappyPirate.setWrappingWidth(mainPane.getMinWidth());
        text2BoatClub.setTextAlignment(TextAlignment.CENTER);
        text2BoatClub.setWrappingWidth(mainPane.getMinWidth());
        radioButtonPane.setPadding(new Insets(30, 30, 0, 0));
        radioButtonPane.setAlignment(Pos.CENTER_RIGHT);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        buttonPane.setPadding(new Insets(10,40,10,20));
        buttonPane.setSpacing(30);
    
        // conforming sizes
        text1HappyPirate.setFont(new Font(40));
        text2BoatClub.setFont(new Font(30));
        buttonPane.setMinWidth(160);
        tableView.setMinWidth(444);
    
        buttonPane.setFillWidth(true);
    
        // color the view and elements
        mainPane.setStyle("-fx-background-color: rgb(150,175,205);");
        text1HappyPirate.setFill(Paint.valueOf("white"));
        text2BoatClub.setFill(Paint.valueOf("white"));
    
        // tableView
        //setting upp columns
    
        TableColumn memberIdCol = new TableColumn("Member ID");
        memberIdCol.setCellValueFactory(
                new PropertyValueFactory<Member, Integer>("memberID")
        );
    
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Member, String>("firstName")
        );
    
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Member, String>("lastName")
        );
    
        TableColumn numOfBoatsCol = new TableColumn("Boats");
        numOfBoatsCol.setCellValueFactory(
                new PropertyValueFactory<Member, String>("boatCount")
        );
        tableView.getColumns().addAll(memberIdCol, firstNameCol, lastNameCol, numOfBoatsCol);
        
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem editMember = new MenuItem("Edit Member");
        MenuItem deleteMember = new MenuItem("Delete Member");
        
        contextMenu.getItems().addAll(editMember,deleteMember);
        
        tableView.setContextMenu(contextMenu);
        tableView.setItems(memberRegister.getMembers());
        
        // Actions
        
        addMemberButton.setOnAction((event) ->
        {
            addMember();
        });
        editMember.setOnAction(event ->
        {
            editMember();
        });
        editMemberButton.setOnAction(event ->
        {
            if(selectedMember != null)
            {
                editMember();
            }
            else
            {
                // TODO, error message that no user is selected
            }
        });
        deleteMember.setOnAction(event ->
        {
            deleteMember();
        });
        
        deleteMemberButton.setOnAction(event ->
        {
            if(selectedMember != null)
            {
                deleteMember();
            }
        });
        
        saveButton.setOnAction(event-> {
           saveMembers();
        });
        
        tableView.setOnMouseClicked(event ->
        {
            if(tableView.getSelectionModel().getSelectedItem()!=null)
            {
                selectedMember = tableView.getSelectionModel().getSelectedItem();
            }
        });
        
        standardRadio.setOnAction(event ->
        {
            showStandardView(firstNameCol);
        });
        
        verboseRadio.setOnAction(event ->
        {
            showVerboseView(firstNameCol);
        });
    }
    
    @Override
    public void showVerboseView(TableColumn firstNameCol)
    {
        TableColumn personalNumberCol = new TableColumn("Personal Number");
        personalNumberCol.setId("personalNumber");
        personalNumberCol.setCellValueFactory(
                new PropertyValueFactory<Member, String>("personalNumber")
        );
       tableView.getColumns().add(personalNumberCol);
    
        firstNameCol.setCellFactory(CustomTextFieldTableCell.forTableColumn()); // adds popup for boats,
                                        // TODO, should be changed to numOfBoatsCol but since  the is integer
                                        // TODO, that is some not yet solve issue.
    }
    @Override
    public void showStandardView(TableColumn firstNameCol)
    {
        tableView.getColumns().remove(4);
        // Removes the scrolldown of boats
        
        firstNameCol.setCellFactory(CustomTextFieldTableCell.revertTableColumn());
    }
    
    @Override
    public void addMember()
    {
        AddMemberView addMemberView = new AddMemberView(memberRegister);
    }
    
    @Override
    public void editMember()
    {
        EditMemberView editMemberView = new EditMemberView(selectedMember, memberRegister);
    }
    
    @Override
    public void deleteMember()
    {
        memberRegister.deleteMember(selectedMember);
        selectedMember = null;
    }
    
    @Override
    public void saveMembers()
    {
        memberRegister.exportXML(memberRegister);
    }
}