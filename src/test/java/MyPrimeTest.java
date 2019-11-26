import org.junit.Test;

import static org.junit.Assert.*;

public class MyPrimeTest
{
    @Test
    public void isPrimeOfPrimeShouldReturnTrue()
    {
        assertTrue(MyPrime.isPrime(2));
        assertTrue(MyPrime.isPrime(3));
        assertTrue(MyPrime.isPrime(5));
        assertTrue(MyPrime.isPrime(7));
        assertTrue(MyPrime.isPrime(11));
        assertTrue(MyPrime.isPrime(13));
        assertTrue(MyPrime.isPrime(17));
    }

    @Test
    public void isPrimeOfNotPrimeShouldReturnFalse()
    {
        assertFalse(MyPrime.isPrime(-2));
        assertFalse(MyPrime.isPrime(0));
        assertFalse(MyPrime.isPrime(1));
        assertFalse(MyPrime.isPrime(4));
        assertFalse(MyPrime.isPrime(6));
        assertFalse(MyPrime.isPrime(9));
        assertFalse(MyPrime.isPrime(14));
        assertFalse(MyPrime.isPrime(15));
        assertFalse(MyPrime.isPrime(21));
    }
}

