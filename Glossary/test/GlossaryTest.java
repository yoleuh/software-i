import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;
import components.queue.Queue2;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 *
 */

/**
 * @author brian
 *
 */
public class GlossaryTest {

    //nextWordOrSeparator
    //testing middle of word and separators, numbers, multiple separators

    @Test
    public void nextWordOrSeparatorTest1() {
        String text = "testing 123 !;";
        int position = 0;
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

        String nextWordOrSeparator = Glossary.nextWordOrSeparator(text,
                position, separators);

        assertEquals("testing", nextWordOrSeparator);
    }

    @Test
    public void nextWordOrSeparatorTest2() {
        String text = "testing 123 !;";
        int position = 5;
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

        String nextWordOrSeparator = Glossary.nextWordOrSeparator(text,
                position, separators);

        assertEquals("ng", nextWordOrSeparator);
    }

    @Test
    public void nextWordOrSeparatorTest3() {
        String text = "testing 123 !;";
        int position = 7;
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

        String nextWordOrSeparator = Glossary.nextWordOrSeparator(text,
                position, separators);

        assertEquals(" ", nextWordOrSeparator);
    }

    @Test
    public void nextWordOrSeparatorTest4() {
        String text = "testing 123 !;";
        int position = 8;
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

        String nextWordOrSeparator = Glossary.nextWordOrSeparator(text,
                position, separators);

        assertEquals("123", nextWordOrSeparator);
    }

    @Test
    public void nextWordOrSeparatorTest5() {
        String text = "testing 123 !;";
        int position = 12;
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

        String nextWordOrSeparator = Glossary.nextWordOrSeparator(text,
                position, separators);

        assertEquals("!;", nextWordOrSeparator);
    }

    @Test
    public void nextWordOrSeparatorTest6() {
        String text = "testing 123 !;";
        int position = 13;
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

        String nextWordOrSeparator = Glossary.nextWordOrSeparator(text,
                position, separators);

        assertEquals(";", nextWordOrSeparator);
    }

    @Test
    public void nextWordOrSeparatorTest7() {
        String text = "testing 123 !;";
        int position = 11;
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

        String nextWordOrSeparator = Glossary.nextWordOrSeparator(text,
                position, separators);

        assertEquals(" !;", nextWordOrSeparator);
    }

    //sortTerm
    //testing one letter terms

    @Test
    public void sortTermTest1() {
        SimpleReader fileIn = new SimpleReader1L("termsTest1.txt");
        Queue<String> result = new Queue2<>();
        result.enqueue("a");
        Queue<String> sortTerm = Glossary.sortTerm(fileIn);
        assertEquals(result, sortTerm);
    }

    @Test
    public void sortTermTest2() {
        SimpleReader fileIn = new SimpleReader1L("termsTest2.txt");
        Queue<String> result = new Queue2<>();
        result.enqueue("computer");
        Queue<String> sortTerm = Glossary.sortTerm(fileIn);
        assertEquals(result, sortTerm);
    }

    @Test
    public void sortTermTest3() {
        SimpleReader fileIn = new SimpleReader1L("termsTest3.txt");
        Queue<String> result = new Queue2<>();
        result.enqueue("phrase");
        Queue<String> sortTerm = Glossary.sortTerm(fileIn);
        assertEquals(result, sortTerm);
    }

    //sortDef
    //testing terms with separators, multiple lines

    @Test
    public void sortDefTest1() {
        SimpleReader fileIn = new SimpleReader1L("termsTest1.txt");
        Queue<String> result = new Queue2<>();
        result.enqueue(
                "used when referring to someone or something for the first time in a text or conversation...");
        Queue<String> sortDef = Glossary.sortDef(fileIn);
        assertEquals(result, sortDef);
    }

    @Test
    public void sortDefTest2() {
        SimpleReader fileIn = new SimpleReader1L("termsTest2.txt");
        Queue<String> result = new Queue2<>();
        result.enqueue(
                "an electronic device for storing and processing data, typically in binary form, according to instructions given to it in a variable program.");
        Queue<String> sortDef = Glossary.sortDef(fileIn);
        assertEquals(result, sortDef);
    }

    @Test
    public void sortDefTest3() {
        SimpleReader fileIn = new SimpleReader1L("termsTest3.txt");
        Queue<String> result = new Queue2<>();
        result.enqueue("a collection of multiple--words");
        Queue<String> sortDef = Glossary.sortDef(fileIn);
        assertEquals(result, sortDef);
    }
}
