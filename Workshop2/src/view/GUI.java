package view;/**
 * Created by joakimbergqvist on 2017-09-23.
 */

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

public class GUI implements View
{
    private Member selectedMember;
    model.MemberRegister memberRegister;
    VBox mainPane;
    TableView<Member> tableView;
    ToggleGroup radioToggle;
    RadioButton standardRadio;
    RadioButton verboseRadio;
    
    public GUI(model.MemberRegister memberRegister, VBox pane)
    {
        this.memberRegister = memberRegister;
        
        this.mainPane = pane;
        mainMenu();
    }
    
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
        Button helpButton = new Button("Help");
    
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
        buttonPane.getChildren().add(helpButton);
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
    
        TableColumn numOfBoats = new TableColumn("Boats");
        numOfBoats.setCellValueFactory(
                new PropertyValueFactory<Member, Integer>("boatCount")
        );
        tableView.getColumns().addAll(memberIdCol, firstNameCol, lastNameCol, numOfBoats);
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem editMember = new MenuItem("Edit Member");
        MenuItem deleteMember = new MenuItem("Delete Member");
        
        contextMenu.getItems().addAll(editMember,deleteMember);
        
        
        tableView.setContextMenu(contextMenu);
          //  TableColumn personalID = new TableColumn("Personal ID");
          //  numOfBoats.setCellValueFactory(
         //           new PropertyValueFactory<Member, String>("personalNumber")
         //   );
          //  tableView.getColumns().add(personalID);
        
    
        tableView.setItems(memberRegister.getMembers());
        
       
//        tableView.getColumns().add((TableColumn<Member, String>) memberRegister.getMembers());
       // stage.setScene(new Scene(group));
       // stage.show();
       
        
        
        // Actions
        
        addMemberButton.setOnAction((event) -> {
            
            AddMemberView addMemberView = new AddMemberView(memberRegister);
            
        });
        editMember.setOnAction(event ->
        {
            EditMemberView editMemberView = new EditMemberView(selectedMember, memberRegister);
        });
    
        editMemberButton.setOnAction(event ->
        {
            if(selectedMember != null)
            {
                EditMemberView editMemberView = new EditMemberView(selectedMember, memberRegister);
            }
            else
            {
                // TODO, error message that no user is selected
            }
        });
        deleteMember.setOnAction(event ->
        {
            memberRegister.deleteMember(selectedMember);
        });
        
        deleteMemberButton.setOnAction(event -> {
            memberRegister.deleteMember(selectedMember);
        });
        
        saveButton.setOnAction(event-> {
            memberRegister.exportXML(memberRegister);
            
        });
        
        tableView.setOnMouseClicked(event ->
        {
            if(tableView.getSelectionModel().getSelectedItem()!=null)
            {
                selectedMember = tableView.getSelectionModel().getSelectedItem();
            }
           
        ;
        });
        
        standardRadio.setOnAction(event ->
        {
            showStandardView();
        });
        
        verboseRadio.setOnAction(event ->
        {
            showVerboseView();
        });
        
        
        
    }
    // TableView
    private void showVerboseView()
    {
        TableColumn personalNumberCol = new TableColumn("Personal Number");
        personalNumberCol.setCellValueFactory(
                new PropertyValueFactory<Member, String>("personalNumber")
        );
       tableView.getColumns().add(personalNumberCol);
    }
    
    private void showStandardView()
    {
        tableView.getColumns().remove(4);
    }
    
    
    @Override
    public void showHelp()
    {
        
    }
    
    @Override
    public void showNormalList()
    {
        
    }
    
    @Override
    public void showVerboseList()
    {
        
    }
    
    @Override
    public void addMember()
    {
        
    }
    
    @Override
    public void editMember()
    {
        
    }
    
    @Override
    public void deleteMember()
    {
        
    }
    
    @Override
    public void addBoat()
    {
        
    }
    
    @Override
    public void editBoat()
    {
        
    }
    
    @Override
    public void deleteBoat()
    {
        
    }
    
    @Override
    public void saveMembers()
    {
        
    }
}
