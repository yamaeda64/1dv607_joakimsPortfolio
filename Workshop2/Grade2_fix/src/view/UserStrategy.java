package view;

import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public interface UserStrategy
{
    public VBox makeMainButtonPane();
    
    public ContextMenu makeMainContextMenu();
    
    public void makeEditViewInfo(Text mainText, TextField firstName, TextField lastName, TextField personalNumber);
    
    public void setButtonsEditView(Button confirmationButton, Button cancelButton);
    
    public void makeEditViewBoatButtonBox(VBox boatButtonBox);
    
    public ContextMenu makeBoatContextMenu();
    
}
