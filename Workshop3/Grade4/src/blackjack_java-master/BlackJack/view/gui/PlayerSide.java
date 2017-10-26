package BlackJack.view.gui;

import BlackJack.model.Card;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Created by joakimbergqvist on 2017-10-24.
 */
public class PlayerSide extends VBox
{
    private HBox playerCardPane;
    private HBox playerScorePane;
    private Label currentPlayerScore;
    
    
    public PlayerSide()
    {
        playerScorePane = new HBox();
        playerScorePane.setStyle("-fx-background-color: lightgreen;");
        playerCardPane = new HBox();
        getChildren().addAll(playerCardPane, playerScorePane);
    
        playerCardPane.setMinSize(600, 200);
        playerCardPane.setStyle("-fx-background-color: lightgreen;");
    
        Font font = new Font(30);
        Label playerScoreLabel = new Label("Player Score: ");
        playerScoreLabel.setFont(font);
        currentPlayerScore = new Label("0");
        currentPlayerScore.setFont(font);
        
        playerScorePane.getChildren().addAll(playerScoreLabel, currentPlayerScore);
    }
    
    public void setCurrentPlayerScore(int score)
    {
        currentPlayerScore.setText(""+score);
    }
    
    public void showCard(Card card)
    {
        VisualCard vCard = new VisualCard(card);
        playerCardPane.getChildren().add(vCard);
        TranslateTransition dealTransition = new TranslateTransition();
        dealTransition.setNode(vCard);
        dealTransition.setDuration(Duration.millis(300));
        dealTransition.setFromX(600);
        dealTransition.setFromY(-300);
        dealTransition.setToY(0);
        dealTransition.setToX(0);
        dealTransition.setInterpolator(Interpolator.EASE_OUT);
        dealTransition.play();
        
        
        
    }
    
    public void removeCards()
    {
      
        playerCardPane.getChildren().clear();
           
        
    }
}
