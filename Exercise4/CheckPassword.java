public class CheckPassword {
    public static boolean longEnough(String password) {
        return password.length() >= 8;
    }

    public static boolean atLeastTwoDigits(String password) {
        int digitCount = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                digitCount++;
            }
        }
        return digitCount >= 2;
    }

    public static void main(String[] args) {
        String password = args[0];
        if (longEnough(password) && atLeastTwoDigits(password)) {
            System.out.println("Password is valid");
        } else {
            System.out.println("Password is not valid");
        }
    }
}