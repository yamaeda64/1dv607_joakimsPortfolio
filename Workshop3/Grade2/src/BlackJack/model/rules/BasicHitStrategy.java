package BlackJack.model.rules;

import BlackJack.model.Player;

class BasicHitStrategy implements HitStrategy
{
    private final int hitLimit = 17;
    
    public boolean DoHit(Player dealer)
    {
        return dealer.CalcScore() < hitLimit;
    }
    
}
