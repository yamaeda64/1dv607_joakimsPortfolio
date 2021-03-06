package BlackJack.model;

import BlackJack.model.rules.HitStrategy;
import BlackJack.model.rules.NewGameStrategy;
import BlackJack.model.rules.RulesFactory;
import BlackJack.model.rules.WinnerRuleStrategy;

import java.util.ArrayList;

public class Dealer extends Player
{
    private Deck deck;
    private NewGameStrategy newGameRule;
    private HitStrategy hitRule;
    private WinnerRuleStrategy winRule;
    private ArrayList<CardDealtObserver> subscribers;
    
    
    public Dealer(RulesFactory rulesFactory)
    {
        
        newGameRule = rulesFactory.GetNewGameRule();
        hitRule = rulesFactory.GetHitRule();
        winRule = rulesFactory.GetWinnerRule();
        subscribers = new ArrayList<CardDealtObserver>();
    }
    
    /**
     * Adds a subscriber who will listen to whenever a card is dealt.
     * @param subscriber The subscriber
     */
    public void addObserverSubscriber(CardDealtObserver subscriber)
    {
        this.subscribers.add(subscriber);
    }
    
    public boolean NewGame(Player player)
    {
        if (deck == null || IsGameOver())
        {
            deck = new Deck();
            ClearHand();
            player.ClearHand();
            return newGameRule.newGame(this, player);
        }
        return false;
    }
    
    public boolean Hit(Player player)
    {
        if (deck != null && player.CalcScore() < MAX_SCORE && !IsGameOver())
        {
            dealCard(player);
            
            return true;
        }
        return false;
    }
    
    public boolean IsDealerWinner(Player player)
    {
        return winRule.IsDealerWinner(this, player);
    }
    
    public boolean IsGameOver()
    {
        if (deck != null && hitRule.DoHit(this) != true)
        {
            return true;
        }
        return false;
    }
    
    public boolean Stand()
    {
        boolean returnBool;
        if(deck != null)
        {
            ShowHand();
            
            while(hitRule.DoHit(this))
            {
                returnBool = hitRule.DoHit(this);
                dealCard(this);
            }
        }
        return false;
    }
    
    public void dealCard(Player player)
    {
        dealCard(player, true);
    }
    
    public void dealCard(Player player, Boolean isShown)
    {
        Card c = deck.GetCard();
        c.Show(isShown);
        player.addCardtoHand(c);
        // notify subscribers that card is dealt
        for(CardDealtObserver subscriber : subscribers)
        {
            subscriber.cardIsDealt();
        }
    }
}