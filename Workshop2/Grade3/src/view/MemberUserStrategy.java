package view;

import controller.CRUDController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Member;


public class MemberUserStrategy implements UserStrategy
{
    CRUDController controller;
    
    public MemberUserStrategy(CRUDController controller)
    {
        this.controller = controller;
    }
    
    @Override
    public VBox makeMainButtonPane()
    {
        VBox buttonPane = new VBox();
        
        Button editMemberButton = new Button("View Selected Member");
        
        buttonPane.getChildren().add(editMemberButton);
    
        buttonPane.setPadding(new Insets(10,10,10,5));
        buttonPane.setSpacing(10);
        
        editMemberButton.setOnAction(event ->
        {
           editMember();
        });
        
        return buttonPane;
    }
    
    
    @Override
    public ContextMenu makeMainContextMenu()
    {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem editMember = new MenuItem("View Member");
        
        contextMenu.getItems().addAll(editMember);
        
        
        // Actions
        
        editMember.setOnAction(event ->
        {
            editMember();
        });
        
    
        return contextMenu;
    }
    
    @Override
    public void makeEditViewInfo(Text mainText, TextField firstName, TextField lastName, TextField personalNumber)
    {
        mainText.setText("View Member");
        firstName.setEditable(false);
        lastName.setEditable(false);
        personalNumber.setEditable(false);
    }
    
    @Override
    public void setButtonsEditView(Button confirmationButton, Button cancelButton)
    {
        confirmationButton.setVisible(false);
        cancelButton.setText("OK");
    }
    
    @Override
    public void makeEditViewBoatButtonBox(VBox boatButtonBox)
    {
        
    }
    
    @Override
    public ContextMenu makeBoatContextMenu()
    {
        return null;
    }
    
    
    public void makeEditViewBoatButtonBox(Member member, VBox boatButtonBox)
    {
       
    }
    
    @Override
    public void addButtonsEditView(VBox vBox)
    {
        
    }
    
    
    public void editMember()
    {
        try
        {
            controller.editMemberWindow();
        }
        catch(NullPointerException e)
        {
            // Show error message that no member was selected.
        }
    }
}
