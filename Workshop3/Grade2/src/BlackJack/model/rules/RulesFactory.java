package BlackJack.model.rules;

public class RulesFactory {
    
    public HitStrategy GetHitRule()
    {
        return new Soft17HitStrategy();
    }
    
    public NewGameStrategy GetNewGameRule()
    {
        return new InternationalNewGameStrategy();
    }
    
    public WinnerRuleStrategy GetWinnerRule()
    {
        return new PlayerAdvantageWinnerRuleStrategy();
    }
}