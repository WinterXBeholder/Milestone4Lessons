package learn;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Collection of overloaded re-prompting console input methods that validate within specified parameters.
 */
public class Input {

    private static Scanner console = new Scanner(System.in);

    /**
     * logs "Enter an Integer greater than %s%n" when out of bounds. logs "That's not an Integer" when not an integer.
     * @param min input must be this number or higher
     * @return valid integer
     */
    public static int getInt( int min) {
        int input = 0;
        boolean isInt = false;
        do {
            try {
                input = Integer.parseInt(console.nextLine());
                isInt = input >= min;
            } catch (NumberFormatException e) {
                System.out.println("That's not an Integer");
                isInt = false;
            } catch (NoSuchElementException e) {
                System.out.printf("Scanner error: no element found%nNoSuchElementException%n");
                isInt = false;
            } catch (IllegalStateException e) {
                System.out.printf("Scanner error: scanner closed%nIllegalStateException%n");
                isInt = false;
            }
            if (!isInt) {System.out.printf("Enter an Integer greater than %s%n", min-1);};
        } while (!isInt);
        return input;
    }

    /**
     * logs "Enter an Integer between %s and %s%n" when out of bounds. logs "That's not an Integer" when not an integer.
     * @param min input must be this number or higher
     * @param max input must be this number or lower
     * @return valid integer
     */
    public static int getInt(int min, int max) {
        int input = 0;
        boolean isInt = false;
        do {
            try {
                input = Integer.parseInt(console.nextLine());
                isInt = input >= min && input <= max;
            } catch (NumberFormatException e) {
                System.out.println("That's not an Integer");
                isInt = false;
            } catch (NoSuchElementException e) {
                System.out.printf("Scanner error: no element found%nNoSuchElementException%n");
                isInt = false;
            } catch (IllegalStateException e) {
                System.out.printf("Scanner error: scanner closed%nIllegalStateException%n");
                isInt = false;
            }
            if (!isInt) {System.out.printf("Enter an Integer between %s and %s%n", min, max);};
        } while (!isInt);
        return input;
    }

    /**
     * Logs "That's not an Integer" when not an integer.
     * @return valid integer
     */
    public static int getInt() {
        int input = 0;
        boolean isInt = false;
        do {
            try {
                input = Integer.parseInt(console.nextLine());
                isInt = true;
            } catch (NumberFormatException e) {
                System.out.println("That's not an Integer");
                isInt = false;
            } catch (NoSuchElementException e) {
                System.out.printf("Scanner error: no element found%nNoSuchElementException%n");
                isInt = false;
            } catch (IllegalStateException e) {
                System.out.printf("Scanner error: scanner closed%nIllegalStateException%n");
                isInt = false;
            }
            if (!isInt) {System.out.println("Enter an Integer: ");};
        } while (!isInt);
        return input;
    }

    /**
     * Logs "That's not a word%nNon-letter characters not allowed. Try again" when any non-(a-z or A-Z) character is
     * detected.
     * @return valid string
     */
    public static String getWord() {
        String input = "";
        boolean isWord = false;
        do {
            try {
                input = console.nextLine();
                isWord = input.matches("[a-zA-Z]+");
                if (!isWord) {
                    System.out.printf("That's not a word%nNon-letter characters not allowed. Try again: ");
                };
            } catch (NoSuchElementException e) {
                System.out.printf("Scanner error: probably no line element found%nNoSuchElementException%n");
                isWord = false;
            } catch (IllegalStateException e) {
                System.out.printf("Scanner error: scanner probably closed%nIllegalStateException%n");
                isWord = false;
            }
        } while (!isWord);
        return input;
    }

    /**
     * Logs "Numbers and punctuation not allowed. Try again: " when any non-(a-z or A-Z) character is
     * detected.
     * @return valid string
     */
    public static String getPhrase() {
        String input = "";
        boolean isWord = false;
        do {
            try {
                input = console.nextLine();
                isWord = input.matches("[a-zA-Z ]+");
                if (!isWord) {
                    System.out.printf("Numbers and punctuation not allowed. Try again: ");
                };
            } catch (NoSuchElementException e) {
                System.out.printf("Scanner error: probably no line element found%nNoSuchElementException%n");
                isWord = false;
            } catch (IllegalStateException e) {
                System.out.printf("Scanner error: scanner probably closed%nIllegalStateException%n");
                isWord = false;
            }
        } while (!isWord);
        return input;
    }
}
