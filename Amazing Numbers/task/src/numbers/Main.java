package numbers;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        write your code here
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n" +
                        "Supported requests:\n" +
                        "- enter a natural number to know its properties;\n" +
                        "- enter two natural numbers to obtain the properties of the list:\n" +
                        "  * the first parameter represents a starting number;\n" +
                        "- two natural numbers and a property to search for;\n" +
                        "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                        "- separate the parameters with one space;\n- enter 0 to exit.\n");
        while (true) {
            System.out.print("Enter a request: ");
            String strForCheck = scan.nextLine();
            try {
                long numb = Long.parseLong(strForCheck.split(" ")[0]);
            } catch (Exception e) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }
            long numb = Long.parseLong(strForCheck.split(" ")[0]);
            if (numb == 0) {
                System.out.println("Goodbye!");
                break;
            } else if (numb < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }
            if (strForCheck.split(" ").length < 2) {
                ifOneNumb(numb);
            } else {
                try {
                    Integer.parseInt(strForCheck.split(" ")[1]);
                } catch (Exception e) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                int count = Integer.parseInt(strForCheck.split(" ")[1]);
                if (count < 0) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                while (count > 0) {
                    ArrayList<String> checkResult = new ArrayList();
                    System.out.printf("%d is ", numb);
                    if (numb % 7 == 0 || numb % 10 == 7) checkResult.add("buzz");
                    if (checkDuck(numb)) checkResult.add("duck");
                    if (palindromic(numb)) checkResult.add("palindromic");
                    if (gapful(numb)) checkResult.add("gapful");
                    if (numb % 2 == 0) checkResult.add("even");
                    if (numb % 2 != 0) checkResult.add("odd");
                    if (spyNumber(numb)) checkResult.add("spy");
                    for (String result : checkResult) {
                        if (result.equals(checkResult.get(checkResult.toArray().length-1)))
                            System.out.println(result);
                        else
                            System.out.print(result + ", ");
                    }
                    numb++;
                    count--;
                }
            }
        }
    }

    public  static void ifOneNumb(long numb) {
        boolean even = numb % 2 == 0;
        boolean odd = numb % 2 != 0;
        boolean buzz = numb % 7 == 0 || numb % 10 == 7;

        System.out.printf("Properties of %d :\n", numb);
        System.out.println("        buzz: " + buzz);
        System.out.println("        duck: " + checkDuck(numb));
        System.out.println(" palindromic: " + palindromic(numb));
        System.out.println("      gapful: " + gapful(numb));
        System.out.println("        even: " + even);
        System.out.println("         odd: " + odd);
        System.out.println(spyNumber(numb));
    }
    public static boolean checkDuck(long numb) {
        for (int i = 1; i < String.valueOf(numb).length(); i++) {
            if (String.valueOf(numb).charAt(i) == '0') {
                return true;
            }
        }
        return false;
    }
    public static boolean palindromic(long numb) {
        String stringNumb = new StringBuilder(String.valueOf(numb)).reverse().toString();
        return String.valueOf(numb).equals(stringNumb);
    }
    public static boolean gapful(long numb) {
        if (String.valueOf(numb).length() < 3) {
            return false;
        }
        char[] numbStrArr = String.valueOf(numb).toCharArray();
        String cooncatFirstLast = String.valueOf(numbStrArr[0])+String.valueOf(numbStrArr[numbStrArr.length-1]);
        return numb % Integer.parseInt(cooncatFirstLast) == 0;
    }
    public static boolean spyNumber(long numb) {
        long sum = 0;
        long mult = 1;
        for (char number : String.valueOf(numb).toCharArray()) {
            sum += Long.parseLong(number.toString());
        }
        for (char number : String.valueOf(numb).toCharArray()) {
            mult *= Long.parseLong(number.toString());
        }
        return sum == mult;
    }
}