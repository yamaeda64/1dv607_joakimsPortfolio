package BlackJack.view;


import BlackJack.model.rules.HitStrategy;
import BlackJack.model.rules.NewGameStrategy;
import BlackJack.model.rules.Visitor;
import BlackJack.model.rules.WinnerRuleStrategy;

/**
 * Created by joakimbergqvist on 2017-10-18.
 */
public class RulePrinterVisitor implements Visitor
{
    @Override
    public void visitBasicHitStrategy(HitStrategy basicHitStrategy)
    {
        System.out.println("\"Basic Hit\"");
    }
    
    @Override
    public void visitSoft17HitStrategy(HitStrategy soft17HitStrategy)
    {
        System.out.println("\"Soft 17\" ");
    }
    
    @Override
    public void visitInternationalNewGameStrategy(NewGameStrategy internationalNewGameStrategy)
    {
        System.out.println("\"International\"");
    }
    
    @Override
    public void visitAmericanNewGameStrategy(NewGameStrategy americanNewGameStrategy)
    {
        System.out.println("\"American\"");
    }
    
    @Override
    public void visitPlayerAdvantageWinnerRule(WinnerRuleStrategy playerAdvantageWinnerRule)
    {
        System.out.println("\"Player Advantage\"");
    }
    
    @Override
    public void visitDealerAdvantageWinnerRule(WinnerRuleStrategy dealerAdvantageWinnerRule)
    {
        System.out.println("\"Dealer Advantage\"");
    }
}
