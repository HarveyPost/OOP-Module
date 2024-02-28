import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * JavaFX application to plot elevations of a GPS track, for the
 * Advanced task of COMP1721 Coursework 1.
 *
 * @author Harvey Postings
 */
public class PlotApplication extends Application {
  // If attempting the Advanced task, implement your plotting code here.
  // You will need to modify this class definition so that it extends
  // javafx.application.Application

  private static String filename;

  public void start(Stage stage) {
    NumberAxis xAxis = new NumberAxis();
    xAxis.setLabel("Distance (m)");
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Elevation (m)");

    LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    lineChart.setTitle("Elevation profile");

    XYChart.Series<Number, Number> series = new XYChart.Series<>();
    series.setName("Elevation vs Distance");
    loadDataFromFile(series);

    Scene scene = new Scene(lineChart, 800, 600);
    stage.setScene(scene);
    stage.setTitle("Elevation plot");
    stage.show();
  }

  private void loadDataFromFile(XYChart.Series<Number, Number> series) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      reader.readLine();
      String line;
      double distance = 0;
      while ((line = reader.readLine()) != null) {
        String[] fields = line.split(",");
        double longitude = Double.parseDouble(fields[1]);
        double latitude = Double.parseDouble(fields[2]);
        double elevation = Double.parseDouble(fields[3]);

        // Distance logic goes here:
        // Placeholder code below just adds longitude and latitude
        distance = longitude + latitude;

        series.getData().add(new XYChart.Data<>(distance, elevation));
      }
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
      System.exit(0);
    }
  }

  public static void main(String[] args) {
    // If attempting the Advanced task, uncomment the line below
    if (args.length != 1) {
      System.out.println("Usage: java PlotApplication <filename>");
      System.exit(0);
    }
    filename = args[0];
    launch(args);
  }
}
