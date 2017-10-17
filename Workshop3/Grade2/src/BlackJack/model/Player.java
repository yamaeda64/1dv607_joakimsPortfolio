package BlackJack.model;

import java.util.List;
import java.util.LinkedList;

public class Player {

  private List<Card> hand;
  protected final int MAX_SCORE = 21;

  public Player()
  {
    hand = new LinkedList<Card>();
  }
  
  public void addCardtoHand(Card a_addToHand)
  {
    hand.add(a_addToHand);
  }
  
  public Iterable<Card> GetHand()
  {
    return hand;
  }
  
  public void ClearHand()
  {
    hand.clear();
  }
  
  public void ShowHand()
  {
    for(Card c : hand)
    {
      c.Show(true);
    }
  }
  
  public int CalcScore()
  {
    // the number of scores is dependant on the number of scorable values
    // as it seems there is no way to do this check at compile time in java ?!
    // cardScores[13] = {...};
    int cardScores[] = {
        2, 3, 4, 5, 6, 7, 8, 9, 10, 10 ,10 ,10, 11
    };
    assert (cardScores.length == Card.Value.Count.ordinal()) : "Card Scores array size does not match number of card values";
  
    
    int score = 0;

    for(Card c : GetHand()) {
        if (c.GetValue() != Card.Value.Hidden)
        {
            score += cardScores[c.GetValue().ordinal()];
        }
    }

    if (score > MAX_SCORE)
    {
        for(Card c : GetHand())
        {
            if (c.GetValue() == Card.Value.Ace && score > MAX_SCORE)
            {
                score -= 10;
            }
        }
    }

    return score;
  }
  
  // getter
    public int getMAX_SCORE()
    {
        return MAX_SCORE;
    }
}