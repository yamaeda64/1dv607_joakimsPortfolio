package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

/**
 * This Class calculates the winner between Dealer and Player.
 * If player draws with dealer, player wins, except when both hands are over the limit, then dealer wins
 */
public class PlayerAdvantageWinnerRuleStrategy implements WinnerRuleStrategy
{
    @Override
    public boolean IsDealerWinner(Dealer dealer, Player player)
    {
        if (player.CalcScore() > player.getMAX_SCORE())
        {
            return true;
        }
        else if (dealer.CalcScore() > dealer.getMAX_SCORE())
        {
            return false;
        }
        return dealer.CalcScore() > player.CalcScore();
    }
}
