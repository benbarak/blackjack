import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int pot = 3000;
    static ArrayList<Card> hand;
    static int bet = 0;
    static boolean burn = false;

    public static void main(String[] args){
        ArrayList<Card> deck = new ArrayList<>();
        for(Suit suit : Suit.values()){
            for(Rank rank : Rank.values()){
                deck.add(new Card(suit, rank));
            }
        }

        instructions();

        while(pot > 0){
            shuffle();
            placeBet();
            deal();
            play();
            dealerPlay();
            result();
        }
    }

    private static void instructions(){
        System.out.println("Welcome to the BlackJack casino.\nYour pot: 3000");
    }

    private static void placeBet(){
        System.out.println("Place your Bet");
        int temp = scanner.nextInt();
        while(!isValidBet(bet))
            placeBet();
        bet = temp;
        pot -= bet;
    }

    private static boolean isValidBet(int bet){
        if(bet > pot){
            System.out.println("Can't bet more than you have");
            return false;
        }
        if(bet < 0){
            System.out.println("Bet must be positive");
            return false;
        }
        return true;
    }

    private static void play(){
        if(isBurn()){
            System.out.println("BURN!");
            burn = true;
            return;
        }
        System.out.println("Enter 1 for hit\nEnter 2 for stand");
        int decision = scanner.nextInt();
        while(!isValidDecisioin(decision))
            play();
        switch(decision){
            case 1:
                hit();
                break;
            case 2:
                return;
        }
        play();
    }

    private static boolean isBurn(){
        return playerResult() > 21;
    }

    private static int playerResult(){
        int sum = 0;
        int aces = 0;
        for(Card card : hand){
            sum += card.getNumericRank();
            if(card.getRank() == Rank.A)
                aces++;
        }
        while(sum > 21 && aces > 0){
            sum -= 10;
            aces--;
        }
        return sum;
    }
}
