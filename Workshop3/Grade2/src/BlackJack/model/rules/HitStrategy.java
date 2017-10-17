package BlackJack.model.rules;

import BlackJack.model.Player;

public interface HitStrategy
{
    boolean DoHit(Player a_dealer);
}