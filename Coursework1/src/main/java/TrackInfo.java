import java.io.IOException;

/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Harvey Postings
 */
public class TrackInfo {
  public static void main(String[] args) {
    // TODO: Implementation TrackInfo application here
    if (args.length != 1) {
      System.out.println("Usage: java TrackInfo <filename>");
      System.exit(0);
    }
    try {
      Track track = new Track(args[0]);
      System.out.println(track.size() + " points in track");
      System.out.println("Lowest point is " + track.lowestPoint());
      System.out.println("Highest point is " + track.highestPoint());
      System.out.format("Total distance = %.3f km", track.totalDistance() / 1000);
      System.out.println();
      System.out.format("Average speed = %.3f m/s", track.averageSpeed());
    } catch (IOException e) {
      System.out.println("Error reading file");
      System.exit(0);
    }
  }
}
