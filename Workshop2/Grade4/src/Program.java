import controller.CRUDController;
import javafx.application.Application;
import javafx.stage.Stage;

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
        CRUDController controller = new CRUDController();
        
    }
}