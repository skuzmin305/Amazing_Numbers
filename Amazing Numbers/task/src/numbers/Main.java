package numbers;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        write your code here
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameters show how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.printf("- separate the parameters with one space;\n- enter 0 to exit.\n");

        ArrayList<String> keyArr = new ArrayList<String>();
        String[] keys = new  String[]{
                "buzz", "duck", "palindromic", "gapful", "spy", "even", "odd"
        };
        for (String str : keys) {
            keyArr.add(str);
        }
        while (true) {
            System.out.print("Enter a request: ");
            String[] strForCheck = new String[]{
                    null, null, null
            };
            String strWithstr = scan.nextLine();
            int index = 0;
            for (String str : strWithstr.split(" ")) {
                strForCheck[index] = str;
                index++;
            }
            try {
                Long.parseLong(strForCheck[0]);
            } catch (Exception e) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }
            long numb = Long.parseLong(strForCheck[0]);
            if (numb == 0) {
                System.out.println("Goodbye!");
                break;
            } else if (numb < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }
            if (strForCheck[1] == null) {
                ifOneParam(numb);
            } else {
                try {
                    Integer.parseInt(strForCheck[1]);
                } catch (Exception e) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                int count = Integer.parseInt(strForCheck[1]);
                if (count < 0) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                String key = strForCheck[2];
                while (count > 0) {
                    ArrayList<String> checkResult = new ArrayList();
                    if (numb % 7 == 0 || numb % 10 == 7) checkResult.add("buzz");
                    if (checkDuck(numb)) checkResult.add("duck");
                    if (palindromic(numb)) checkResult.add("palindromic");
                    if (spy(numb)) checkResult.add("spy");
                    if (gapful(numb)) checkResult.add("gapful");
                    if (numb % 2 == 0) checkResult.add("even");
                    if (numb % 2 != 0) checkResult.add("odd");
                    if (key == null) {
                        System.out.printf("%d is ", numb);
                        for (String result : checkResult) {
                            if (result.equals(checkResult.get(checkResult.toArray().length - 1)))
                                System.out.println(result);
                            else
                                System.out.print(result + ", ");
                        }
                    } else {
                        if (keyArr.contains(key.toLowerCase())) {
                            if (sortToKey(checkResult, key.toLowerCase())) {
                                System.out.printf("%d is ", numb);
                                for (String result : checkResult) {
                                    if (result.equals(checkResult.get(checkResult.toArray().length - 1)))
                                        System.out.println(result);
                                    else
                                        System.out.print(result + ", ");
                                }
                            }
                        } else {
                            System.out.printf("The property [%s] is wrong.\n", key.toUpperCase(Locale.ROOT));
                            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]");
                            break;
                        }
                    }
                    numb++;
                    count--;
                }
            }
        }
    }

    public  static void ifOneParam(long numb) {
        boolean even = numb % 2 == 0;
        boolean odd = numb % 2 != 0;
        boolean buzz = numb % 7 == 0 || numb % 10 == 7;

        System.out.printf("Properties of %d :\n", numb);
        System.out.println("        buzz: " + buzz);
        System.out.println("        duck: " + checkDuck(numb));
        System.out.println(" palindromic: " + palindromic(numb));
        System.out.println("      gapful: " + gapful(numb));
        System.out.println("         spy: " + spy(numb));
        System.out.println("        even: " + even);
        System.out.println("         odd: " + odd);
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
    public static boolean spy (long numb) {
        int sum = 0;
        int mult = 1;
        for (char num : String.valueOf(numb).toCharArray()) {
            sum += Long.parseLong(String.valueOf(num));
        }
        for (char num : String.valueOf(numb).toCharArray()) {
            mult *= Long.parseLong(String.valueOf(num));
        }
        return sum == mult;
    }
    public static boolean sortToKey(ArrayList<String> listArray, String key) {
        return listArray.contains(key);
    }
}