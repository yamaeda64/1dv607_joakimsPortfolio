package BlackJack.model.rules;

public class PlayersParadiseFactory implements AbstractRuleFactory
{
    
    @Override
    public HitStrategy createHitStrategy()
    {
        return new BasicHitStrategy();
    }
    
    @Override
    public WinnerRuleStrategy createWinnerRuleStrategy()
    {
        return new PlayerAdvantageWinnerRuleStrategy();
    }
    
    @Override
    public NewGameStrategy createNewGameStrategy()
    {
        return new InternationalNewGameStrategy();
    }
}