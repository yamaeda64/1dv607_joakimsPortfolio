package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

/**
 * This Class calculates the winner between Dealer and Player.
 */
class DealerAdvantageWinnerRuleStrategy implements WinnerRuleStrategy
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
        return dealer.CalcScore() >= player.CalcScore();
    }
    
    @Override
    public void accept(Visitor visitor)
    {
        visitor.visitDealerAdvantageWinnerRule(this);
    }
}
