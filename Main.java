import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int pot = 3000;
    static ArrayList<Card> hand = new ArrayList<>();
    static ArrayList<Card> dealerHand = new ArrayList<>();
    static int bet = 0;
    static boolean burn = false;
    static boolean dealerBurn = false;
    static int deckIndex = 0;
    static ArrayList<Card> deck = new ArrayList<>();
    static boolean blackjack = false;

    public static void main(String[] args){
        for(Suit suit : Suit.values()){
            for(Rank rank : Rank.values()){
                deck.add(new Card(suit, rank, false));
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
            clear();
        }
    }

    private static void instructions(){
        System.out.println("Welcome to the BlackJack casino.\nYour pot: 3000");
    }

    private static void placeBet(){
        System.out.println("Place your Bet");
        int temp = scanner.nextInt();
        if(!isValidBet(temp))
            placeBet();
        else{
            bet = temp;
            pot -= bet;
        }
    }

    private static boolean isValidBet(int bet){
        if(bet > pot){
            System.out.println("Can't bet more than you have");
            return false;
        }
        if(bet <= 0){
            System.out.println("Bet must be positive");
            return false;
        }
        return true;
    }

    private static void play(){
        if(blackjack)
            return;
        if(isBurn(true)){
            System.out.println("BURN!");
            burn = true;
            return;
        }
        System.out.println("Enter 1 for hit\nEnter 2 for stand");
        int decision = scanner.nextInt();
        while(!isValidDecision(decision))
            play();
        switch(decision){
            case 1:
                hit(true);
                break;
            case 2:
                return;
        }
        play();
    }

    //true == player    false == dealer
    private static boolean isBurn(boolean player){
        return calResult(player) > 21;
    }

    //true == player    false == dealer
    private static int calResult(boolean player){
        ArrayList<Card> cards;
        if(player)
            cards = hand;
        else
            cards = dealerHand;
        int sum = 0;
        int aces = 0;
        for(Card card : cards){
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


    private static boolean isValidDecision(int dec){
        return dec == 1 || dec == 2;
    }

    //true == player    false == dealer
    private static void hit(boolean player){
        ArrayList<Card> cards;
        if(player)
            cards = hand;
        else
            cards = dealerHand;
        cards.add(deck.get(deckIndex++));
        cards.get(cards.size()-1).show();
        printCards();
    }

    private static void shuffle(){
        Collections.shuffle(deck);
    }

    private static void deal(){
        hand.add(deck.get(deckIndex++)); hand.get(hand.size()-1).show();
        hand.add(deck.get(deckIndex++)); hand.get(hand.size()-1).show();
        dealerHand.add(deck.get(deckIndex++));
        dealerHand.add(deck.get(deckIndex++)); dealerHand.get(dealerHand.size()-1).show();
        printCards();
        if(calResult(true) == 21)
            blackjack = true;
    }

    private static void blackjack(){
        blackjack = true;
    }

    private static void dealerPlay(){
        if(burn || blackjack)
            return;
        while(calResult(false) < 17)
            hit(false);
        dealerHand.get(0).show();
    }

    private static void result(){
        if(blackjack){
            System.out.println("BLACKJACK!");
            pot += 3*bet;
        }
        printCards();
        if(burn || (!dealerBurn && calResult(true) <= calResult(false)))
            System.out.println("Dealer won");
        else{
            System.out.println("You won!");
            pot += 2*bet;
        }
    }

    private static void clear(){
        hand.clear();
        dealerHand.clear();
        bet = 0;
        burn = false;
        dealerBurn = false;
        deckIndex = 0;
        blackjack = false;
    }

    private static void printCards(){
        System.out.print("You: ");
        for(Card card : hand)
            System.out.print(card.toString() + "| ");
        System.out.println();
        System.out.print("Dealer: ");
        for(Card card : dealerHand)
            System.out.print(card.toString() + "| ");
        System.out.println();
    }
}
