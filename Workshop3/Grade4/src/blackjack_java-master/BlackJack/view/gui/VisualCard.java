package BlackJack.view.gui;

import BlackJack.model.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Created by joakimbergqvist on 2017-10-25.
 */
public class VisualCard extends ImageView
{
    private Card card;
    private File file;
    private Boolean isHidden;
    private Image image;
    
    public VisualCard(Card card)
    {
        this.card = card;
        String fileString = "resources/" + card.GetValue().toString().toLowerCase() + "_of_" +
                card.GetColor().toString().toLowerCase() + ".png";
        
        File file = new File(fileString);
        if(card.GetValue() == Card.Value.Hidden)
        {
            isHidden = true;
            
        }
        else
        {
            isHidden = false;
        }
        
       try
       {
          image = new Image(file.toURI().toURL().toString());
          
           setImage(image);
          
       }
       catch(Exception e)
        {
            System.out.println("HittadeinteFilen : Visual Card"); // TODO
        }
       
        setFitHeight(100);
        setPreserveRatio(true);
    }
    
    public void updateCard()
    {

        String fileString = "resources/" + card.GetValue().toString().toLowerCase() + "_of_" +
                card.GetColor().toString().toLowerCase() + ".png";
    
        File file = new File(fileString);
    
        try
        {
            image = new Image(file.toURI().toURL().toString());
        
            setImage(image);
        
        } catch(Exception e)
        {
            System.out.println("HittadeinteFilen : Visual Card"); // TODO
        }
        isHidden = false;
    }
        public Card.Value getCardValue()
        {
            return card.GetValue();
        }
        
        public boolean getIsHidden()
        {
            return isHidden;
        }
    
        
}
