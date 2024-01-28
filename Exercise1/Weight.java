import java.util.Scanner;

class Weight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter weight in kilograms: ");
        double kilograms = scanner.nextDouble();

        double poundsPerKilogram = 2.20462;
        double ouncesPerPound = 16.0;
        double pounds = kilograms * poundsPerKilogram;

        int wholePounds = (int) Math.floor(pounds);
        double fractionalPounds = pounds - wholePounds;
        double ounces = fractionalPounds * ouncesPerPound;

        System.out.printf("Equivalent imperial weight is %d lb and %.1f oz.\n", kilograms, wholePounds, ounces);
        scanner.close();
    }
}
