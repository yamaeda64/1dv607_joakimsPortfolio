package BlackJack.model.rules;

public class SneakyDealerFactory implements AbstractRuleFactory
{
    
    @Override
    public HitStrategy createHitStrategy()
    {
        return new Soft17HitStrategy();
    }
    
    @Override
    public WinnerRuleStrategy createWinnerRuleStrategy()
    {
        return new DealerAdvantageWinnerRuleStrategy();
    }
    
    @Override
    public NewGameStrategy createNewGameStrategy()
    {
        return new AmericanNewGameStrategy();
    }
}