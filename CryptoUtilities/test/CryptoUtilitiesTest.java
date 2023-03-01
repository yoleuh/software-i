import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author brian
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_10_5() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(5);
        NaturalNumber m = new NaturalNumber2(5);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_9_3() {
        NaturalNumber n = new NaturalNumber2(9);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(3);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    public void testIsEven_10() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(10);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    public void testIsEven_9() {
        NaturalNumber n = new NaturalNumber2(9);
        NaturalNumber nExpected = new NaturalNumber2(9);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_50_0_19() {
        NaturalNumber n = new NaturalNumber2(50);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */
    @Test
    public void compositeWitness_8_2() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber w = new NaturalNumber2(2);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(witness, true);
    }

    @Test
    public void compositeWitness_5_2() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber w = new NaturalNumber2(2);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(witness, true);
    }

    @Test
    public void compositeWitness_100_5() {
        NaturalNumber n = new NaturalNumber2(100);
        NaturalNumber w = new NaturalNumber2(5);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(witness, true);
    }

    @Test
    public void compositeWitness_99_3() {
        NaturalNumber n = new NaturalNumber2(99);
        NaturalNumber w = new NaturalNumber2(3);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(witness, true);
    }

    /*
     * Tests of isPrime1
     */
    @Test
    public void isPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals(prime, true);
    }

    @Test
    public void isPrime1_98() {
        NaturalNumber n = new NaturalNumber2(98);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals(prime, false);
    }

    @Test
    public void isPrime1_3() {
        NaturalNumber n = new NaturalNumber2(3);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals(prime, true);
    }

    @Test
    public void isPrime1_101() {
        NaturalNumber n = new NaturalNumber2(101);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals(prime, false);
    }

    /*
     * Tests of isPrime2
     */
    @Test
    public void isPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals(prime, true);
    }

    @Test
    public void isPrime2_99() {
        NaturalNumber n = new NaturalNumber2(99);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals(prime, false);
    }

    @Test
    public void isPrime2_3() {
        NaturalNumber n = new NaturalNumber2(3);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals(prime, true);
    }

    @Test
    public void isPrime2_100() {
        NaturalNumber n = new NaturalNumber2(100);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals(prime, false);
    }

    /*
     * Tests of generateNextLikelyToPrime
     */

    @Test
    public void testGenerateNextLikelyToPrime_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(5);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);

    }

    @Test
    public void testGenerateNextLikelyToPrime_95() {
        NaturalNumber n = new NaturalNumber2(95);
        NaturalNumber nExpected = new NaturalNumber2(97);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);

    }

    @Test
    public void testGenerateNextLikelyToPrime_72() {
        NaturalNumber n = new NaturalNumber2(72);
        NaturalNumber nExpected = new NaturalNumber2(73);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);

    }

    @Test
    public void testGenerateNextLikelyToPrime_10() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(11);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);

    }
}