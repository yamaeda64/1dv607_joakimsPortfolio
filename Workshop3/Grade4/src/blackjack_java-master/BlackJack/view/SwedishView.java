package BlackJack.view;

import BlackJack.controller.PlayGame.PlayerState;
import BlackJack.model.rules.AbstractRuleFactory;
import BlackJack.model.rules.HitStrategy;
import BlackJack.model.rules.NewGameStrategy;
import BlackJack.model.rules.WinnerRuleStrategy;

public class SwedishView implements View
    {
        AbstractRuleFactory ruleFactory;
        public SwedishView(AbstractRuleFactory abstractRuleFactory)
        {
            ruleFactory = abstractRuleFactory;
        
        }
        public void DisplayWelcomeMessage()
        {
         
            for(int i = 0; i < 50; i++) {System.out.print("\n");};

            System.out.println("Hej Black Jack Världen");
            displayRules();
            System.out.println("Skriv 'p' för att Spela, 'k' för nytt kort, 's' för att stanna 'a' för att avsluta\n");
        }
        @Override
        public PlayerState getPlayerState()
        {
            try
            {
                int c = System.in.read();
                while (c == '\r' || c =='\n')
                {
                    c = System.in.read();
                }
                if(c == 'p')
                {
                    return PlayerState.PLAY;
                }
                else if(c=='k')
                {
                    return PlayerState.HIT;
                }
                else if(c=='s')
                {
                    return PlayerState.HOLD;
                }
                else if(c=='a')
                {
                    return PlayerState.QUIT;
                }
            }
            catch (java.io.IOException e)
            {
                System.out.println("" + e);
            }
            return null;
        }
    
    
        public void DisplayCard(BlackJack.model.Card a_card)
        {
            if (a_card.GetColor() == BlackJack.model.Card.Color.Hidden)
            {
                System.out.println("Dolt Kort");
            }
            else
            {
                String colors[] = 
                    { "Hjärter", "Spader", "Ruter", "Klöver" };
                String values[] =  
                    { "två", "tre", "fyra", "fem", "sex", "sju", "åtta", "nio", "tio", "knekt", "dam", "kung", "ess" };
                System.out.println("" + colors[a_card.GetColor().ordinal()] + " " + values[a_card.GetValue().ordinal()]);
            }
        }
        public void DisplayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
        {
            DisplayHand("Spelare", a_hand, a_score);
        }
        public void DisplayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
        {
            DisplayHand("Croupier", a_hand, a_score);
        }
        public void DisplayGameOver(boolean a_dealerIsWinner)
        {
            System.out.println("Slut: ");
            if (a_dealerIsWinner)
            {
                System.out.println("Croupiern Vann!");
            }
            else
            {
                System.out.println("Du vann!");
            }
        }

        private void DisplayHand(String a_name, Iterable<BlackJack.model.Card> a_hand, int a_score)
        {
            System.out.println(a_name + " Har: " + a_score);
            for(BlackJack.model.Card c : a_hand)
            {
                DisplayCard(c);
            }
            System.out.println("Poäng: " + a_score);
            System.out.println("");
        }
    
        public void displayRules()
        {
            System.out.println("----------------------------------------------------------------------------------------");
            RulePrinterVisitor rulePrinter = new RulePrinterVisitor();
        
            System.out.println("Spelregler");
            System.out.print("\t\tStart: ");
        
            NewGameStrategy newGameStrategy = ruleFactory.createNewGameStrategy();
            newGameStrategy.accept(rulePrinter);
        
            System.out.print("\t\tDealer tar nytt kort regel: ");
        
            HitStrategy hitStrategy = ruleFactory.createHitStrategy();
            hitStrategy.accept(rulePrinter);
        
            System.out.print("\t\tVinner vid lika: ");
            WinnerRuleStrategy winnerRuleStrategy = ruleFactory.createWinnerRuleStrategy();
            winnerRuleStrategy.accept(rulePrinter);
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }
