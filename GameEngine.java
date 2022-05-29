package blackjack;

import java.util.*;

public class GameEngine {

    static Player p; // Player
    static Dealer d; // Dealer
    static Wallet w; // Wallet

    public void InitializeReferences(Player p, Dealer d, Wallet w) {
        GameEngine.p = p;
        GameEngine.d = d;
        GameEngine.w = w;
    }

    private final static List<Integer> deck = Arrays.asList(
            2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5,
            5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8,
            8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11);
    
    private static int nextCard = 0;

    public void shuffleCardList() {
        Collections.shuffle(deck);
        nextCard = 0;
    }

    Integer nextCard() {
        return deck.get(nextCard++);
    }

    public void newGame() {
        Scanner s = new Scanner(System.in);
        w.writeMoneyToFile();
        System.out.println("Would you like to play again? 'yes' or 'no'");
        String yesorno = s.nextLine().toLowerCase().replaceAll(" ", "");
        switch (yesorno) {
            case "yes":
            case "ye":
            case "y":
                BlackJack.main(null);
                break;
            default:
                System.out.println("Thank you for playing.");
                System.exit(0);
        }
    }

    public void start() {
        outPrintWithDelay("The dealer draws a: " + d.getCard3() + " showing and a hidden card.", 1000);
        outPrintWithDelay("His total is hidden, too.\n", 1000);
        outPrintWithDelay("Also draws you a: " + p.getCard1() + " - " + p.getCard2() + ".", 1000);
        if (p.cardValue() == 21) {
            System.out.println("BLACKJACK! You win!");
            p.setMoney(p.getMoney() + p.getBet() * 3);
            newGame();
        }
        outPrintWithDelay("Your total is: " + p.cardValue() + "\n", 1000);
    }

    public void outPrintWithDelay(String outprint, int delay) {
        System.out.println(outprint);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {}
    }
}