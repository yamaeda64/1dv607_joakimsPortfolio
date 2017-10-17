package BlackJack;

import BlackJack.model.Game;
import BlackJack.view.*;
import BlackJack.controller.*;

public class Program
{
    
    public static void main(String[] a_args)
    {
        Game g = new Game();
        View v =  new EnglishView();  //new Swedish();
        PlayGame ctrl = new PlayGame(g, v);
        
        while (ctrl.Play())
        {
            
        }  // loop until user chooses to quit
    }
}