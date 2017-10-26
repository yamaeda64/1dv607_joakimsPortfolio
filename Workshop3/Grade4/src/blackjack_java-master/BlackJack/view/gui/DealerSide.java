package BlackJack.view.gui;

import BlackJack.model.Card;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;


public class DealerSide extends VBox
{
    private HBox dealerScorePane;
    private Label currentDealerScore;
    private HBox dealerCardPane;
    private ArrayList<VisualCard> dealerHand;
    
    public DealerSide()
    {
        dealerHand = new ArrayList<>();
        dealerScorePane = new HBox();
        dealerScorePane.setStyle("-fx-background-color: lightgreen;");
        dealerCardPane = new HBox();
        getChildren().addAll(dealerScorePane, dealerCardPane);
        
        dealerCardPane.setMinSize(600,200);
        dealerCardPane.setStyle("-fx-background-color: lightgreen;");
        
        Font font = new Font(30);
        Label dealerScoreLabel = new Label("Dealer Score: ");
        dealerScoreLabel.setFont(font);
        currentDealerScore = new Label("0");
        currentDealerScore.setFont(font);
        dealerScorePane.getChildren().addAll(dealerScoreLabel, currentDealerScore);
        
    }
    
    public void setCurrentDealerScore(int dealerScore)
    {
        currentDealerScore.setText(""+dealerScore);
    }
    
    public void showCard(Card card)
    {
        VisualCard vCard = new VisualCard(card);
        
        dealerCardPane.getChildren().add(vCard);
        TranslateTransition dealTransition = new TranslateTransition();
        dealTransition.setNode(vCard);
        dealTransition.setDuration(Duration.millis(300));
        dealTransition.setFromX(600);
        dealTransition.setFromY(10);
        dealTransition.setToY(0);
        dealTransition.setToX(0);
        dealTransition.setInterpolator(Interpolator.EASE_OUT);
        dealTransition.play();
        
        dealerHand.add(vCard);
        
    }
    
    public void removeCards()
    {
        dealerCardPane.getChildren().clear();
    }
    
    public void flipCards()
    {
        for(VisualCard card : dealerHand)
        {
            if(card.getIsHidden())
            {
                turnAnimation(card);
            }
        }
    }
    
    private void turnAnimation(VisualCard card)
    {
       
        Timeline turnCard = new Timeline();
        turnCard.setCycleCount(1);
        KeyFrame[] keyFrames = new KeyFrame[3];
        KeyValue open = new KeyValue(card.scaleXProperty(), 1);
        KeyValue close = new KeyValue(card.scaleXProperty(), 0);
        
        EventHandler<ActionEvent> switchSideFrame = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                card.updateCard();
            }
        };
        
        keyFrames[0] = new KeyFrame(Duration.millis(000), open);
        keyFrames[1] = new KeyFrame(Duration.millis(100), switchSideFrame, close);
        keyFrames[2] = new KeyFrame(Duration.millis(200), open);
        
        turnCard.getKeyFrames().addAll(keyFrames);
        turnCard.play();
        
    }
}
