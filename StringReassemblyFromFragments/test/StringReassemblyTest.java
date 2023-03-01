import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {
    //overlap
    @Test
    public void testOverlap1() {
        String str1 = "keybo";
        String str2 = "eyboard";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(4, overlap);
    }

    @Test
    public void testOverlap2() {
        String str1 = "ton";
        String str2 = "night";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(1, overlap);
    }

    @Test
    public void testOverlap3() {
        String str1 = "hot";
        String str2 = "dog";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(0, overlap);
    }

    //combination
    @Test
    public void testCombination1() {
        String str1 = "keybo";
        String str2 = "eyboard";
        int overlap = 4;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("keyboard", combine);
    }

    @Test
    public void testCombination2() {
        String str1 = "ton";
        String str2 = "night";
        int overlap = 1;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("tonight", combine);
    }

    @Test
    public void testCombination3() {
        String str1 = "hot";
        String str2 = "dog";
        int overlap = 0;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("hotdog", combine);
    }

    //addToSetAvoidingSubstrings
    @Test
    public void testAddToSetAvoidingSubstrings1() {
        Set<String> strSet = new Set2<>();
        strSet.add("keybo");
        strSet.add("eyboard");
        strSet.add("mouse");
        String str = "keyboard";
        Set<String> expect = new Set2<>();
        expect.add("mouse");
        expect.add("keyboard");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAddToSetAvoidingSubstrings2() {
        Set<String> strSet = new Set2<>();
        strSet.add("ton");
        strSet.add("night");
        strSet.add("day");
        String str = "tonight";
        Set<String> expect = new Set2<>();
        expect.add("tonight");
        expect.add("day");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAddToSetAvoidingSubstrings3() {
        Set<String> strSet = new Set2<>();
        strSet.add("hotdog");
        strSet.add("cat");
        String str = "dog";
        Set<String> expect = new Set2<>();
        expect.add("hotdog");
        expect.add("cat");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expect, strSet);
    }

    //printWithLineSeparators
    @Test
    public void testPrintWithLineSeparators1() {
        SimpleWriter out = new SimpleWriter1L(
                "testPrintWithLineSeparators1.txt");
        SimpleReader in = new SimpleReader1L(
                "testPrintWithLineSeparators1.txt");
        String text = "tonight~to night~to~night";
        String expect = "tonight\nto night\nto\nnight";
        StringReassembly.printWithLineSeparators(text, out);
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String line3 = in.nextLine();
        String line4 = in.nextLine();
        String test = line1 + "\n" + line2 + "\n" + line3 + "\n" + line4;
        in.close();
        out.close();
        assertEquals(expect, test);
    }

    @Test
    public void testPrintWithLineSeparators2() {
        SimpleWriter out = new SimpleWriter1L(
                "testPrintWithLineSeparators2.txt");
        SimpleReader in = new SimpleReader1L(
                "testPrintWithLineSeparators2.txt");
        String text = "keyboard key~board";
        String expect = "keyboard key\nboard";
        StringReassembly.printWithLineSeparators(text, out);
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String test = line1 + "\n" + line2;
        in.close();
        out.close();
        assertEquals(expect, test);
    }

    @Test
    public void testPrintWithLineSeparators3() {
        SimpleWriter out = new SimpleWriter1L(
                "testPrintWithLineSeparators3.txt");
        SimpleReader in = new SimpleReader1L(
                "testPrintWithLineSeparators3.txt");
        String text = "~hotdog";
        String expect = "\nhotdog";
        StringReassembly.printWithLineSeparators(text, out);
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String test = line1 + "\n" + line2;
        in.close();
        out.close();
        assertEquals(expect, test);
    }

    //assemble
    @Test
    public void testAssemble1() {
        Set<String> strSet = new Set2<>();
        strSet.add("key b");
        strSet.add("y board");
        strSet.add("d and");
        strSet.add("and mouse");
        Set<String> expect = new Set2<>();
        expect.add("key board and mouse");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAssemble2() {
        Set<String> strSet = new Set2<>();
        strSet.add("hot d");
        strSet.add("dog");
        Set<String> expect = new Set2<>();
        expect.add("hot dog");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAssemble3() {
        Set<String> strSet = new Set2<>();
        strSet.add("to n");
        strSet.add("night");
        Set<String> expect = new Set2<>();
        expect.add("to night");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAssemble4() {
        Set<String> strSet = new Set2<>();
        strSet.add("day");
        strSet.add("night");
        Set<String> expect = new Set2<>();
        expect.add("night");
        expect.add("day");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

}