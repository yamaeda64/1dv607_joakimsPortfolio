import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MemberRegister;
import view.GUI;

import javax.xml.bind.JAXBException;

/**
 * The main program file that executes the Application and creates the needed classes from
 * both view package and model package to be able to run the program
 */
public class Program extends Application
{
    public static void main(String[] args) throws JAXBException
    {
        launch();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        MemberRegister memberRegister = null;
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
        VBox pane = new VBox();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        GUI gui = new GUI(memberRegister, pane);
        primaryStage.show();
    }
}