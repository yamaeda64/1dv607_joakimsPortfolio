package view;

/**
 *  An interface to make sure that a Boat can be created, edited and deleted
 */
public interface BoatCRUD
{
    /**
     * UI for user to add a new boat.
     * @param member member that should be assigned the new boat.
     */
    void addBoat(model.Member member);
    
    /**
     * UI for edit a boat.
     * @param selectedBoat the boat that are to be edited.
     * @param member Member that owns the boat
     */
    void editBoat(model.Boat selectedBoat, model.Member member);
    
    /**
     * UI to delete a boat
     * @param selectedBoat the boat that are to be removed from the system.
     * @param member the member that owned the boat.
     */
    void deleteBoat(model.Boat selectedBoat, model.Member member);
}
