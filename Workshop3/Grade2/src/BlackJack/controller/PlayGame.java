package BlackJack.controller;

import BlackJack.model.CardDealtObserver;
import BlackJack.view.View;
import BlackJack.model.Game;


public class PlayGame implements CardDealtObserver
{
  private Game game;
  private View view;
  
  public PlayGame(Game game, View view)
  {
    this.game = game;
    this.view = view;
    
    game.getDealer().addObserverSubscriber(this);
    
    view.DisplayWelcomeMessage();
  }
  public boolean Play()
  {
    
    if (game.IsGameOver())
    {
      view.DisplayGameOver(game.IsDealerWinner());
    }
    
    PlayerState input = view.getPlayerState();
    
    if (input == PlayerState.PLAY)
    {
      game.NewGame();
    }
    else if (input == PlayerState.HIT)
    {
      game.Hit();
    }
    else if (input == PlayerState.HOLD)
    {
      game.Stand();
    }
    
    return input != PlayerState.QUIT;
  }
  
  @Override
  public void cardIsDealt()
  {
    try
    {
      Thread.sleep(1000);
    }
    catch(InterruptedException e)
    {
      e.printStackTrace();
    }
    updateView();
  }
  
  public void updateView()
  {
    view.DisplayWelcomeMessage();
    
    view.DisplayDealerHand(game.GetDealerHand(), game.GetDealerScore());
    view.DisplayPlayerHand(game.GetPlayerHand(), game.GetPlayerScore());
  }
  
  public enum PlayerState
  {
    PLAY, HIT, HOLD, QUIT;
  }
  
}