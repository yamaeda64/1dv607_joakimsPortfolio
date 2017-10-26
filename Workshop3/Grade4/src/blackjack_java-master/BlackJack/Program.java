package BlackJack;

import BlackJack.controller.PlayGame;
import BlackJack.model.Game;
import BlackJack.model.rules.SneakyDealerFactory;
import BlackJack.view.gui.Table;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application
{
    public static void main(String[] args)
    {
        
        launch();
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        
        
        SneakyDealerFactory ruleSettings = new SneakyDealerFactory();  // change here for what type of rules
        
        Game g = new Game(ruleSettings);
        
        Table v = new Table(ruleSettings, primaryStage);   // change this if other language is needed
        
        
        PlayGame ctrl = new PlayGame(g, v);

        /*
        Platform.runLater(()->
        {
            while(ctrl.Play())
            {
        
            }
        });
        
        
        */
    }
    
    
    
}