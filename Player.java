package blackjack;

import java.util.Scanner;

/**
 *
 * @author Nintendo
 */
public class Player extends GameEngine implements PlayerAndDealer {
        
    private int value,
                card1,
                card2,
                newCard,
                money,
                bet;


    @Override
    public void dealFirstHand() {
        card1 = nextCard();
        card2 = nextCard();
        value = card1 + card2;
    }

    @Override
    public int cardValue() {
        return value;
    }
    
    public void hitStay() {
        Scanner y = new Scanner(System.in);
        System.out.println("Would you like to 'hit' or 'stay'?");
        String hitOrStay = y.nextLine().toLowerCase().replaceAll(" ", "");
        switch (hitOrStay) {
            case "hit":
                hit();
                break;
            case "stay":
                d.hit();
                break;
            default:
                outPrintWithDelay("Wrong input please use 'hit' or 'stay'.", 1000);
                hitStay();
        }
    }


    @Override
    public void hit() {
        newCard = nextCard();
        value += newCard;
        outPrintWithDelay("You drew a: " + newCard, 1000);
        outPrintWithDelay("Your total is " + cardValue() + "\n", 1000);
        if (cardValue() > 21) {
            outPrintWithDelay("BUST, the dealer wins", 1000);
            return;
        }
        hitStay();
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getMoney() {
        return money;
    }

    public int getBet() {
        return bet;
    }

    public int getCard1() {
        return card1;
    }

    public int getCard2() {
        return card2;
    }

}