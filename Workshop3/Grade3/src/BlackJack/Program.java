package BlackJack;

import BlackJack.controller.PlayGame;
import BlackJack.model.Game;
import BlackJack.model.rules.AbstractRuleFactory;
import BlackJack.model.rules.PlayersParadiseFactory;
import BlackJack.view.EnglishView;
import BlackJack.view.View;

public class Program
{
    public static void main(String[] args)
    {
        AbstractRuleFactory ruleSettings = new PlayersParadiseFactory();  // change here for what type of rules
        
        Game g = new Game(ruleSettings);
        View v =  new EnglishView(ruleSettings);   // change this if other language is needed
        PlayGame ctrl = new PlayGame(g, v);
        
        while (ctrl.Play())
        {
            
        }  // loop until user chooses to quit
    }
}