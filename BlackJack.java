package blackjack;

/**
 *
 * @author Nintendo
 */
public class BlackJack {

    public static void main(String[] args) {

        Player p = new Player();
        Dealer d = new Dealer();
        Wallet w = new Wallet();
        GameEngine game = new GameEngine();
        
        game.InitializeReferences(p, d, w);

        game.shuffleCardList();
        
        w.checkWallet();
        w.makeBet();
        w.writeMoneyToFile();
        
        p.dealFirstHand(); // Deals player hand
        d.dealFirstHand(); // Deals dealer's hand
        game.start();
        p.hitStay();
        game.newGame();
    }
}