import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MyPrimeTest
{
        @Test
        public void isPrimeOfPrimeShouldReturnTrue()
        {
                assertEquals(true, MyPrime.isPrime(2));
                assertEquals(true, MyPrime.isPrime(3));
                assertEquals(true, MyPrime.isPrime(5));
                assertEquals(true, MyPrime.isPrime(7));
                assertEquals(true, MyPrime.isPrime(11));
                assertEquals(true, MyPrime.isPrime(13));
                assertEquals(true, MyPrime.isPrime(17));
        }

        @Test
        public void isPrimeOfNotPrimeShouldReturnFalse()
        {
                assertEquals(false, MyPrime.isPrime(-2));
                assertEquals(false, MyPrime.isPrime(0));
                assertEquals(false, MyPrime.isPrime(1));
                assertEquals(false, MyPrime.isPrime(4));
                assertEquals(false, MyPrime.isPrime(6));
                assertEquals(false, MyPrime.isPrime(9));
                assertEquals(false, MyPrime.isPrime(14));
                assertEquals(false, MyPrime.isPrime(15));
                assertEquals(false, MyPrime.isPrime(21));
        }
}