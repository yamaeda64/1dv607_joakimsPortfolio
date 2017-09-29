package view;

/**
 * Created by joakimbergqvist on 2017-09-13.
 */
public interface View
{
    void mainMenu();
    
    void showHelp();
    
    void showNormalList();
    void showVerboseList();
    
    void addMember();
    void editMember();
    void deleteMember();
    
    void addBoat();
    void editBoat();
    void deleteBoat();
    
    void saveMembers();
}
