import java.util.Scanner;

/** The FractionCalculator class is a class that will allow the user
 * to enter in fractions and operations, calculating and displaying the result.
 * It will run until the user tells it to quit.
 */

public class FractionCalculator {
    private Scanner input = new Scanner(System.in);
    private boolean valid;

    /**
     * Asks the user to enter in a valid mathematical operation.
     * If the user enters anything except "+", "-
     * @return operation String
     */
    public String getOperation(){
        System.out.print("Please enter a valid mathematical operation (+,-,/,*,=) or \"Q\" to quit: ");
        String operation = input.nextLine();
        int q=0;
        while (q==0){
            if (operation.equalsIgnoreCase("Q")){
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
     * @param input String from user
     * @return true if the parameter is in the form "a/b" where a is any int and b is any positive int
     */
    public boolean validFraction(String input){
        if (input.contains("-")){
            input = input.replace("-","");
            }
        if (input.contains("/")) {
            input = input.replace("/","");
        }
        if (input.contains(" ")||input.charAt(input.indexOf("/")+1)==('0')){
            valid = false;
        } else {isNumber(input);}
        return valid;
    }

    /**
     * Takes a String as input and returns true if every character in the String is a number 0-9 and false otherwise.
     * This method can also check for empty strings.
     * @param input String
     * @return valid Boolean
     */
    private boolean isNumber(String input){
        if (input.matches("[0-9]+")){
            valid = true;
        } else { valid = false; }
        return valid;
    }

    /**
     * It prompts the user for a String that is a validFraction.
     * If they enter any thing that is not a valid Fraction, it should re-prompt them until it is valid
     * @return input Fraction
     */
    public Fraction getFraction(){
        System.out.print("Please enter a Fraction (a/b) or integer (a): ");
        String fractionInput = input.nextLine();
        while (!validFraction(fractionInput)){
            System.out.print("Invalid Fraction, Please enter (a/b) or (a), where a and b are integers and b is greater than zero: ");
            fractionInput = input.nextLine();
        }
        int num;
        int den;
        if (fractionInput.contains("/")){
            num = Integer.parseInt(fractionInput.substring(0,fractionInput.indexOf("/")));
            den = Integer.parseInt(fractionInput.substring(fractionInput.indexOf("/")+1,fractionInput.length()));
        } else {
            num = Integer.parseInt(fractionInput);
            den = 1;
        }

        Fraction fraction = new Fraction(num,den);
        return fraction;
    }

    /**
     * Get two fractions from the user and then perform whichever operation they ask for
     * @param f1
     * @param f2
     * @return
     */
    public String calculate(Fraction f1, Fraction f2, String op){
        Fraction result = new Fraction();

        if (op.equals("*")){
            result = f1.multiply(f2);
        } else if (op.equals("/")){
            if(f2.getNumerator()==0){
                System.out.println("Undefined.");
            } else{ result = f1.divide(f1); }
        } else if (op.equals("+")){
            result = f1.add(f2);
        } else if (op.equals("-")){
            result = f1.subtract(f2);
        }
        return f1+" "+op+" "+f2+" = "+result;
    }


    private void intro (){
        System.out.println("FRACTION CALCULATOR");
        System.out.println("This program will add, subtract, multiply and divide fractions until you type \"Q\" to quit.");
        System.out.println("Enter your fraction in the form a/b, where a and b are integers, and b is greater than zero.");
        System.out.println("---------------------------------------------------------------------------------------------");
    }



    public static void main(String[] args) {
        FractionCalculator fractionCal = new FractionCalculator();
        fractionCal.intro();

        while (true){
            String operation = fractionCal.getOperation();
            Fraction fraction1= fractionCal.getFraction();
            Fraction fraction2= fractionCal.getFraction();
            if (operation.equals("=")) {
                System.out.println(fraction1 + " " + operation + " " + fraction2 + " is " + fraction1.equals(fraction2)+"\n");
            } else {
                System.out.println(fractionCal.calculate(fraction1,fraction2,operation)+"\n");
            }
        }
    }
}
