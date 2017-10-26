package BlackJack.view.gui;

import BlackJack.controller.PlayGame;
import BlackJack.model.Card;
import BlackJack.model.rules.AbstractRuleFactory;
import BlackJack.view.View;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by joakimbergqvist on 2017-10-24.
 */
public class Table extends VBox implements View
{
    private DealerSide dealerSide;
    
    private CommandPane commandPane;
    
    private PlayerSide playerSide;
    
    ArrayList<Card> recentDealerHand;
    ArrayList<Card> recentPlayerHand;
    ArrayList<Card> hiddenCards;
    
    
    public Table(AbstractRuleFactory abstractRuleFactory, Stage primaryStage)
    {
        primaryStage.setOnCloseRequest(e ->
        {
            System.exit(0);
        });
        
      
        recentDealerHand = new ArrayList<Card>();
        recentPlayerHand = new ArrayList<Card>();
        
        dealerSide = new DealerSide();
        commandPane = new CommandPane();
        playerSide = new PlayerSide();
        
        getChildren().addAll(dealerSide,commandPane,playerSide) ;
         
        
        Scene scene = new Scene(this);
        primaryStage.setScene(scene);
        primaryStage.show();
        setPrefSize(600,400);
        setMinSize(800,600);
        
        
    }
    
    @Override
    public void DisplayWelcomeMessage()
    {
       
    }
    
    @Override
    public PlayGame.PlayerState getPlayerState()
    {
       
        
        while(commandPane.getCurrentState() == PlayGame.PlayerState.WAIT)
        {
            // wait until user presses button
        }
        
        return commandPane.getCurrentState();
    }
    
    @Override
    public void DisplayCard(Card a_card)
    {
        System.out.println("PlayGame:DisplayCard");
    }
    
    @Override
    public void DisplayPlayerHand(Iterable<Card> a_hand, int a_score)
    {
        
        Iterator<Card> iterator = a_hand.iterator();
        while(iterator.hasNext())
        {
            Card nextCard = iterator.next();
            if(!recentPlayerHand.contains(nextCard))
            {
                recentPlayerHand.add(nextCard);
                playerSide.showCard(nextCard);
            }
        }
        playerSide.setCurrentPlayerScore(a_score);
  
        
    }
    
    @Override
    public void DisplayDealerHand(Iterable<Card> a_hand, int a_score)
    {
        Iterator<Card> iterator = a_hand.iterator();
        while(iterator.hasNext())
        {
            Card nextCard = iterator.next();
            if(!recentDealerHand.contains(nextCard))
            {
                recentDealerHand.add(nextCard);
                dealerSide.showCard(nextCard);
            }
        }
        
        dealerSide.setCurrentDealerScore(a_score);
       
    
    
    }
    
    @Override
    public void DisplayGameOver(boolean a_dealerIsWinner)
    {
        commandPane.displayGameOver(a_dealerIsWinner);
    }
    
    public Button getPlayGameButton()
    {
        return commandPane.getPlayGameButton();
    }
    public Button getStandButton()
    {
        return commandPane.getStandButton();
    }
    public Button getHitButton()
    {
        return commandPane.getHitButton();
    }
    
    public void removeCards()
    {
        recentPlayerHand.clear();
        playerSide.removeCards();
        recentDealerHand.clear();
        dealerSide.removeCards();
    }
    
    public void hideWinningMessage()
    {
        commandPane.hideWinnerMessage();
    }
    
    public void flipCards()
    {
        dealerSide.flipCards();
    }
}
