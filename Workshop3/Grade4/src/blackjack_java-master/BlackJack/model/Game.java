package BlackJack.model;

import BlackJack.model.rules.AbstractRuleFactory;

public class Game
{
  private Dealer dealer;
  private Player player;
  
  public Game(AbstractRuleFactory ruleSettings)
  {
    dealer = new Dealer(ruleSettings);
    player = new Player();
  }
  
  
  public boolean IsGameOver()
  {
    return dealer.IsGameOver();
  }
  
  public boolean IsDealerWinner()
  {
    return dealer.IsDealerWinner(player);
  }
  
  public boolean NewGame()
  {
    return dealer.NewGame(player);
  }
  
  public boolean Hit()
  {
    return dealer.Hit(player);
  }
  
  public boolean Stand()
  {
    return dealer.Stand();
  }
  
  public Iterable<Card> GetDealerHand()
  {
    return dealer.GetHand();
  }
  
  public Iterable<Card> GetPlayerHand()
  {
    return player.GetHand();
  }
  
  public int GetDealerScore()
  {
    return dealer.CalcScore();
  }
  
  public int GetPlayerScore()
  {
    return player.CalcScore();
  }
  
  // Getters & Setters
  
  public Dealer getDealer()
  {
    return this.dealer;
  }
}