import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MemberRegister;
import view.GUI;

import javax.xml.bind.JAXBException;

/**
 * Created by joakimbergqvist on 2017-09-12.
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
            
            // TODO, write error handling
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

