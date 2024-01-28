import java.util.Scanner;

class Spheroid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter equatorial radius in km: ");
        double equatorialRadius = scanner.nextDouble();

        System.out.print("Enter polar radius in km: ");
        double polarRadius = scanner.nextDouble();

        double eccentricity = Math.sqrt(1 - Math.pow(polarRadius / equatorialRadius, 2));
        double volume = 4.0 / 3.0 * Math.PI * Math.pow(equatorialRadius, 2) * polarRadius;

        System.out.printf("Eccentricity = %.3f\n", eccentricity);
        System.out.printf("Volume = %g cubic km\n", volume);
        scanner.close();
    }
}