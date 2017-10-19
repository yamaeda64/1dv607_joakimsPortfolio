package BlackJack.view;

import BlackJack.controller.PlayGame.PlayerState;
import BlackJack.model.rules.AbstractRuleFactory;
import BlackJack.model.rules.HitStrategy;
import BlackJack.model.rules.NewGameStrategy;
import BlackJack.model.rules.WinnerRuleStrategy;

public class EnglishView implements View
{
    private AbstractRuleFactory ruleFactory;
    
    public EnglishView(AbstractRuleFactory abstractRuleFactory)
    {
        ruleFactory = abstractRuleFactory;
        
    }
    public void DisplayWelcomeMessage()
    {
        for(int i = 0; i < 50; i++) {System.out.print("\n");};
        System.out.println("Hello Black Jack World");
        displayRules();
        
        System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
    }
    
    @Override
    public PlayerState getPlayerState()
    {
        try {
            int c = System.in.read();
            while (c == '\r' || c =='\n')
            {
                c = System.in.read();
            }
            if(c == 'p')
            {
                return PlayerState.PLAY;
            }
            else if(c=='h')
            {
                return PlayerState.HIT;
            }
            else if(c=='s')
            {
                return PlayerState.HOLD;
            }
            else if(c=='q')
            {
                return PlayerState.QUIT;
            }
        } catch (java.io.IOException e) {
            System.out.println("" + e);
            
        }
        return null;
    }
    
    public void DisplayCard(BlackJack.model.Card a_card)
    {
        System.out.println("" + a_card.GetValue() + " of " + a_card.GetColor());
    }
    
    public void DisplayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
    {
        DisplayHand("Player", a_hand, a_score);
    }
    
    public void DisplayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
    {
        DisplayHand("Dealer", a_hand, a_score);
    }
    
    private void DisplayHand(String a_name, Iterable<BlackJack.model.Card> a_hand, int a_score)
    {
        System.out.println(a_name + " Has: ");
        for(BlackJack.model.Card c : a_hand)
        {
            DisplayCard(c);
        }
        System.out.println("Score: " + a_score);
        System.out.println("");
    }
    
    public void DisplayGameOver(boolean a_dealerIsWinner)
    {
        System.out.println("GameOver: ");
        if (a_dealerIsWinner)
        {
            System.out.println("Dealer Won!");
        }
        else
        {
            System.out.println("You Won!");
        }
        
    }
    public void displayRules()
    {
        System.out.println("----------------------------------------------------------------------------------------");
        RulePrinterVisitor rulePrinter = new RulePrinterVisitor();
        
        System.out.println("Game settings");
        System.out.print("\t\tStart: ");
        
        NewGameStrategy newGameStrategy = ruleFactory.createNewGameStrategy();
        newGameStrategy.accept(rulePrinter);
    
        System.out.print("\t\tHit style: ");
    
        HitStrategy hitStrategy = ruleFactory.createHitStrategy();
        hitStrategy.accept(rulePrinter);
    
        System.out.print("\t\tWinning when draw: ");
        WinnerRuleStrategy winnerRuleStrategy = ruleFactory.createWinnerRuleStrategy();
        winnerRuleStrategy.accept(rulePrinter);
        System.out.println("----------------------------------------------------------------------------------------");
    }
}
