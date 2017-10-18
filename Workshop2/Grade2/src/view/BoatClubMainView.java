package view;

import javafx.scene.control.TableColumn;

/**
 * An interface to make sure that the UI has the most important functionalities
 * for the Happy Pirate Boat Club application.
 */
public interface BoatClubMainView
{
    /**
     * Main menu for the application
     */
    void mainMenu();
    
    /**
     * Show a standard view of members where member ID, member name, and members number of boats
     * will be shown.
     * @param firstNameCol the TableColumn of the firstname that is needed to remove the
     *                     drop down on mouse over that is visible when showing the verbose list
     */
    void showStandardView(TableColumn firstNameCol);
    
    /**
     * Show a verbose view of members where memberID, member name, member personal ID, number of boats
     * and a list of the members boats is shown.
     * @param firstNameCol the TableColumn of the firstname that is needed to add the
     *                     drop down on mouse over which shows a list of the members boats.
     */
    void showVerboseView(TableColumn firstNameCol);   // TODO, should be changed to numOfBoats instead
    
    /**
     * save the current list of members and their boats.
     */
    void saveMembers();
}
