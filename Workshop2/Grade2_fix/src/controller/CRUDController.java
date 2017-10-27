package controller;

import javafx.scene.control.Alert;
import model.Boat;
import model.BoatType;
import model.Member;
import model.MemberRegister;
import view.*;

import javax.xml.bind.JAXBException;


public class CRUDController implements MemberCRUD, BoatCRUD
{
    
    Member selectedMember;
    Boat selectedBoat;
    MemberRegister memberRegister;
    UserStrategy user;
    
    MainWindow mainWindow;
    
    
    public CRUDController()
    {
        try
        {
            model.XmlHandler xmlHandler = new model.XmlHandler();
            memberRegister = xmlHandler.importXML();
        }
        catch(JAXBException e)
        {
            Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
            warning.setTitle("No members found");
            warning.setHeaderText("No member found");
            warning.setContentText("If this is the first time opening this software just click ok, otherwise contact support before adding new users");
            warning.showAndWait();
            
            if(warning.getResult().getButtonData().isCancelButton())
            {
                System.exit(10); // system quits with status 10 (user list wasn't imported from database)
            }
            
        }
        if(memberRegister == null)
        {
            memberRegister = new MemberRegister();
        }
        
        
        showLoginWindow();
        
    }
    
    private void showLoginWindow()
    {
        LoginView loginView = new LoginView(this);
    }
    
    public void showMainWindow(UserStrategy user)
    {
        this.user = user;
        mainWindow = new MainWindow(memberRegister, user, this);
    }
    
    @Override
    public void addMember(String firstName, String lastName, String personalNumber)
    {
        memberRegister.addMember(firstName, lastName, personalNumber);
    }
    
    @Override
    public void editMember(String firstName, String lastName, String personalNumber)
    {
        memberRegister.editMember(selectedMember, firstName, lastName, personalNumber);
        mainWindow.modelIsChanged();
    }
    
    public void addMemberWindow()
    {
        AddMemberView addMemberView = new AddMemberView(this);
    }
    
    public void editMemberWindow()
    {
        if(selectedMember==null)
        {
            showErrorMessage("No member was selected", "Please select a member and try again");
        }
        else
        {
            EditMemberView editMemberView = new EditMemberView(selectedMember, this, user);
            selectedMember.addSubscriber(mainWindow);
        }
    }
    
    public void addBoatWindow()
    {
        AddBoatView addBoatView = new AddBoatView(selectedMember,this);
    }
    
    @Override
    public void deleteMember()
    {
        if(selectedMember==null)
        {
            showErrorMessage("No member was selected", "Please select a member and try again");
        }
        else
        {
            memberRegister.deleteMember(selectedMember);
            selectedMember = null;
        }
    }
    
    @Override
    public void saveMembers()
    {
        memberRegister.exportXML();
    }
    
    @Override
    public void addBoat(BoatType type, int cm, String regID)
    {
        selectedMember.addBoat(type,cm,regID);
    }
    
    @Override
    public void editBoat(BoatType type, int cm, String regID)
    {
        selectedMember.editBoat(selectedBoat,type,cm,regID);
    }
    
    @Override
    public void deleteBoat()
    {
        if(selectedBoat!=null)
        {
            selectedMember.deleteBoat(selectedBoat);
            selectedBoat = null;
        }
        else
        {
            showErrorMessage("No boat was selected", "Please select a boat and try again");
        }
    }
    
    public void setSelectedMember(Member member)
    {
        selectedMember = member;
    }
    
    public Member getSelectedMember()
    {
        return selectedMember;
    }
    
    public void setSelectedBoat(Boat boat)
    {
        selectedBoat = boat;
    }
    
    public Boat getSelectedBoat()
    {
        return selectedBoat;
    }
    
    public void setUser(UserStrategy user)
    {
        this.user = user;
    }
    
    public void editBoatWindow()
    {
        if(selectedBoat != null)
        {
            EditBoatView editBoatView = new EditBoatView(selectedBoat, this);
        }
        else
        {
            showErrorMessage("No boat was selected", "Please select a boat and try again");
        }
        
    }
    public MainWindow getMainWindow()
    {
        return mainWindow;
    }
    
    private void showErrorMessage(String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}