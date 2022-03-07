package learn;

import learn.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Warmups {
    public static void main(String[] args) {
        String input = "";
        while(!input.equals("exit")) {
            try {
                System.out.println("Input the warmup number you want to run:\n");
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                input = br.readLine();
                System.out.println("Trying: " + input);

                switch (input.toLowerCase())
                {
                    case "exit":
                        System.out.println("exiting");
                        break;
                    case "01":
                        warmup01();
                        break;
                    case "02":
                        warmup02();
                        break;
                    case "03":
                        warmup03();
                        break;
                    case "04":
                        warmup04();
                        break;
                    case "05":
                        warmup05();
                        break;
                    case "06":
                        warmup06();
                        break;
                    case "07":
                        warmup07();
                        break;
                    case "08":
                        warmup08();
                        break;
                    case "09":
                        warmup09();
                        break;
                    case "10":
                        warmup10();
                        break;
                    case "11":
                        warmup11();
                        break;
                    default:
                        System.out.println("try again");
                        break;
                }
            }
            catch (IOException ioe) {
                System.out.println("IO Exception raised!");
            }
        };
    }

    public static void warmup01() {
        System.out.println("Hello Warmups");

        String name = "Brian";
        String adj1 = "Happy";
        String adj2 = "Tall";
        System.out.println(name + " is " + adj1 + " and \n" + adj2);

        System.out.println( true || false && true && true);

    }

    public static void warmup02() {
        //knock-knock joke

        Scanner console = new Scanner(System.in);

        System.out.println("Knock Knock");
        console.nextLine();
        System.out.println("who's there?");
        console.nextLine();
        System.out.println("Justin");
        console.nextLine();
        System.out.println("Justin who?");
        console.nextLine();
        System.out.println("Justin time for lunch!");


    }

    public static void warmup03() {
        // Brian's Riddle
        Scanner console = new Scanner(System.in);
        String answer1 = "breath";
        String riddle1 = "What weighs almost nothing, yet no man can hold it for more than 5 minutes?";
        String input = "";
        System.out.println(riddle1);
        do {
            input = console.nextLine();
            if (answer1.equalsIgnoreCase(input)) {
                break;
            }
            System.out.println("Try again");
        } while (!answer1.equalsIgnoreCase(input));

        System.out.println("That's right! Congratulations.");

        /*
            //Sultan's version of Brian's Riddle
            import java.util.Scanner;
            public class ObjectiveProgram {
                public static void main(String[] args) {
                    Scanner console = new Scanner(System.in);
                    boolean incorrectAnswer = true;
                    System.out.printf("What weighs almost nothing, yet no man can hold it for more than 5 minutes?\nEnter [1] to give up.\nYour answer: ");
                    do {
                        String input = console.nextLine();
                        if (input.equals("1")){
                            System.out.println("The answer is your breath! Nice try.");
                            incorrectAnswer = false;
                        }else if (input.equalsIgnoreCase("breath")) {
                            System.out.println("Congratulations! You are correct.");
                            incorrectAnswer = false;
                        }
                        else
                            System.out.printf("Incorrect!\nEnter [1] to give up.\nPlease Try again: ");
                    } while (incorrectAnswer);
                }
            }
        */
    }

    public static void warmup04() {
        // Brian's version of Sultan's Palindrome checker
        Scanner console = new Scanner(System.in);
        System.out.println("Enter a palindrome:");
        boolean test = true;
        String input = console.nextLine().toLowerCase();
        for (int one = 0, two = input.length()-1; one < input.length(); one++, two--) {
            if (input.charAt(one) != input.charAt(two)) {
                test = false;
                break;
            }
        }
        if (test) {
            System.out.println("That is a palindrome");
        } else {
            System.out.println("That is not a palindrome");
        }

        /*
        // Sultan's Palindrome checker:
        import java.util.Scanner;
        public class WarmUp {
            public static void main(String[] args) {
                Scanner console = new Scanner(System.in);
                System.out.printf("Enter a palindrome: ");
                String input = console.nextLine();

                if (isPalindrome(input))
                    System.out.println("Your input is a Palindrome!");
                else
                    System.out.println("Your input is not a Palindrome.");
            }

            public static boolean isPalindrome(String input)
            {
                boolean result = true;
                for (int i = 0; i < input.length() / 2; i++)
                {
                    if (input.charAt(i) != input.charAt(input.length() - i - 1)) {
                        result = false;
                        return result;
                    }
                }
                return result;
            }
        }
         */
    }


    public static void warmup05() {
        // Brian and Aaron's palindrome checker
        // str = str.replaceAll("[^a-zA-Z0-9]", " ")
        Scanner console = new Scanner(System.in);
        boolean exit = false;
        do {
            boolean isPalindrome = true;
            System.out.println("Enter a palindrome:");
            String input = Input.getWord();
            for ( int i = 0; i < input.length()/2; i ++) {
                if (input.charAt(i) != input.charAt(input.length()-i-1)) {
                    isPalindrome = false;
                    break;
                }
            }
            System.out.printf(isPalindrome ? "It's a Palindrome!%n" : "Error:%nThis is not a Palindrome!%n");
            System.out.println("Would you exit? 1 for yes, 2 for no");
            exit = Input.getInt(1, 2) == 1;
        } while (!exit);
    }

    public static void warmup06() {
        Scanner console = new Scanner(System.in);
        System.out.println("Type RNA");
        String input = Input.getWord();
        System.out.printf("%s -> %s%n", input, rnaEncoding(input));
    }

    public static String rnaEncoding( String strand ) {
        String first = strand.substring(0, 1);
        String output = "";
        switch (first) {
            case "A":
                output = rnaTest("U", strand);
                break;
            case "U":
                output = rnaTest("A", strand);
                break;
            case "C":
                output = rnaTest("G", strand);
                break;
            case "G":
                output = rnaTest("C", strand);
                break;
            default:
                break;
        }
        return output;
    }

    public static String rnaTest(String letter, String strand) {
        if (strand.length() == 1 ) {
            return letter;
        } else {
            return String.format("%s%s", letter, rnaEncoding( strand.substring(1, strand.length())));
        }

    }

    public static void warmup07() {
        String phrase = "Hello World!~}";
        int key = 17;
        StringBuilder encode = new StringBuilder();

        for (int i = 0; i < phrase.length(); i++) {
            encode.append((char) (phrase.charAt(i)+key));
        }
        String encoded = encode.toString();
        System.out.println(encoded);
        StringBuilder decode = new StringBuilder();
        for (int i = 0; i < encoded.length(); i++) {
            decode.append((char) (encoded.charAt(i)-key));
        }

        String decoded = decode.toString();
        System.out.println(decoded);
    }

    public static void warmup08() {
        /*
        String message = "...Something ...something dark side... Something ...something Sith.";
        String fileName = "secretMessage.txt";
        File file = new File(fileName);*/
    }

    public static void stringToFile(String message) {

    }

    public static void stringFromFile(String fileName) {

    }

    public static void warmup09() {
        String[] testInputs = {"C", "C#", "CD", "ACE", "CC#DFEA#A#"};
        for (int i = 0; i < testInputs.length; i++) {
            majorThirds(testInputs[i]);
        }
    }

    public static void majorThirds(String input) {
        String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        ArrayList<String> majorThird = new ArrayList<>();
        System.out.printf("Your input was: %s%n", input);

        for (int i = 0; i < input.length(); i++) {
            String c = Character.toString(input.charAt(i));
            int index = 0;
            if (c.equals("#")) {
                String note = input.charAt(i-1) + "#";
                index = (Arrays.asList(notes).indexOf(note) + 4) % 12;
                majorThird.set(majorThird.size()-1, notes[index]);
            } else {
                index = (Arrays.asList(notes).indexOf(c) + 4) % 12;
                majorThird.add(notes[index]);
            }
        }
        System.out.printf("Your major thirds are: %s%n", majorThird);
    }

    public static void warmup10() {
        // New Employee Gross Salary Calculator

        System.out.printf("%nPlease enter employee annual salary: ");
        BigDecimal salary = new BigDecimal(Input.getInt(0));
        System.out.println("Please enter employee start month [1-12]:");
        BigDecimal month = new BigDecimal(Input.getInt(1, 12));

        BigDecimal threeMonthRaise = new BigDecimal(".03");
        BigDecimal sixMonthRaise = new BigDecimal(".06");
        BigDecimal grossSalary = new BigDecimal(0);

        for (BigDecimal i = month;  i.compareTo(new BigDecimal(13)) == -1; i = i.add(BigDecimal.ONE)) {

            if (i.compareTo(month.add(new BigDecimal(3)))==0) {
                salary = salary.add(salary.multiply(threeMonthRaise));
            } else if (i.compareTo(month.add(new BigDecimal(6)))==0) {
                salary = salary.add(salary.multiply(sixMonthRaise));
            }

            grossSalary = grossSalary.add(salary.divide(new BigDecimal(12), 2, RoundingMode.HALF_UP)).setScale(2);
        }

        System.out.println("The employee's final annual salary will be:");
        System.out.println(salary);
        System.out.println("The employee's gross salary through the end of the year will be:");
        System.out.println(grossSalary);

    }

    public static void warmup11() {


    }


































}
