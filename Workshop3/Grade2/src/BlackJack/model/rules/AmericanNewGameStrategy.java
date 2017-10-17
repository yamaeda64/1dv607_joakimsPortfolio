package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

class AmericanNewGameStrategy implements NewGameStrategy
{
    
    public boolean newGame(Dealer dealer, Player player) {
        
        dealer.dealCard(player);
        dealer.dealCard(dealer);
        dealer.dealCard(player);
        
        dealer.dealCard(dealer,false);  // the last dealer card is hidden from start.
        
        return true;
    }
}