public class BaccaratCard extends Card {

    public BaccaratCard(Rank rank, Suit suit) {
        super(rank, suit);
    }

    @Override
    public Rank getRank() {
        return super.getRank();
    }

    @Override
    public Suit getSuit() {
        return super.getSuit();
    }

    @Override
    public String toString() {
        return "" + getRank().getSymbol() + getSuit().getSymbol();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BaccaratCard)) return false;
        BaccaratCard other = (BaccaratCard) obj;
        return this.getRank() == other.getRank() && this.getSuit() == other.getSuit();
    }

    @Override
    public int compareTo(Card o) {
        return super.compareTo(o);
    }

    // Ace is worth 1, 2-9 are worth their face value, and 10, Jack, Queen, King are worth 0.
    @Override
    public int value() {
        if (this.getRank() == Card.Rank.ACE) {
            return 1;
        } else if (this.getRank().ordinal() >= Card.Rank.TWO.ordinal() &&
                   this.getRank().ordinal() <= Card.Rank.NINE.ordinal()) {
            return this.getRank().ordinal() + 1; // Enums are zero-indexed
        } else {
            return 0;
        }
    }
}
