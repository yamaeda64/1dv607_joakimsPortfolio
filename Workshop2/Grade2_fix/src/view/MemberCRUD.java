package view;
/**
 *  An interface to make sure that a Member can be created, edited and deleted
 */
public interface MemberCRUD
{
    /**
     * UI for adding a new member.
     */
    void addMember(String firstName, String lastName, String personalNumber);
    
    /**
     * UI to edit an existing member.
     */
    void editMember(String firstName, String lastName, String personalNumber);
    
    /**
     * Remove a member from the system.
     */
    void deleteMember();
    
    /**
     * Save list of Members and their boats.
     */
    void saveMembers();
}
