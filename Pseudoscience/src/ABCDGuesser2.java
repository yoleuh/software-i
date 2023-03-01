import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * This program will prompt for a, b, c, d, and μ, then calculate and report the
 * values of the exponents a, b, c, and d that bring the de Jager formula as
 * close as possible to μ.
 *
 * @author brian tan
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     *  * Repeatedly asks the user for a positive real number until the user
     * enters  * one. Returns the positive real number.  *  * @param in
     *  *            the input stream  * @param out  *            the output
     * stream  * @return a positive real number entered by the user  
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        boolean checkNumber = false;
        double realNumber = 0.0;
        while (!checkNumber) {
            out.println("positive real number:");
            String realNumberString = in.nextLine();
            checkNumber = FormatChecker.canParseDouble(realNumberString);
            if (checkNumber) {
                realNumber = Double.parseDouble(realNumberString);
            }
        }
        return realNumber;
    }

    /**
     *  * Repeatedly asks the user for a positive real number not equal to 1.0
     *  * until the user enters one. Returns the positive real number.  *
     *  * @param in  *            the input stream  * @param out  *           
     * the output stream  * @return a positive real number not equal to 1.0
     * entered by the user  
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        boolean checkNumber = false;
        double realNumberNot1 = 1.0;
        while (realNumberNot1 == 1.0 || !checkNumber) {
            out.println("positive real number not equal to 1.0:");
            String realNumberString = in.nextLine();
            checkNumber = FormatChecker.canParseDouble(realNumberString);
            if (checkNumber) {
                realNumberNot1 = Double.parseDouble(realNumberString);
            }
        }
        return realNumberNot1;
    }

    /**
     *  print results
     *
     * @param out
     *            * the output stream
     * @param mu
     *            * value given by user
     * @param exponents
     *            * array of calculated exponents
     * @param value
     *            * calculated value
     * @param error
     *            * error percentage calculated
     */
    private static void printResults(SimpleWriter out, double mu,
            double[] exponents, double value, double error) {
        out.println("μ: " + mu);
        out.println("exponents: " + exponents[0] + "," + exponents[1] + ","
                + exponents[2] + "," + exponents[3]);
        out.println("value: " + value);
        out.println("relative error: " + error * 100 + "%");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        //prompt user for values
        double mu = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        //exponents array
        double[] exponents = { 0, 0, 0, 0 };
        //de Jager formula 17 numbers array
        final double[] numbers = { -5.0, -4.0, -3.0, -2.0, -1.0, -1.0 / 2.0,
                -1.0 / 3.0, -1.0 / 4, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1.0,
                2.0, 3.0, 4.0, 5.0 };
        //temporary a b c d for loops
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        //value of formula
        double value = 0;
        //temporary value estimate
        double estimate = 0;
        double error = 0.01;

        //while loop for each exponent
        for (a = 0; a < numbers.length; a++) {
            double e = Math.pow(w, numbers[a]);
            //reset loop counter each time
            b = 0;

            for (b = 0; b < numbers.length; b++) {
                double f = Math.pow(x, numbers[b]);

                c = 0;

                for (c = 0; c < numbers.length; c++) {
                    double g = Math.pow(y, numbers[c]);

                    d = 0;

                    for (d = 0; d < numbers.length; d++) {
                        double h = Math.pow(z, numbers[d]);
                        estimate = e * f * g * h;
                        //check if error is smaller than the last, to get
                        //smallest possible error
                        if (Math.abs(mu - estimate) / mu < error) {
                            //save estimate to value
                            value = estimate;
                            //calculate error
                            error = Math.abs((mu - value) / mu);
                            //put exponent values into exponents array
                            exponents[0] = numbers[a];
                            exponents[1] = numbers[b];
                            exponents[2] = numbers[c];
                            exponents[3] = numbers[d];

                        }
                    }
                }
            }
        }
        //print results via method
        printResults(out, mu, exponents, value, error);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
