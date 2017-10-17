package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public interface WinnerRuleStrategy
{
    boolean IsDealerWinner(Dealer dealer, Player player);
}