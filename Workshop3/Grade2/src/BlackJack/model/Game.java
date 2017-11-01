package BlackJack.model;

public class Game
{
  private Dealer dealer;
  private Player player;
  
  public Game()
  {
    dealer = new Dealer(new BlackJack.model.rules.RulesFactory());
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