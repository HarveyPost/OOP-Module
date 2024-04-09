import java.util.Scanner;

public class Baccarat {

  private static int roundsPlayed = 0;
  private static int playerWins = 0;
  private static int bankerWins = 0;
  private static int ties = 0;
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
      boolean interactiveMode = args.length > 0 && (args[0].equals("-i") || args[0].equals("--interactive"));

      try {
          Shoe shoe = new Shoe(6);
          shoe.shuffle();

          while (shoe.size() > 6) {  // Ensure there are enough cards for a round
              playRound(shoe);
              roundsPlayed++;

              if (interactiveMode && !userWantsToContinue()) {
                  break;  // Exit if the user doesn't want to continue
              }
          }

          displayEndOfGameStatistics();
      } catch (CardException e) {
          System.err.println("An error occurred: " + e.getMessage());
      } finally {
          scanner.close();
      }
  }

  private static void playRound(Shoe shoe) throws CardException {
      BaccaratHand playerHand = new BaccaratHand();
      BaccaratHand bankerHand = new BaccaratHand();

      // Deal initial two cards to each hand
      playerHand.add((BaccaratCard) shoe.deal());
      bankerHand.add((BaccaratCard) shoe.deal());
      playerHand.add((BaccaratCard) shoe.deal());
      bankerHand.add((BaccaratCard) shoe.deal());

      System.out.println("\nRound " + (roundsPlayed + 1) + ":");
      System.out.println("Player hand: " + playerHand + " = " + playerHand.value());
      System.out.println("Banker hand: " + bankerHand + " = " + bankerHand.value());

      // Check for naturals
      if (!playerHand.isNatural() && !bankerHand.isNatural()) {
          BaccaratCard playerThirdCard = null;
          // Apply drawing rules
          if (playerHand.value() <= 5) {
              playerThirdCard = (BaccaratCard) shoe.deal();
              playerHand.add(playerThirdCard);
              System.out.println("Player dealt " + playerThirdCard);
          }

          if (bankerShouldDraw(playerHand, bankerHand, playerThirdCard)) {
              BaccaratCard bankerThirdCard = (BaccaratCard) shoe.deal();
              bankerHand.add(bankerThirdCard);
              System.out.println("Banker dealt " + bankerThirdCard);
          }

          System.out.println("Player hand: " + playerHand + " = " + playerHand.value());
          System.out.println("Banker hand: " + bankerHand + " = " + bankerHand.value());
      }

      // Determine round outcome
      String result = determineRoundOutcome(playerHand, bankerHand);
      updateStatistics(result);
  }

  private static boolean bankerShouldDraw(BaccaratHand playerHand, BaccaratHand bankerHand, BaccaratCard playerThirdCard) {
      int bankerTotal = bankerHand.value();
      if (playerHand.size() == 2) {  // Player stands
          return bankerTotal <= 5;
      }

      // Check for null since the player may not always draw a third card
      int playerThirdCardValue = playerThirdCard != null ? playerThirdCard.value() : -1;

      switch (bankerTotal) {
          case 0:
          case 1:
          case 2:
              return true;
          case 3:
              return playerThirdCardValue != 8;
          case 4:
              return playerThirdCardValue >= 2 && playerThirdCardValue <= 7;
          case 5:
              return playerThirdCardValue >= 4 && playerThirdCardValue <= 7;
          case 6:
              return playerThirdCardValue == 6 || playerThirdCardValue == 7;
          default:
              return false;  // Banker stands if total is 7
      }
  }

  private static String determineRoundOutcome(BaccaratHand playerHand, BaccaratHand bankerHand) {
      int playerValue = playerHand.value();
      int bankerValue = bankerHand.value();

      if (playerValue == bankerValue) {
          System.out.println("Tie");
          return "tie";
      } else if (playerValue > bankerValue) {
          System.out.println("Player win!");
          return "player";
      } else {
          System.out.println("Banker win!");
          return "banker";
      }
  }

  private static void updateStatistics(String result) {
      switch (result) {
          case "player":
              playerWins++;
              break;
          case "banker":
              bankerWins++;
              break;
          case "tie":
              ties++;
              break;
      }
  }

  private static boolean userWantsToContinue() {
      System.out.print("Another round? (y/n): ");
      String input = scanner.nextLine();
      return input.trim().equalsIgnoreCase("y");
  }

  private static void displayEndOfGameStatistics() {
      System.out.println("\nGame Over!");
      System.out.println("Rounds Played: " + roundsPlayed);
      System.out.println("Player Wins: " + playerWins);
      System.out.println("Banker Wins: " + bankerWins);
      System.out.println("Ties: " + ties);
  }
}
