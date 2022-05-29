package blackjack;

import static java.lang.System.out;

/**
 *
 * @author Nintendo
 */
public class Dealer extends GameEngine implements PlayerAndDealer {

    private int value,
                card3,
                card4,
                newCard;

    @Override
    public void dealFirstHand() {
        card3 = nextCard();
        card4 = nextCard();
        value = card3 + card4;
    }

    @Override
    public int cardValue() {
        return value;
    }

    @Override
    public void hit() {
        outPrintWithDelay("Okay, dealer's turn.", 1000);
        outPrintWithDelay("His hidden card was a " + getCard4(), 1000);
        outPrintWithDelay("His total was " + cardValue() + "\n", 1000);
        while (cardValue() < 17) {
            newCard = nextCard();
            value += newCard;
            outPrintWithDelay("Dealer chooses to hit.", 1000);
            outPrintWithDelay("He draws a " + newCard, 1000);
            outPrintWithDelay("His total is " + cardValue() + "\n", 1000);
            if (cardValue() > 21) {
                out.println("Dealer BUST, you win.\n");
                p.setMoney(p.getMoney() + p.getBet() * 2);
                return;
            } else if (p.cardValue() == cardValue() && cardValue() > 16) {
                out.println("Both you and the dealer has the same amount therefore the dealer wins.\n");
            }
        }
        if (p.cardValue() < cardValue()) {
            outPrintWithDelay("The dealer has a higher amount and wins.\n", 1000);
        } else {
            outPrintWithDelay("You won!", 1000);
            p.setMoney(p.getMoney() + p.getBet() * 2);
        }
    }

    public int getCard3() {
        return card3;
    }

    public int getCard4() {
        return card4;
    }
}