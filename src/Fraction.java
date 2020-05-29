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
            throw new IllegalArgumentException("Denominator can not be zero");
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
    public Fraction(int num){
        this(num,1);
    }

    /**
     * zero parameter constructor that initializes the object to 0,
     * meaning the numerator is 0 and the denominator is 1
     */
    public Fraction(){
        this(0);
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return numerator+"/"+denominator;
    }
}
