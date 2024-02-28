import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Harvey Postings
 */
public class Track {
  private List<Point> points;

  public Track() {
    points = new ArrayList<>();
  }

  // TODO: Create a stub for the constructor
  public Track(String filename) throws IOException {
    this();
    readFile(filename);
  }

  // TODO: Create a stub for readFile()
  public void readFile(String filename) throws IOException {
    points.clear();
    Scanner scanner = new Scanner(new File(filename));
    if (scanner.hasNextLine()) {
      scanner.nextLine();
    }
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] fields = line.split(",");
      if (fields.length != 4) {
        throw new GPSException("Invalid number of values to create a Point object");
      }
      ZonedDateTime time = ZonedDateTime.parse(fields[0]);
      double longitude = Double.parseDouble(fields[1]);
      double latitude = Double.parseDouble(fields[2]);
      double elevation = Double.parseDouble(fields[3]);
      Point point = new Point(time, longitude, latitude, elevation);
      points.add(point);
    }
    scanner.close();
  }

  // TODO: Create a stub for add()
  public void add(Point point) {
    points.add(point);
  }

  // TODO: Create a stub for get()
  public Point get(int index) {
    if (index < 0 || index >= points.size()) {
      throw new GPSException("Index out of bounds");
    }
    return points.get(index);
  }

  // TODO: Create a stub for size()
  public int size() {
    return points.size();
  }

  // TODO: Create a stub for lowestPoint()
  public Point lowestPoint() {
    if (points.size() < 1) {
      throw new GPSException("No points in track");
    }
    Point lowest = points.get(0);
    for (int i = 1; i < points.size(); i++) {
      if (points.get(i).getElevation() < lowest.getElevation()) {
        lowest = points.get(i);
      }
    }
    return lowest;
  }

  // TODO: Create a stub for highestPoint()
  public Point highestPoint() {
    if (points.size() < 1) {
      throw new GPSException("No points in track");
    }
    Point highest = points.get(0);
    for (int i = 1; i < points.size(); i++) {
      if (points.get(i).getElevation() > highest.getElevation()) {
        highest = points.get(i);
      }
    }
    return highest;
  }

  // TODO: Create a stub for totalDistance()
  public double totalDistance() {
    if (points.size() < 2) {
      throw new GPSException("Not enough points in track");
    }
    double distance = 0.0;
    for (int i = 0; i < points.size() - 1; i++) {
      distance += Point.greatCircleDistance(points.get(i), points.get(i + 1));
    }
    return distance;
  }

  // TODO: Create a stub for averageSpeed()
  public double averageSpeed() {
    if (points.size() < 2) {
      throw new GPSException("Not enough points in track");
    }
    ZonedDateTime start = points.get(0).getTime();
    ZonedDateTime end = points.get(points.size() - 1).getTime();

    long seconds = start.until(end, ChronoUnit.SECONDS);
    if (seconds <= 0) {
      throw new GPSException("Invalid time range");
    }
    double distance = totalDistance();
    return distance / seconds;
  }
}
