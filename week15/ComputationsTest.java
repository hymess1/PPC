import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComputationsTest {

    @Test
    void testFibonacci() {
        assertEquals(0, Computations.fibonacci(0));
        assertEquals(1, Computations.fibonacci(1));
        assertEquals(5, Computations.fibonacci(5));
    }

    @Test
    void testFibonacciNegative() {
        assertThrows(IllegalArgumentException.class, () -> Computations.fibonacci(-1));
    }

    @Test
    void testIsPrime() {
        assertEquals(true, Computations.isPrime(7));
        assertEquals(false, Computations.isPrime(8));
    }

    @Test
    void testEvenOdd() {
        assertEquals(true, Computations.isEven(4));
        assertEquals(false, Computations.isEven(5));

        assertEquals(true, Computations.isOdd(5));
        assertEquals(false, Computations.isOdd(4));
    }

    @Test
    void testTemperatureConversion() {
        assertEquals(0.0, Computations.toCelsius(32));
        assertEquals(32.0, Computations.toFahrenheit(0));
    }
}
