public class Card {
    private final Suit suit;
    private final Rank rank;
    private final int numericRank;

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
        numericRank = rank.getNumericVal();
    }

    public int getNumericRank() {
        return numericRank;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        if(this.rank == rank.J || this.rank == rank.Q || this.rank == rank.K || this.rank == rank.A)
            return rank.toString() + "." + suit.toString();
        return rank.getNumericVal() + suit.toString();
    }
}
