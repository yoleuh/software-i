import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
/*
 * Project: Compute Roots Using Newton Iteration
 *
 * repeatedly prompt user to calculate another root
 *
 * calculation is done using newton iteration
 *
 * CSE 2221
 *
 * Brian Tan
 *
 */

public final class Newton2 {

    //private class to calculate the square root with newton iteration without
    //public access
    private static double sqrt(double x) {
        double r = x;
        double e = 0.0001;
        //while loop to continue updating r until relative error is less than e
        while ((Math.abs(r * r - x)) / x > e * e) {
            r = ((r + x / r) / 2);
        }
        //return square root for main method to print
        return r;
    }

    //main method that determines if root needs to be calculated and calls for
    //sqrt
    public static void main(String[] args) {
        //input and output streams
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //prompt for calculating root
        out.println("Calculate  root?");
        String response = in.nextLine();
        double x;

        //while loop to proceed if input is y, else quit
        while (response.equals("y")) {
            //prompt user for number
            out.println("Enter a number:");
            x = in.nextDouble();
            //call for sqrt to calculate the root
            double root = sqrt(x);
            //report square root
            out.println("The root is " + root);
            out.println();
            //prompt for calculating another root
            out.println("Calculate another root?");
            response = in.nextLine();

        }

        //Close input and output streams
        in.close();
        out.close();
    }
}
