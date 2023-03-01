import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author brian tan
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        // number of children of tree
        int children = exp.numberOfChildren();
        //Initialize value, numbeOne for node one and numbeTwo for node two
        int value = 0;
        int numberOne = 0;
        int numberTwo = 0;
        //if two children are present, exp label is an operator
        if (children == 2) {
            XMLTree childOne = exp.child(0);
            XMLTree childTwo = exp.child(1);
            String label = exp.label();
            //recursive call on smaller trees to start with the first trees
            numberOne = evaluate(childOne);
            numberTwo = evaluate(childTwo);
            //after recursion, two child nodes will be numbers
            //calculate expressions
            if (label.equals("plus")) {
                value = numberOne + numberTwo;
            }
            if (label.equals("minus")) {
                value = numberOne - numberTwo;
            }
            if (label.equals("times")) {
                value = numberOne * numberTwo;
            }
            if (label.equals("divide")) {
                value = numberOne / numberTwo;

            }
        }
        //else exp is a number
        else {
            //get integer type of the number
            String valueString = exp.attributeValue("value");
            value = Integer.parseInt(valueString);

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