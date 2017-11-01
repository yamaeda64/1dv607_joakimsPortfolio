package BlackJack.model.rules;

/**
 * Created by joakimbergqvist on 2017-10-18.
 */
public interface Visitor
{
    public void visitBasicHitStrategy(HitStrategy basicHitStrategy);
    public void visitSoft17HitStrategy(HitStrategy soft17HitStrategy);
    public void visitInternationalNewGameStrategy(NewGameStrategy internationalNewGameStrategy);
    public void visitAmericanNewGameStrategy(NewGameStrategy americanNewGameStrategy);
    public void visitPlayerAdvantageWinnerRule(WinnerRuleStrategy playerAdvantageWinnerRule);
    public void visitDealerAdvantageWinnerRule(WinnerRuleStrategy dealerAdvantageWinnerRule);
}
