package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public interface NewGameStrategy
{
    boolean newGame(Dealer dealer, Player player);
    void accept(Visitor visitor);
}
    
    