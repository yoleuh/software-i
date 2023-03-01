import java.util.Comparator;

import components.map.Map;
import components.map.Map2;
import components.queue.Queue;
import components.queue.Queue2;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Html page of list terms, with their definitions from a text file.
 *
 * @author Brian Tan
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        char c = text.charAt(position);
        String x = "";
        int i = position + 1;
        x = x + c;
        if (!separators.contains(c)) {
            while (i < text.length() && !separators.contains(c)) {
                c = text.charAt(i);
                if (!separators.contains(c)) {
                    String y = "" + c;
                    x = x.concat(y);
                }
                i++;
            }
        } else {
            while (i < text.length() && separators.contains(c)) {
                c = text.charAt(i);
                if (separators.contains(c)) {
                    String y = "" + c;
                    x = x.concat(y);
                }
                i++;
            }
        }
        return x;
    }

    /**
     * Takes text file and produces queue of terms
     *
     * @param fileIn
     *            input stream from text file
     * @return the queue with terms from text file
     *
     * @requires text file
     *
     * @ensures only terms are add into the queue
     */
    public static Queue<String> sortTerm(SimpleReader fileIn) {
        Queue<String> terms = new Queue2<>();
        //while file has not reached last line
        while (!fileIn.atEOS()) {
            //add word to term queue if line does not contain a space and is not empty
            String line = fileIn.nextLine();
            if (!line.contains(" ") && !line.isEmpty()) {
                terms.enqueue(line);
            }
        }
        return terms;
    }

    /**
     * Takes text file and produces queue of definitions
     *
     * @param fileIn
     *            input stream from text file
     * @return the queue with definitions from text file
     *
     * @requires text file
     *
     * @ensures multi-line definitions are combined into one in the queue
     */
    public static Queue<String> sortDef(SimpleReader fileIn) {
        Queue<String> defs = new Queue2<>();
        //while file has not reached last line
        while (!fileIn.atEOS()) {
//add line to queue if it contains a space and combine if following line contains space
            String nextLine = fileIn.nextLine();
            String line = "";
            if (nextLine.contains(" ")) {
                line = nextLine;
                nextLine = fileIn.nextLine();
                while (nextLine.contains(" ")) {
                    line = line + " " + nextLine;
                    nextLine = fileIn.nextLine();
                }
                defs.enqueue(line);
            }
        }
        return defs;
    }

    /**
     * Output glosssary into .html files
     *
     * @param folder
     *            name of output folder
     * @param fileOut
     *            output stream into folder
     * @param keys
     *            queue of terms
     * @param map
     *            unsorted map of terms as the key and definitions as the
     *            respective values
     *
     * @requires folder exists
     *
     * @ensures complete html files that can be opened by index.html
     */
    public static void output(String folder, SimpleWriter fileOut,
            Queue<String> keys, Map<String, String> map) {
        //header
        fileOut.println("<html>");
        fileOut.println("<head>");
        fileOut.println("<title>Glossary</title>");
        fileOut.println("</head>");
        fileOut.println("<body>");
        fileOut.println("<h2>Glossary</h2>");
        fileOut.println("<hr>");
        fileOut.println("<h3>Index</h3>");
        fileOut.println("<ul>");

        Queue<String> keys2 = new Queue2<String>();
        while (keys.length() > 0) {
            String key = keys.dequeue();
            String definition = map.value(key);
            //hyperlinked terms that open term.html
            fileOut.print("<li><a href=\"" + key + ".html\">");
            fileOut.println(key + "</a></li>");
            keys2.enqueue(key);
//open stream to term.html file
            SimpleWriter keyOut = new SimpleWriter1L(
                    folder + "/" + key + ".html");
//term.html header
            keyOut.println("<html>");
            keyOut.println("<head>");
            keyOut.println("<title>" + key + "</title");
            keyOut.println("</head>");
            keyOut.println("<body>");
            keyOut.println("<h2><b><i><font color=\"red\">" + key
                    + "</font></i></b></h2>");
            keyOut.println("<blockquote>");

            //separator set
            Set<Character> separators = new Set1L<Character>();
            separators.add(',');
            separators.add(' ');
            separators.add('.');
            separators.add('!');
            separators.add('?');
            separators.add('/');
            separators.add(';');
            separators.add(':');
            separators.add('-');

            String wordOrSeparator = "";
            //output definition word by word with nextWordOrSeparator
            for (int o = 0; o < definition.length(); o = o
                    + wordOrSeparator.length()) {
                wordOrSeparator = nextWordOrSeparator(definition, o,
                        separators);
                //print as hyperlink if word is a term
                if (map.hasKey(wordOrSeparator)) {
                    keyOut.print("<a href=\"" + wordOrSeparator + ".html\">"
                            + wordOrSeparator + "</a>");
                } else {
                    keyOut.print(wordOrSeparator);
                }
            }

            //term.html closing tags
            keyOut.println("</blockquote>");
            keyOut.println("<hr />");
            keyOut.println(
                    "<p>Return to <a href=\"index.html\">index</a>.</p>");
            keyOut.println("</body>");
            keyOut.println("</html>");
            keyOut.close();
        }
        //closing tags
        fileOut.println("</ul>");
        fileOut.println("</body>");
        fileOut.println("</html>");
    }

    /**
     * Compare two strings based on alphabetical order
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
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

        //prompt for input and output file and open streams
        out.print("name of the input file: ");
        String inputFile = in.nextLine();
        SimpleReader fileIn = new SimpleReader1L(inputFile);
        SimpleReader fileIn2 = new SimpleReader1L(inputFile);

        out.print("name of a folder: ");
        String folder = in.nextLine();
        SimpleWriter fileOut = new SimpleWriter1L(folder + "/index.html");
        in.close();
        out.close();

        //sort text file into terms and definitions
        Queue<String> terms = sortTerm(fileIn);
        Queue<String> defs = sortDef(fileIn2);
        //sort terms and definitions into a map
        Map<String, String> map = new Map2<String, String>();
        while (defs.length() > 0) {
            String term = terms.dequeue();
            String def = defs.dequeue();
            terms.enqueue(term);
            map.add(term, def);
        }
        //sort alphabetically
        Comparator<String> comparator = new StringLT();
        terms.sort(comparator);

        //output
        output(folder, fileOut, terms, map);

    }

}
