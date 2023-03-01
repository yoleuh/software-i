import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author brian tan
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        // number of children of tree
        int children = exp.numberOfChildren();
        //initialize naturalnumber value
        NaturalNumber value = new NaturalNumber2();
        //if two children are present, exp label is an operator

        if (children == 2) {
            XMLTree childOne = exp.child(0);
            XMLTree childTwo = exp.child(1);
            String label = exp.label();

            //recursive call on smaller trees to start with the first trees
            NaturalNumber numberOne = new NaturalNumber2(evaluate(childOne));
            NaturalNumber numberTwo = new NaturalNumber2(evaluate(childTwo));

            //after recursion, two child nodes will be numbers
            //calculate expressions
            if (label.equals("plus")) {
                numberOne.add(numberTwo);
            }
            if (label.equals("minus")) {
                //ensure if operator if minus, first number must be larger than the second
                //else report error
                if (numberOne.compareTo(numberTwo) > 0) {
                    numberOne.subtract(numberTwo);
                } else {
                    Reporter.fatalErrorToConsole(
                            "Cannot subtract NaturalNumber by a larger NaturalNumber");

                }
            }
            if (label.equals("times")) {
                numberOne.multiply(numberTwo);
            }
            if (label.equals("divide")) {
                //ensure if operator if divide, second number is not zero
                //else report error
                if (!numberTwo.isZero()) {
                    numberOne.divide(numberTwo);
                } else {
                    Reporter.fatalErrorToConsole("Cannot divide by 0");
                }
            }
            value.copyFrom(numberOne);
        }

        //else exp is a number
        else {
            //get NaturalNumber type of the number
            String valueString = exp.attributeValue("value");
            NaturalNumber valueInt = new NaturalNumber2(
                    Integer.parseInt(valueString));
            value.copyFrom(valueInt);
        }

        return value;

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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}