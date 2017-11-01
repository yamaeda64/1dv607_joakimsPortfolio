package view;

import model.BoatType;

/**
 *  An interface to make sure that a Boat can be created, edited and deleted
 */
public interface BoatCRUD
{

    void addBoat(BoatType type, int cm, String regID);

    void editBoat(BoatType type, int cm, String regID);
    
    void deleteBoat();
}
