package controller;

import model.Boat;
import model.BoatType;
import model.Member;
import model.MemberRegister;
import view.*;



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
        catch(Exception e)
        {
            // TODO, write error handling (user should be informed if the xml wasn't loaded)
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
        selectedMember.editMember(firstName, lastName, personalNumber);
        mainWindow.modelIsChanged();
    }
    
    public void addMemberWindow()
    {
        AddMemberView addMemberView = new AddMemberView(this);
    }
    
    public void editMemberWindow()
    {
        
        //TODO selectedMember.addSubscriber( // TODO add GUI);
        
        if(selectedMember==null)
        {
            throw new NullPointerException("No member was selected");
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
            throw new NullPointerException("No member was selected");
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
        selectedMember.deleteBoat(selectedBoat);
        selectedBoat = null;
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
        EditBoatView editBoatView = new EditBoatView(selectedBoat, this);
    }
    public MainWindow getMainWindow()
    {
        return mainWindow;
    }

}
