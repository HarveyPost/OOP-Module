import java.util.Collections;

public class Shoe extends CardCollection {

  public Shoe(int decks) throws CardException {
    if (decks != 6 && decks != 8) {
      throw new CardException("Invalid number of decks. Shoe can only be 6 or 8 decks.");
    }
    for (int i = 0; i < decks; i++) {
      for (Card.Suit suit : Card.Suit.values()) {
        for (Card.Rank rank : Card.Rank.values()) {
          this.add(new BaccaratCard(rank, suit));
        }
      }
    }
  }

  public void shuffle() {
    Collections.shuffle(this.cards);
  }

  public Card deal() throws CardException {
    if (this.isEmpty()) {
      throw new CardException("Cannot deal from an empty shoe.");
    }
    return this.cards.remove(0);
  }
}
