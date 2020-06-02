import java.util.Scanner;

/** The FractionCalculator class is a class that will allow the user
 * to enter in fractions and operations, calculating and displaying the result.
 * It will run until the user tells it to quit.
 */

public class FractionCalculator {
    private Scanner input = new Scanner(System.in);
    private boolean valid = false;

    /**
     * Asks the user to enter in a valid mathematical operation.
     * If the user enters anything except "+", "-
     *
     * @return operation String
     */
    public String getOperation() {
        System.out.print("Please enter a valid mathematical operation (+,-,/,*,=) or \"Q\" to quit: ");
        String operation = input.nextLine();
        int q = 0;
        while (q == 0) {
            if (operation.equalsIgnoreCase("Q")) {
                System.exit(0);
            } else if (operation.equals("+") || operation.equals("-") || operation.equals("/") || operation.equals("*")) {
                q++;
            } else {
                System.out.print("Invalid input! " +
                        "Please enter a valid mathematical operation (+,-,/,*,=) or \"Q\" to quit: ");
                operation = input.nextLine();
            }
        }
        return operation;
    }

    /**
     * Validate user input if it's a fraction or not
     *
     * @param fraction String from user
     * @return true if the parameter is in the form "a/b" where a is any int and b is any positive int
     */
    public boolean validFraction(String fraction) {
        if (fraction.startsWith("-")) {
            fraction = fraction.substring(1);
        }
        if (fraction.contains(" ") || fraction.contains("-") || fraction.charAt(fraction.indexOf("/") + 1) == ('0')) {
            valid = false;
        } else if (fraction.contains("/")) {
            fraction = fraction.replace("/", "");
        }
        isNumber(fraction);
        return valid;
    }

    /**
     * Takes a String as input and returns true if every character in the String is a number 0-9 and false otherwise.
     * This method can also check for empty strings.
     *
     * @param input String
     */
    private void isNumber(String input) {
        valid = input.matches("[0-9]+");
    }

    /**
     * It prompts the user for a String that is a validFraction.
     * If they enter any thing that is not a valid Fraction, it should re-prompt them until it is valid
     *
     * @return input Fraction
     */
    public Fraction getFraction() {
        System.out.print("Please enter a Fraction (a/b) or integer (a): ");
        String fractionInput = input.nextLine();
        while (!validFraction(fractionInput)) {
            System.out.print("Invalid Fraction, Please enter (a/b) or (a), where a and b are integers and b is greater than zero: ");
            fractionInput = input.nextLine();
        }
        int num;
        int den;
        if (fractionInput.contains("/")) {
            num = Integer.parseInt(fractionInput.substring(0, fractionInput.indexOf("/")));
            den = Integer.parseInt(fractionInput.substring(fractionInput.indexOf("/") + 1));
        } else {
            num = Integer.parseInt(fractionInput);
            den = 1;
        }
        return new Fraction(num, den);
    }

    private void intro() {
        System.out.println("FRACTION CALCULATOR");
        System.out.println("This program will add, subtract, multiply and divide fractions until you type \"Q\" to quit.");
        System.out.println("Enter your fraction in the form a/b, where a and b are integers, and b is greater than zero.");
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        FractionCalculator fractionCal = new FractionCalculator();
        fractionCal.intro();

        while (true) {
            String operation = fractionCal.getOperation();
            Fraction fraction1 = fractionCal.getFraction();
            Fraction fraction2 = fractionCal.getFraction();

            Fraction result = new Fraction(1, 1);
            String resultStr = "";

            if (operation.equals("=")) {
                System.out.println(fraction1 + " " + operation + " " + fraction2 + " is " + fraction1.equals(fraction2)+"\n");
            } else {
                switch (operation) {
                    case "+":
                        result = fraction1.add(fraction2);
                        break;
                    case "-":
                        result = fraction1.subtract(fraction2);
                        break;
                    case "/":
                        if (fraction2.getNumerator() == 0) {
                            resultStr = "Undefined";
                        } else {
                            result = fraction1.divide(fraction2);
                        }
                        break;
                    case "*":
                        if (fraction2.getNumerator() == 0) {
                            resultStr = "0";
                        } else {
                            result = fraction1.multiply(fraction2);
                        }
                        break;
                }

                if (!resultStr.equals("")) {
                    System.out.println(fraction1 + " " + operation + " 0 = " + resultStr +"\n");
                } else if (result.getNumerator() % result.getDenominator() == 0) {
                    System.out.println(fraction1 + " " + operation + " " + fraction2 + " = " + (result.getNumerator()/result.getDenominator())+"\n");
                } else {
                    System.out.println(fraction1 + " " + operation + " " + fraction2 + " = " + result.toString()+"\n");
                }
            }
        }
    }
}