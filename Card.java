public class Card {
    private final Suit suit;
    private final Rank rank;
    private final int numericRank;
    private boolean shown;

    public Card(Suit suit, Rank rank, boolean shown){
        this.suit = suit;
        this.rank = rank;
        numericRank = rank.getNumericVal();
        this.shown = shown;
    }

    public int getNumericRank() {
        return numericRank;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean getShown(){
        return this.shown;
    }

    public void show(){
        this.shown = true;
    }

    @Override
    public String toString() {
        if(!this.shown)
            return "X";
        if(this.rank == rank.J || this.rank == rank.Q || this.rank == rank.K || this.rank == rank.A)
            return rank.toString() + "." + suit.toString();
        return rank.getNumericVal() + suit.toString();
    }
}
