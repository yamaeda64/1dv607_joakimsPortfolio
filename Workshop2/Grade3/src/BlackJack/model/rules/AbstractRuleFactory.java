package BlackJack.model.rules;


public interface AbstractRuleFactory
{
    HitStrategy createHitStrategy();
    
    WinnerRuleStrategy createWinnerRuleStrategy();
    
    NewGameStrategy createNewGameStrategy();
   
}
