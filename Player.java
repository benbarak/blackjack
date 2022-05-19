import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private int pot = 3000;
    private ArrayList<Card> hand = new ArrayList<>();

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public ArrayList<Card> getHand() {
        return new ArrayList<>(hand);
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = new ArrayList<>(hand);
    }
}
