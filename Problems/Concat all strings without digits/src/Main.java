import java.util.Scanner;

class ConcatenateStringsProblem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        System.out.println(concatenateStringsWithoutDigits(strings));
    }

    public static String concatenateStringsWithoutDigits(String[] strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strings) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString().replaceAll("[0-9]", "");
    }
}
