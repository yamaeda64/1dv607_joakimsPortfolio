package BlackJack.controller;

import BlackJack.model.CardDealtObserver;
import BlackJack.model.Game;
import BlackJack.view.gui.Table;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class PlayGame implements CardDealtObserver
{
    private Game game;
    private Table view;
    private Pane mainPane;
    private Button playGame;
    private Button stand;
    private Button hit;
    //private Thread thread;
    
    public PlayGame(Game game, Table view) throws InterruptedException
    {
      
        this.game = game;
        this.view = view;
        
        this.mainPane = mainPane;
        
        game.getDealer().addObserverSubscriber(this);
        
        
        thread.start();
        
        playGame = view.getPlayGameButton();
        hit = view.getHitButton();
        stand = view.getStandButton();
        
        playGame.setOnAction(event ->
        {
            playGame.setVisible(false);
            hit.setVisible(true);
            stand.setVisible(true);
            view.removeCards();
            view.hideWinningMessage();
            game.NewGame();
        });
        hit.setOnAction(event->
        {
            game.Hit();
        });
        stand.setOnAction(event->
        {
            game.Stand();
        });
        
        
    }
        
     /*   //view.DisplayWelcomeMessage();
        Platform.runLater(()->
        {
            Play();
        });
        
    }*/
    
    Runnable r = new Runnable()
    {
        public void run()
        {
            
            while(Play())
            {
                
            }
        }
    };
    
   Thread thread = new Thread(r)
    {
        @Override
        public void run()
        {
            super.run();
        }
    };
    
    
    
   /* public void playThread()
    {
        new Thread(new Runnable(){
            public void run(){
                Play();
            });
        }
                
                , "Thread A").start();
    }
    */
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
        updateView();
    }
    
    @Override
    public void cardIsShowed()
    {
        view.flipCards();
        updateView();
    }
    
    public void updateView()
    {
        
        view.DisplayDealerHand(game.GetDealerHand(), game.GetDealerScore());
        view.DisplayPlayerHand(game.GetPlayerHand(), game.GetPlayerScore());
        if(game.IsGameOver())
        {
            hit.setVisible(false);
            stand.setVisible(false);
            playGame.setVisible(true);
            view.DisplayGameOver(game.IsDealerWinner());
        }
        
    }
    
    public enum PlayerState
    {
        PLAY, HIT, HOLD, QUIT, WAIT;
    }
    
}