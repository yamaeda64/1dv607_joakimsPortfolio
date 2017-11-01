package view;

import controller.CRUDController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class AdminUserStrategy implements UserStrategy
{
    CRUDController controller;
    
    
    public AdminUserStrategy(CRUDController controller)
    {
        this.controller = controller;
    }
    
    
    @Override
    public VBox makeMainButtonPane()
    {
        VBox buttonPane = new VBox();
        
        Button addMemberButton = new Button("Add Member");
        Button editMemberButton = new Button("Edit Member");
        Button deleteMemberButton = new Button("Delete Member");
        Button saveButton = new Button("SAVE");
        
        buttonPane.getChildren().add(addMemberButton);
        buttonPane.getChildren().add(editMemberButton);
        buttonPane.getChildren().add(deleteMemberButton);
        buttonPane.getChildren().add(saveButton);
        
        buttonPane.setPadding(new Insets(10,40,10,20));
        buttonPane.setSpacing(30);
        
        addMemberButton.setOnAction((event) ->
        {
            addMember();
        });
        
        editMemberButton.setOnAction(event ->
        {
            editMember();
        });
        
        deleteMemberButton.setOnAction(event ->
        {
            deleteMember();
        });
        saveButton.setOnAction(event ->
        {
            controller.saveMembers();
        });
        
        return buttonPane;
    }
    
    
    @Override
    public ContextMenu makeMainContextMenu()
    {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem editMember = new MenuItem("Edit Member");
        MenuItem deleteMember = new MenuItem("Delete Member");
        
        contextMenu.getItems().addAll(editMember,deleteMember);
        
        // Actions
        
        
        editMember.setOnAction(event ->
        {
            if(controller.getSelectedMember() != null)
            {
                editMember();
            }
        });
        
        deleteMember.setOnAction(event ->
        {
            if(controller.getSelectedMember() != null)
            {
                deleteMember();
            }
        });
        
        return contextMenu;
    }
    
    @Override
    public void makeEditViewInfo(Text mainText, TextField firstName, TextField lastName, TextField personalNumber)
    {
        mainText.setText("Edit Member");
        firstName.setEditable(true);
        lastName.setEditable(true);
        personalNumber.setEditable(true);
    }
    
    
    @Override
    public void setButtonsEditView(Button confirmButton, Button cancelButton)
    {
        // Standard is ok.
    }
    
    @Override
    public void makeEditViewBoatButtonBox(VBox boatButtonBox)
    {
        Button addBoat = new Button("Add Boat");
        Button editBoat = new Button( "Edit Boat");
        Button deleteBoat = new Button("Delete Boat");
        boatButtonBox.getChildren().add(addBoat);
        boatButtonBox.getChildren().add(editBoat);
        boatButtonBox.getChildren().add(deleteBoat);
        
        addBoat.setOnAction(event ->
        {
            controller.addBoatWindow();
        });
        
        editBoat.setOnAction(event ->
        {
            controller.editBoatWindow();
        });
        
        deleteBoat.setOnAction(event ->
        {
            controller.deleteBoat();
        });
    }
    
    @Override
    public ContextMenu makeBoatContextMenu()
    {
        // Context Menu
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem editBoatContext = new MenuItem("Edit Boat");
        MenuItem deleteBoatContext = new MenuItem("Delete Boat");
    
        contextMenu.getItems().addAll(editBoatContext,deleteBoatContext);
        
        
        // ADD Listners
        
        editBoatContext.setOnAction(event ->
        {
            if(controller.getSelectedBoat() != null)
            {
                controller.editBoatWindow();
            }
        });
        
        deleteBoatContext.setOnAction(event ->
        {
            if(controller.getSelectedBoat() != null)
            {
                controller.deleteBoat();
            }
        });
        
        return contextMenu;
    }
    
    @Override
    public void addButtonsEditView(VBox vBox)
    {
        
    }
    
    public void addMember()
    {
        controller.addMemberWindow();
    }
    
    
    public void editMember()
    {
        
        try
        {
            controller.editMemberWindow();
        }
        catch(NullPointerException e)
        {
            // TODO Show error message that no member was selected.
        }
        
    }
    
    public void deleteMember()
    {
        try
        {
            controller.deleteMember();
        }
        catch(NullPointerException e)
        {
            //TODO add error message
        }
    }
}
