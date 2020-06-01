import java.util.InputMismatchException;

/**
 * The Fraction class is an object that holds information about a fraction (numerator and denominator).
 */

public class Fraction {
    private int numerator;
    private int denominator;

    /**
     * a two parameter constructor that initializes the numerator and denominator
     * @param num = numerator
     * @param den = denominator
     */
    public Fraction (int num, int den){
        if (den == 0) {
            throw new IllegalArgumentException("Denominator can not be zero.");
        } else if (den < 0){
            numerator = num*-1;
            denominator = den*-1;
        } else {
            numerator = num;
            denominator = den;
        }
    }

    /**
     * one parameter constructor that initializes the object equal in value to the integer parameter
     * @param num = numerator
     */
    public Fraction(int num){ this(num,1); }

    /**
     * zero parameter constructor that initializes the object to 0,
     * meaning the numerator is 0 and the denominator is 1
     */
    public Fraction(){ this(0); }

    /**
     * exposes the value of the numerator field to the user
     * @return numerator
     */
    public int getNumerator() { return numerator; }

    /**
     * exposes the value of the denominator field to the user
     * @return denominator
     */
    public int getDenominator() { return denominator; }

    public void setNumerator(int numerator){
        this.numerator = numerator;
    }
    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    /**
     * a String representation of the Fraction
     * @return "numerator/denominator"
     */
    @Override
    public String toString() {
        return numerator+"/"+denominator;
    }

    /**
     * @return the result of numerator / denominator
     */

    public double toDouble(){
        return numerator/denominator;
    }

    /**
     * @param other Fraction object
     * @return fractionSum, a new Fraction that is the sum of other and this fractions
     */
    public Fraction add(Fraction other){
        Fraction fractionSum = new Fraction(((this.numerator*other.denominator)+(other.numerator*this.denominator)),(this.denominator*other.denominator));
        fractionSum.toLowestTerms();
        return fractionSum;
    }

    /**
     * @param other Fraction object
     * @return fractionSub, a new Fraction that is the difference between the other and this fraction
     */
    public Fraction subtract(Fraction other){
        Fraction fractionSub = new Fraction(((this.numerator*other.denominator)-(other.numerator*this.denominator)),(this.denominator*other.denominator));
        fractionSub.toLowestTerms();
        return fractionSub;
    }

    /**
     * @param other Fraction object
     * @return fractionMul, a new Fraction that is the product of the other and this fraction
     */
    public Fraction multiply(Fraction other){
        Fraction fractionMul = new Fraction((this.numerator*other.numerator),(this.denominator*other.denominator));
        fractionMul.toLowestTerms();
        return fractionMul;
    }

    /**
     * @param other Fraction object
     * @return fractionDiv, a new Fraction that is the division of the other and this fraction,
     * throw an IllegalArgumentException() if the user asks you to divide by 0
     */
    public Fraction divide(Fraction other){
        if (other.denominator == 0){
            throw new IllegalArgumentException("Denominator can not be zero.");
        } else {
            Fraction fractionDiv = new Fraction((this.numerator*other.denominator), (this.denominator*other.numerator));
            fractionDiv.toLowestTerms();
            return fractionDiv;
        }
    }

    /**
     * Override the Object equals() method so that it accurately determines
     * whether or not two fractions are equal
     * @param other Object
     * @return
     */
    @Override
    public boolean equals(Object other){
        if(other instanceof Fraction){
            Fraction otherFraction = (Fraction) other; //cast other to fraction
            otherFraction.toLowestTerms();
            return (this.numerator == otherFraction.numerator)&&(this.denominator == otherFraction.denominator);
        } else { throw new InputMismatchException("Fraction parameter expected."); }
    }

    /**
     * converts the current fraction to the lowest terms
     */
    private void toLowestTerms(){
        if (numerator!=0) {
            int gcd = gcd(numerator,denominator);
            numerator = numerator/gcd;
            denominator = denominator / gcd;
        } else {numerator = 0;}

    }

    /**
     * a public static method that takes two integers as parameters
     * @param num Integer for numerator
     * @param den Integer for denominator
     * @return num, an int that is their greatest common divisor.
     */
    private static int gcd(int num, int den) {
        while (num!=0 && den!=0) {
            int remainder = num % den;
            num = den;
            den = remainder;
        }
        return num;
    }
}
