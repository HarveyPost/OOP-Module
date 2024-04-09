public class BaccaratHand extends CardCollection {

  // Creates a BaccaratHand which is initially empty.
  public BaccaratHand() {
    super();
  }

  public void addCard(BaccaratCard card) {
      if (card != null) {
          cards.add(card);
      }
  }

  public int size() {
      return cards.size();
  }

  @Override
  public int value() {
    int baccaratValue = 0;
    for (Card card : cards) {
      baccaratValue += card.value();
    }
    // In Baccarat, the value of a hand is the rightmost digit of the sum of its cards' values
    return baccaratValue % 10;
  }

  public boolean isNatural() {
    return this.size() == 2 && (this.value() == 8 || this.value() == 9);
  }

  @Override
  public String toString() {
      StringBuilder sb = new StringBuilder();
      for (Card card : cards) {
          if (card instanceof BaccaratCard) {
              BaccaratCard baccaratCard = (BaccaratCard) card;
              // Append the two-character string representation of the card
              sb.append(baccaratCard.getRank().getSymbol());
              sb.append(baccaratCard.getSuit().getSymbol());
              sb.append(" "); // Separate cards by spaces
          }
      }
      return sb.toString().trim(); // Trim the trailing space
  }
}
  