package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

class InternationalNewGameStrategy implements NewGameStrategy
{
    public boolean newGame(Dealer dealer, Player player)
    {
        dealer.dealCard(player);
        dealer.dealCard(dealer);
        dealer.dealCard(player);
        
        return true;
    }
    
    @Override
    public void accept(Visitor visitor)
    {
        visitor.visitInternationalNewGameStrategy(this);
    }
}