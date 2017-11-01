package BlackJack.view;

import BlackJack.controller.PlayGame.PlayerState;

public interface View
{
  void DisplayWelcomeMessage();
  
  /**
   * This function takes input from user and return enumerator PlayerState
   * @return PlayerState, {PLAY, HIT, HOLD, QUIT}
   */
  PlayerState getPlayerState();
  void DisplayCard(BlackJack.model.Card a_card);
  void DisplayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score);
  void DisplayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score);
  void DisplayGameOver(boolean a_dealerIsWinner);
}