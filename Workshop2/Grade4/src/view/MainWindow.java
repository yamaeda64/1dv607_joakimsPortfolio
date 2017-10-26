package view;

import controller.CRUDController;
import controller.FilterController;
import controller.filter.AllMembersCriteria;
import controller.filter.Criteria;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Member;
import model.MemberRegister;
import model.ModelChangedObserver;

/**
 *  MainWindow Class is the base of the View package for the Happy Pirate Boat Club.
 *  This Class hold the GUI for the main page of the system where you can
 *  view members. But there is also buttons to handle changes in the program
 *  and a save button to export the list of members and boats.
 */

public class MainWindow implements  BoatClubMainView, ModelChangedObserver
{
    private MemberRegister memberRegister;
    private VBox mainPane;
    private TableView<Member> tableView;
    private ToggleGroup radioToggle;
    private RadioButton standardRadio;
    private RadioButton verboseRadio;
    private UserStrategy user;
    private CRUDController controller;
    private FilterController filterController;
    private Criteria filter;
    
    /**
     * Constructor that saves the arguments in fields before calling the mainMenu
     * @param inputMemberRegister class that holds the collection of members
     */
    public MainWindow(MemberRegister inputMemberRegister, UserStrategy user, CRUDController controller)
    {
        filterController = new FilterController(this);
        filter = new AllMembersCriteria();
        this.controller = controller;
        this.user = user;
        Stage stage = new Stage();
        
        stage.setTitle("Happy Pirate Boat Club");
        memberRegister = inputMemberRegister;
        memberRegister.addSubscriber(this);
        this.memberRegister = memberRegister;
        this.mainPane = new VBox();
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setMinWidth(650);
        stage.setMinHeight(700);
        stage.show();
        mainPane.setMinSize(650,700);
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
        
        VBox buttonPane = user.makeMainButtonPane();
        
        tableView = new TableView<Member>();
        HBox filerPane = filterController.getFilterView();
        
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
        mainPane.getChildren().add(filerPane);
        
        
        // position the elements in the view
        text1HappyPirate.setTextAlignment(TextAlignment.CENTER);
        text1HappyPirate.setWrappingWidth(mainPane.getMinWidth());
        text2BoatClub.setTextAlignment(TextAlignment.CENTER);
        text2BoatClub.setWrappingWidth(mainPane.getMinWidth());
        radioButtonPane.setPadding(new Insets(30, 30, 0, 0));
        radioButtonPane.setAlignment(Pos.CENTER_RIGHT);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        
        
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
        updateTableView();
        
        final ContextMenu contextMenu = user.makeMainContextMenu();
        
        tableView.setContextMenu(contextMenu);
        
        tableView.setOnMouseClicked(event ->
        {
            if(tableView.getSelectionModel().getSelectedItem()!=null)
            {
                controller.setSelectedMember(tableView.getSelectionModel().getSelectedItem());
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
    
    
    public void updateTableView()
    {

        tableView.setItems(filter.meetCriteria(memberRegister.getMemberIterator()));
        tableView.refresh();
    }
    
    @Override
    public void modelIsChanged()
    {
        updateTableView();
    }
    
    
    public void setCriteria(Criteria filter)
    {
        this.filter = filter;
    }
    
}