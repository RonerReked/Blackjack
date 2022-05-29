package blackjack;

import java.io.*;
import static java.lang.System.out;
import java.util.*;

/**
 *
 * @author Nintendo
 */
public class Wallet extends Player {

    private Scanner scanWallet;
    private final String pathToWallet = "C:\\Users\\Nintendo\\Documents\\NetBeansProjects\\BlackJack\\src\\main\\java\\blackjack\\Wallet.txt";

    public void writeMoneyToFile() {
        try (Formatter file = new Formatter(pathToWallet)) {
            file.format("%d\n", p.getMoney()); // %d for ints and %s for strings
        } catch (FileNotFoundException ex) {
            System.out.println("Unwrittable file! Make sure it exists and is not a directory.");
        }
    }

    /**
     *
     */
    public void makeBet() {
        final Scanner s = new Scanner(System.in);
        if (p.getMoney() <= 0) {
            out.println("You're out of chipmarks, refilling with 100.");
            p.setMoney(100);
            writeMoneyToFile();
            scanWallet.close();
            BlackJack.main(null);
        } else {
            out.println("Wallet: " + p.getMoney() + " Place your bet: ");
            try {
                p.setBet(s.nextInt());
            } catch (InputMismatchException e) {
                out.println("Bad input, use numbers only! 1-" + p.getMoney());
                BlackJack.main(null);
            }
            scanWallet.close();
        }
        if (p.getBet() <= p.getMoney() && p.getBet() > 0) {
            p.setMoney(p.getMoney() - p.getBet());
            out.println("Placing bet: " + p.getBet() + "\n");
        } else {
            out.println("insufficient fund.");
               makeBet();
        }
    }

    public void checkWallet() {
        try {
            scanWallet = new Scanner(new File(pathToWallet));
        } catch (FileNotFoundException ex) {
            out.println("Cannot find file! Make sure it exists and is not a directory.");
        }
        while (scanWallet.hasNext()) {
            p.setMoney(scanWallet.nextInt());
        }
    }
}