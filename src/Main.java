import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        amazingNumbers.showRules();

        while (true) {
            System.out.print("Enter a request: ");
            String[] num = sc.nextLine().split(" ");

            if ("0".equals(num[0])) {
                System.out.println("Goodbye!");
                return;
            }

            if (num[0].isEmpty()) {
                amazingNumbers.showRules();
                continue;
            }

            amazingNumbers am;
            long first;
            int second;

            try {
                first = Long.parseLong(num[0]);
                second = num.length > 1 ? Integer.parseInt(num[1]) : 0;
                String state1 = num.length > 2 ? num[2] : "";
                String state2 = num.length > 3 ? num[3] : "";

                if (first < 1) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                } else if (second < 1 && num.length > 1) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }

                if (num.length == 1) {
                    am = new amazingNumbers(first);
                } else if (num.length == 2) {
                    am = new amazingNumbers(first, second);
                } else if (num.length == 3) {
                    am = new amazingNumbers(first, second, state1);
                } else if (num.length == 4) {
                    am = new amazingNumbers(first, second, state1, state2);
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid parameters.");
            }
        }

    }

}

class amazingNumbers {

    private long num;
    private HashMap<String, Boolean> myMap;

    public amazingNumbers(long num) {
        initialize(num);

        show();
    }

    public amazingNumbers(long num, int freq) {
        while (freq-- > 0) {
            initialize(num);

            show2();

            num++;

        }
    }

    public amazingNumbers(long num, int freq, String prop) {
        while (freq > 0) {
            initialize(num);
            if (!myMap.containsKey(prop.toLowerCase())) {
                System.out.printf("The property [%s] is wrong.\n", prop);
                System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
                break;
            }

            if (myMap.get(prop.toLowerCase())) {
                show2();

                freq--;
            }

            num++;

        }
    }

    public amazingNumbers(long num, int freq, String prop1, String prop2) {
        while (freq > 0) {
            initialize(num);
            if (!myMap.containsKey(prop1.toLowerCase()) && !myMap.containsKey(prop2.toLowerCase())) {
                System.out.printf("The properties [%s, %s] are wrong.\n", prop1, prop2);
                System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
                break;
            } else if (!myMap.containsKey(prop1.toLowerCase())) {
                System.out.printf("The property [%s] is wrong.\n", prop1);
                System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
                break;
            } else if (!myMap.containsKey(prop2.toLowerCase())) {
                System.out.printf("The property [%s] is wrong.\n", prop2);
                System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
                break;
            }

            String invalid = prop1+prop2;
            if (invalid.equals("oddeven") || invalid.equals("squaresunny") || invalid.equals("spyduck") || invalid.equals("duckspy") || invalid.equals("sunnysquare") || invalid.equals("evenodd")) {
                System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", prop1, prop2);
                System.out.println("There are no numbers with these properties.");
                break;
            }

            if (myMap.get(prop1.toLowerCase()) && myMap.get(prop2.toLowerCase())) {
                show2();

                freq--;
            }

            num++;

        }

    }

    private void initialize(long num) {
        myMap = new HashMap<>();
        this.num = num;
        myMap.put("even", checkingEven());
        myMap.put("odd", checkingOdd());
        myMap.put("square", checkingSquare());
        myMap.put("sunny", checkingSunny());
        myMap.put("gapful", checkingGapful());
        myMap.put("duck", checkingDuck());
        myMap.put("buzz", checkingBuzz());
        myMap.put("palindromic", checkingPalindromic());
        myMap.put("spy", checkingSpy());
    }

    private boolean checkingBuzz() {
        return num % 7 == 0 || num % 10 == 7;
    }

    private boolean checkingDuck() {
        StringBuilder exp = new StringBuilder(String.valueOf(num));

        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '0') {
                exp.deleteCharAt(i);
            } else {
                break;
            }
        }

        return exp.toString().contains("0");
    }

    private boolean checkingPalindromic() {
        String exp = String.valueOf(num);

        return exp.equals(new StringBuilder(exp).reverse().toString());
    }

    private boolean checkingGapful() {
        String exp = String.valueOf(num);

        if (exp.length() <= 2) {
            return false;
        }

        return Long.parseLong(exp) % Long.parseLong(String.format("%c%c", exp.charAt(0), exp.charAt(exp.length() - 1))) == 0;

    }

    private boolean checkingSpy() {
        int sum = 0;
        int product = 1;

        String[] arr = String.valueOf(num).split("");

        for (String s : arr) {
            sum += Integer.parseInt(s);
            product *= Integer.parseInt(s);
        }

        return sum == product;

    }

    private boolean checkingSunny() {
        return Math.sqrt(num + 1) % 1 == 0;
    }

    private boolean checkingSquare() {
        return Math.sqrt(num) % 1 == 0;
    }

    private boolean checkingEven() {
        return num % 2 == 0;
    }

    private boolean checkingOdd() {
        return num % 2 == 1;
    }

    public void show() {
        System.out.printf("""
        Properties of %d
                buzz: %b
                duck: %b
         palindromic: %b
                 spy: %b
              gapful: %b
                even: %b
                 odd: %b
               sunny: %b
              square: %b
                 """, num, myMap.get("buzz"), myMap.get("duck"), myMap.get("palindromic"), myMap.get("spy"), myMap.get("gapful"), myMap.get("even"), myMap.get("odd"), myMap.get("sunny"), myMap.get("square"));
    }

    private void show2() {
        StringBuilder res = new StringBuilder(String.format("%d is", num));

        if (myMap.get("buzz")) { res.append(" buzz,"); }
        if (myMap.get("duck")) { res.append(" duck,"); }
        if (myMap.get("palindromic")) { res.append(" palindromic,"); }
        if (myMap.get("spy")) { res.append(" spy,"); }
        if (myMap.get("sunny")) { res.append(" sunny,"); }
        if (myMap.get("square")) { res.append(" square,"); }
        if (myMap.get("gapful")) { res.append(" gapful,"); }
        if (myMap.get("even")) { res.append(" even,"); }
        if (myMap.get("odd")) { res.append(" odd,"); }

        res.deleteCharAt(res.length() - 1);
        System.out.println(res);
    }

    public static void showRules() {
        System.out.println("""
                Welcome to Amazing Numbers!
                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameters show how many consecutive numbers are to be processed;
                - two natural numbers and two properties to search for;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);
    }

}
