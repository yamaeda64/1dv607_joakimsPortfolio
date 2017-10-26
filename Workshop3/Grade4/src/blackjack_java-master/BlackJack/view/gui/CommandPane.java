package BlackJack.view.gui;


import BlackJack.controller.PlayGame;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CommandPane extends Pane
{
    private Button playGame;
    private Button hit;
    private Button stand;
    private PlayGame.PlayerState currentState;
    private Text winnerMessage;
    public CommandPane()
    {
        winnerMessage = new Text();
        winnerMessage.setFont(new Font(50));
        getChildren().add(winnerMessage);
        this.setStyle("-fx-background-color: lightgreen;");
        setMinSize(600,100);
        playGame = new Button("Play Game");
        hit = new Button("Hit");
        stand = new Button("Stand");
        getChildren().addAll(playGame,hit,stand);
        
        playGame.setLayoutX(230);
        playGame.setLayoutY(35);
        
        hit.setLayoutX(210);
        hit.setLayoutY(70);
        
        stand.setLayoutX(290);
        stand.setLayoutY(70);
        
        hit.setVisible(false);
        stand.setVisible(false);
        currentState = PlayGame.PlayerState.WAIT;
        
    }
    public void displayGameOver(Boolean dealerIsWinner)
    {
        stand.setVisible(false);
        hit.setVisible(false);
        
        if(dealerIsWinner)
        {
            winnerMessage.setText("You Lost");
        }
        else
        {
            winnerMessage.setText("You Won");
        }
        winnerMessage.setVisible(true);
       
    }
    
    public PlayGame.PlayerState getCurrentState()
    {
        return currentState;
    }
    
    public Button getPlayGameButton()
    {
        return playGame;
    }
    
    public Button getStandButton()
    {
        return stand;
    }
    
    public Button getHitButton()
    {
        return hit;
    }
    public void hideWinnerMessage()
    {
        winnerMessage.setVisible(false);
    }
    
}
