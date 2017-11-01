package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

class Soft17HitStrategy implements HitStrategy
{
    private final int hitLimit = 17;

    public boolean DoHit(Player dealer)
    {
        if(dealer.CalcScore() < hitLimit)
        {
            return true;
        }
        else if(dealer.CalcScore() == hitLimit)
        {
            for(Card c : dealer.GetHand())
            {
                if(c.GetValue()==Card.Value.Ace)
                {
                    return true;
                }
            }
        }
        return false;
    }
}