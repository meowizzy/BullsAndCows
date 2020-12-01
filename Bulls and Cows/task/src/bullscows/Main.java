package bullscows;
import java.util.*;

public class Main {
    private static int turns = 1;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static String sizeOfSecretCode;
    private static String sizeOfPossibleSymbols;
    private static int secret;
    private static int possibleSymbols;

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Input the length of the secret code: ");
            sizeOfSecretCode = sc.nextLine();
            secret = Integer.parseInt(sizeOfSecretCode);
            System.out.println("Input the number of possible symbols in the code: ");
            sizeOfPossibleSymbols = sc.nextLine();
            possibleSymbols = Integer.parseInt(sizeOfPossibleSymbols);
            String secretCode = getPseudoRandomNumber(secret, possibleSymbols);
            printPreparedCode(secret, possibleSymbols);
            System.out.println("Okay, let's start a game! ");
            System.out.println(guessTheSecretCode(secretCode));
        } catch (NumberFormatException e) {
            System.out.println("Error: " + "\"" + sizeOfSecretCode +  "\"" + " isn't a valid number.");
        }
    }

    private static void printPreparedCode(int size, int symbSize) {
        StringBuilder strb = new StringBuilder("The secret code is prepared: ");
        for (int i = 0; i < size; i++) {
            strb.append("*");
        }
        if (symbSize <= 10) {
            strb.append(" (0-" + Character.forDigit(symbSize - 1,16) + ").");
        } else {
            strb.append(" (0-9, a-" + Character.forDigit(symbSize -1 , 36) + ").");
        }
        System.out.println(strb.toString());

    }

//    this method performs the main task of this program;
    private static String guessTheSecretCode(String s) {
        Scanner sc = new Scanner(System.in);
        String secret = s;
        System.out.println("Turn " + turns + ":");
        String answer = sc.nextLine();
        StringBuilder wAnswerSymbols = new StringBuilder();
        int cows = 0;
        int bulls = 0;

        for (int i = 0; i < s.length(); i++) {
            if (secret.charAt(i) != answer.charAt(i)) {
                wAnswerSymbols.append(answer.charAt(i));
            } else {
                bulls++;
            }
        }
        for (int k = 0; k < secret.length(); k++) {
            for (int j = 0; j < wAnswerSymbols.length(); j++) {
                if (wAnswerSymbols.charAt(j) == secret.charAt(k)) {
                    cows++;
                }
            }
        }
        if (bulls == secret.length()) {
            return result(bulls, cows, secret) + "\nCongratulations! You guessed the secret code.";
        }
        else {
            System.out.println(result(bulls, cows, secret));
            turns++;
            return guessTheSecretCode(secret);
        }
    }

//    Method "result ()" returns a string that depends on the condition;
    private static String result(int b, int c, String secretCode) {
        String formatOutputStr;
        if (b > 0 && c > 0) {
           formatOutputStr = String.format("Grade: " + "%d" + " bull(s)" + " and " + "%d" + " cow(s).", b, c);
        } else if (b == 0 && c > 0) {
            formatOutputStr = String.format("Grade: " + "%d" + " cow(s).", c);
        } else if (c == 0 && b > 0) {
            formatOutputStr = String.format("Grade: " + "%d" + " bull(s).", b);
        } else {
            formatOutputStr = "None. The secret code is " + secretCode + ".";
        }
        return formatOutputStr;
    }


//    This method sets the length of generates number and then the method generates a secret number;
    private static String getPseudoRandomNumber(int numSize, int strSize) {

        if (numSize == 0 || strSize == 0) {
            System.out.println("Error: the size of the secret code and the size of possible symbols must be greater than zero.");
            System.exit(0);
        }

        if (numSize > strSize) {
            System.out.println("Error: it's not possible to generate a code with a length of " + numSize + " with " + strSize + " unique symbols.");
            System.exit(0);
        }

        StringBuilder num = new StringBuilder();
        StringBuilder str = new StringBuilder();

        if (strSize > 36) {
            System.out.println("Error: can't generate a secret number with a length of " + numSize + " because there aren't enough unique digits.");
            System.exit(0);
        }
        for (int i = 0; i < numSize; i++) {
            str.append(i);
        }
        for (int i = 0; i < strSize - 10; i++) {
            str.append(ALPHABET.charAt(i));

        }

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            strings.add(str.charAt(i) + "");
        }
        Collections.shuffle(strings);

        for (int i = 0; i < numSize; i++) {
            num.append(strings.get(i));
        }
        return num.toString();
    }
}
